package analysis;


public class ResultItem {

	public int id;
	public int gameId;
	public int playerId;
	public String playerName;
	public int originChip;
	public int currentTotalBet;
	public String result;
	public int currentRevenue;
	public int tax;
	public int currentChip;
	public int costTime;
	public int playedTimes;
	public long playingTime;
	public int lastChip;
	
	@Override
	public String toString() {
		return id + ", " + gameId + ", " + playerId + ", " + playerName + ", "
				+ originChip + ", " + currentTotalBet + ", " + result + ", "
				+ currentRevenue + ", " + tax + ", " + currentChip + ", "
				/*+ winRate + ", "*/ + costTime + ", " + playedTimes + ", " + playingTime;
	}


}
