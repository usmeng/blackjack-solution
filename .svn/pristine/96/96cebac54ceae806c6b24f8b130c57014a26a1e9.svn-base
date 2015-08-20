package strategy;

import java.io.File;

import main.DealerMachine;
import main.Table;
import strategy.hit.Action;
import strategy.hit.HitStrategy;
import strategy.hit.IHitStrategy;
import util.LogUtil;
import bean.Card;
import bean.HandCard;
import bean.Person;
import bean.Player;

public class StrategyManager {

	public static final int DEARLER_STRATEGY = 0;
	public static final int EXPERT_STRATEGY = 1;
	
	private static final String TABLE_PATH = "strategy";
	private static StrategyManager manager = new StrategyManager();
	private Card openCard;
	private DealerMachine dealerMachine;
	private File[] mStrategyFiles;

	private StrategyManager(){
		File file = new File(TABLE_PATH);
		mStrategyFiles = file.listFiles();
	}
	
	public static StrategyManager getInstance(){
		return manager;
	}
	
	public void excuteHitStrategy(Person person, HandCard hand){
		
		HitStrategy strategy = person.getHitStrategy();
		strategy.setOpenCard(openCard);
		Action nextStep = strategy.nextStep(person.getHandCard());
		
		if(nextStep == Action.DOUBLE) {
			LogUtil.printMessage(person.getName() + ": double for next card: ");
			Player player = (Player) person;
			hand.setBetChip(hand.getBetChip() * 2);
			// add one more card when double
			Card card = dealerMachine.deal();
			player.addCard(card);
			LogUtil.printMessage(player.getName() + ": --> " + card.getColor() + "\t" + card.getName());
		} else if(nextStep == Action.SPLIT) {
			Player player = (Player) person;
			if(!player.isSpilted()) {
				player.split();
				LogUtil.printMessage(person.getName() + ": split hand cards: ");
				LogUtil.printMessage("Hand 1: ");
				excuteHitStrategy(player, player.getHandCard());
				LogUtil.printMessage("Hand 2: ");
				excuteHitStrategy(player, player.getSplitHand());
				return;
			}
		}
		while(Action.HIT == nextStep){
			Card card = dealerMachine.deal();
			person.addCard(card);
			LogUtil.printMessage(person.getName() + ": --> " + card.getColor() + "\t" + card.getName());
			nextStep = strategy.nextStep(person.getHandCard());
		}
		
		if(nextStep == Action.STAND) {
			displayResult(person, person.getHandCard());
		}

	}

	private void displayResult(Person person, HandCard hands) {
		LogUtil.printMessage(person.getName() + ": stand.");
		if(Rule.isBlackJack(hands)){
			LogUtil.printMessage(person.getName() + ": BlackJack, will win double bets!");
		} else if(Rule.isBurst(hands)) {
			LogUtil.printMessage(person.getName() + ": Point: " + Rule.calPoints(hands) + ", Burst! ");
		} else {
			LogUtil.printMessage(person.getName() + ": Point: " + Rule.calPoints(hands));
		}
	}
	
	public IHitStrategy createStrategy(String fileName){
		return null;
	}

	public Card getOpenCard() {
		return openCard;
	}

	public void setOpenCard(Card openCard) {
		this.openCard = openCard;
	}

	public void setDealerMachine(DealerMachine dealerMachine) {
		this.dealerMachine = dealerMachine;
	}
	
	public HitStrategy getDealerStrategy(){
		HitStrategy strategy = new HitStrategy(mStrategyFiles[DEARLER_STRATEGY]);
		return strategy;
	}
	
	public HitStrategy getExpertStrategy(){
		HitStrategy strategy = new HitStrategy(mStrategyFiles[EXPERT_STRATEGY]);
		return strategy;
	}
	
	public void createStrategy(IHitStrategy strategy){
		
	}

	public void excuteBetStrategy(Player player) {
		int betChip = player.getHandCard().getBetChip();
		if(betChip < Table.DEFAULT_MINIMUM_BET) player.setBetChip(Table.DEFAULT_MINIMUM_BET);
		else if(betChip > Table.DEFAULT_MAXIMUM_BET) {
			player.setBetChip(Table.DEFAULT_MAXIMUM_BET);
		}
	}
	
}
