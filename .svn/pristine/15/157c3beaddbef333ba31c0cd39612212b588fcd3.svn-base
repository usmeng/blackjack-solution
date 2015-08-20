package main;

import java.util.ArrayList;
import java.util.List;

import util.LogUtil;
import bean.Dealer;
import bean.Player;

public class Table {

	public static final int DEFAULT_MINIMUM_BET = 5;
	public static final int DEFAULT_MAXIMUM_BET = 500;
	
	private int minimumBet = DEFAULT_MINIMUM_BET;
	private int maximumBet = DEFAULT_MAXIMUM_BET;
	public static int maximumPlayers = 7;
	public static int defaultDeckSize = 6;
	
	private int id;
	private int gameId;
	private int taxPerTime;
	private int totalTax;
	private Dealer dealer;
	private DealerMachine machine;
	private List<Player> mPlayerList;


	public Table(Dealer dealer, int tax) {
		this.dealer = dealer;
		this.taxPerTime = tax;
		
		mPlayerList = new ArrayList<Player>();
		
		initDealerMachine();
		
		LogUtil.printMessage(toString() + "\n");
	}
	
	private void initDealerMachine(){
		machine = new DealerMachine(defaultDeckSize, 80);
		
		dealer.setDealerMachine(machine);
		
		dealer.shuffle();
	}
	
	public void loadPlayers(List<Player> playerList){
		for (int i = 0; i < playerList.size(); i++) {
			if(i >= maximumPlayers - 1){
				LogUtil.printMessage("the table is full~");
				return;
			}
			Player player = playerList.get(i);
			int bet = taxPerTime + minimumBet;
			if(player.getCurrentChip() < bet) {
				LogUtil.printMessage("player " + player.getName() + " has no enough chip");
			} else {
				mPlayerList.add(playerList.get(i));
				LogUtil.printMessage("Player" + (i + 1) + " "+ player.getName() + " join in the table...");
			}
		}
	}
	
	public void startAGame(){
		
		for (int i = 1; i <= 1000; i++) {
			LogUtil.printMessage("\n------------- game" + i + " -------------");
			Game game = new Game(dealer, this, gameId++);
			
			if(machine.isOnStop()) {
				LogUtil.printMessage("\n------------- shuffle cards ------------\n");
				dealer.shuffle();
			}
			
			game.addPlayers(mPlayerList);
			
			game.start();
		}
	}

	public int getTax() {
		return taxPerTime;
	}

	public void setTax(int tax) {
		this.taxPerTime = tax;
	}

	public int getMinimumBet() {
		return minimumBet;
	}

	public void setMinimumBet(int minimumBet) {
		this.minimumBet = minimumBet;
	}

	public int getMaximumBet() {
		return maximumBet;
	}

	public void setMaximumBet(int maximumBet) {
		this.maximumBet = maximumBet;
	}

	public Dealer getDealer() {
		return dealer;
	}

	public void setDealer(Dealer dealer) {
		this.dealer = dealer;
	}

	public DealerMachine getMachine() {
		return machine;
	}

	public void setMachine(DealerMachine machine) {
		this.machine = machine;
	}

	public int getTotalTax() {
		return totalTax;
	}
	
	public void addToTax(int tax){
		totalTax += tax;
	}
	
	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("Table " + id);
		builder.append(", Dealer: " + dealer.getName());
		builder.append(", Tax per game: " + taxPerTime);
		builder.append("$ , Minimum Bet: " + minimumBet + "$");
		return builder.toString();
	}

}
