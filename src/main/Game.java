package main;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import strategy.Rule;
import strategy.StrategyManager;
import util.LogUtil;
import analysis.ResultItem;
import analysis.ResultManager;
import bean.Card;
import bean.Dealer;
import bean.HandCard;
import bean.Person;
import bean.Player;
import club.PlayerCenter;

public class Game {

	public static final int INITIAL_CARD_SIZE = 2;
	public static final int DEFAULT_COST_TIME = 240;
	
	/** calculate how much time one game spends */
	private int mId;
	private List<Player> mPlayers;
	private Table mTable;
	private Dealer dealer;
	private StrategyManager mManager;
	private List<ResultItem> mRecords;
	
	public Game(Dealer dealer, Table table, int id){
		this.dealer = dealer;
		this.mTable = table;
		mId = id;
		
		mManager = StrategyManager.getInstance();
		mManager.setDealerMachine(dealer.getDealerMachine());
		
		mPlayers = new ArrayList<Player>();
		mRecords = new ArrayList<ResultItem>();

		LogUtil.printMessage("\nA new game start...");
	}
	
	public void addPlayer(Player player) {
		if (player != null)
			mPlayers.add(0, player);
	}
	
	public void addPlayers(List<Player> players){
		mPlayers.addAll(players);
	}
	
	public void start(){
		// initializing, clear card data
		for (Player player : mPlayers) {
			player.getHandCard().initClear();
			if (player.isSpilted()) {
				player.getSplitHand().initClear();
				player.setSplit(false);
			}
		}
		dealer.getHandCard().initClear();
		
		// first step: players pay tax to join a game
		payTaxToAttenGame();
		LogUtil.printMessage("");
		
		// second step: execute chip strategy
		for(Player player : mPlayers) {
			mManager.excuteBetStrategy(player);
		}
		
		// third step: delivery two cards to every player
		assignTwoCards();
		mManager.setOpenCard(dealer.getOpenCard());
		LogUtil.printMessage("");
		
		// fourth step: player execute strategy one bye one
		for(Person player : mPlayers){
			mManager.excuteHitStrategy(player, player.getHandCard());
			System.out.println();
		}
		if(hasPlayerAlive()) {
			mManager.excuteHitStrategy(dealer, dealer.getHandCard());
		} else {
			LogUtil.printMessage("All players bursted! Dearler stand.");
		}
		LogUtil.printMessage("");
		
		// fifth step: claim payment
		claimPayment();
		
		// sixth step: generate result, record game
		recordGame();
	}

	private boolean hasPlayerAlive() {
		for (Player player : mPlayers) {
			if(!Rule.isBurst(player.getHandCard())) return true;
			if(player.isSpilted() && !Rule.isBurst(player.getSplitHand()))
				return true;
		}
		return false;
	}

	private void recordGame() {
		ResultManager.getInstance().saveResutItemList(mRecords);
	}

	private void claimPayment() {
		for (Player player : mPlayers) {
			ResultItem item = new ResultItem();
			item.gameId = mId;
			item.playerId = player.getId();
			item.playerName = player.getName();
			item.originChip = player.getOriginalChip();
			item.currentTotalBet = player.getTotalBetChip();
			item.lastChip = player.getCurrentChip();
			
			judgeResultAndRecord(player.getHandCard(), player, item);
			judgeResultAndRecord(player.getSplitHand(), player, item);
			
			mRecords.add(item);
		}
	}
	
	public void judgeResultAndRecord(HandCard hands, Player player, ResultItem item){
		if(hands == null || hands.size() == 0) return;
		
		Rule.ResultValue resultState = null;
		
		if(Rule.isBurst(hands)) {
			player.updateTotalChip(hands.getBetChip() * -1);
			dealer.updateTotalChip(hands.getBetChip());
			resultState = Rule.ResultValue.LOSE;
		} else if(Rule.isBurst(dealer.getHandCard())){
			if(Rule.isBlackJack(hands)) {
				player.updateTotalChip(hands.getBetChip() * 2);
				dealer.updateTotalChip(hands.getBetChip() * -2);
				resultState = Rule.ResultValue._WIN;
			} else {
				player.updateTotalChip(hands.getBetChip());
				dealer.updateTotalChip(hands.getBetChip() * -1);
				resultState = Rule.ResultValue._WIN;
			}
		} else { // both player and dealer are alive
			if(Rule.isBlackJack(hands) && !Rule.isBlackJack(dealer.getHandCard())) {
				player.updateTotalChip(hands.getBetChip() * 2);
				dealer.updateTotalChip(hands.getBetChip() * -2);
				resultState = Rule.ResultValue._WIN;
			} else {
				if(Rule.calPoints(hands) > Rule.calPoints(dealer.getHandCard())) {
					player.updateTotalChip(hands.getBetChip());
					dealer.updateTotalChip(hands.getBetChip() * -1);
					resultState = Rule.ResultValue._WIN;
				} else if(Rule.calPoints(hands) < Rule.calPoints(dealer.getHandCard())){
					player.updateTotalChip(hands.getBetChip() * -1);
					dealer.updateTotalChip(hands.getBetChip());
					resultState = Rule.ResultValue.LOSE;
				} else {
					resultState = Rule.ResultValue.EVEN;
				}
			}
		}
		item.result = resultState.toString();
		item.currentChip = player.getCurrentChip();
		item.currentRevenue = item.currentChip - item.lastChip;
		item.costTime = DEFAULT_COST_TIME;
		item.playedTimes = PlayerCenter.getInstance().checkAndUpdateTimes(player);
		item.playingTime = new Date().getTime();
		item.tax = mTable.getTax();
		LogUtil.printMessage(player.getName() + ": " + resultState.toString() + ", Revenue -> " + item.currentRevenue);
		LogUtil.printMessage(player.getName() + ": currentChip -> " + item.currentChip + "\n");
	}
	
	private void assignTwoCards() {
		int size = mPlayers.size();
		Person person = null;
		for (int i = 0; i < (size + 1) * INITIAL_CARD_SIZE; i++) {
			Card card = dealer.dealCard();
			if(i != 0 && (i + 1) % (size + 1) == 0){
				dealer.addCard(card);
				person = dealer;
			} else {
				Person player = mPlayers.get(i % size);
				player.addCard(card);
				person = player;
			}
			LogUtil.printMessage(person.getName() + ": --> " + card.getColor() + "\t" + card.getName());
		}
	}

	private void payTaxToAttenGame(Person player) {
		LogUtil.printMessage(player.getName() + ": pay tax:" + mTable.getTax() + "$");
		player.updateTotalChip(mTable.getTax() * -1);
		mTable.addToTax(mTable.getTax());
	}
	
	private void payTaxToAttenGame(){
		for(Person player : mPlayers) {
			payTaxToAttenGame(player);
		}
		payTaxToAttenGame(dealer);
	}

	public int getTime() {
		return mId;
	}
	
}
