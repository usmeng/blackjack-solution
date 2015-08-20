package club;

import java.util.HashMap;
import java.util.Map;

import bean.Player;

public class PlayerCenter {

	private static PlayerCenter mCenter = new PlayerCenter();
	private Map<Player, PlayerInfo> maps;
	
	private PlayerCenter(){
		maps = new HashMap<Player, PlayerInfo>();
	}
	
	public static PlayerCenter getInstance(){
		return mCenter;
	}
	
	public static class PlayerInfo{
		int times;
		int orginalChip;
		int checkOutChip;
	}
	
	public void checkIn(Player player){
		PlayerInfo playerInfo = maps.get(player);
		if(playerInfo == null) {
			playerInfo = new PlayerInfo();
			playerInfo.orginalChip = player.getOriginalChip();
		}
	}
	
	public void checkOut(Player player){
		PlayerInfo info = maps.get(player);
		if(info == null) return;
		info.checkOutChip = player.getCurrentChip();
	}
	
	public int checkTimes(Player player){
		PlayerInfo info = maps.get(player);
		if(info == null) return 0;
		return info.times;
	}
	
	public int checkAndUpdateTimes(Player player){
		PlayerInfo info = maps.get(player);
		if(info == null) return 0;
		return info.times++;
	}
	
}
