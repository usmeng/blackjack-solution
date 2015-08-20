package main;

import java.util.ArrayList;
import java.util.List;

import strategy.StrategyManager;
import util.LogUtil;
import analysis.ResultManager;
import bean.Dealer;
import bean.Player;

public class MainTest {

	private static StrategyManager mManager;

	public static void main(String[] args) {
		
		Dealer dealer = new Dealer("Dealer");
		Table table = new Table(dealer , 1);
		
		List<Player> playerList = new ArrayList<Player>();
		
		Player playerA = new Player("Meng", 10000);
		Player playerB = new Player("Zhou", 10000);
		
		playerA.setBetChip(10);
		playerB.setBetChip(10);
		
		mManager = StrategyManager.getInstance();
		
		dealer.setStrategy(mManager.getDealerStrategy());
		playerA.setStrategy(mManager.getExpertStrategy());
		playerB.setStrategy(mManager.getDealerStrategy());
		
		playerList.add(playerA);
		playerList.add(playerB);
		
		table.loadPlayers(playerList);
		
		// start a game
		table.startAGame();
		
		ResultManager.getInstance().wirteResultsToFile();
		
		LogUtil.printMessage("Test Done!");
		for (Player player : playerList) {
			LogUtil.printMessage(player.getName() + ": originalChip -> " + player.getOriginalChip() + ", currentChip -> " + player.getCurrentChip());
		}
		LogUtil.printMessage(dealer.getName() + ": originalChip -> 0, currentChip -> " + dealer.getCurrentChip());
	}
}
