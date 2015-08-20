package strategy.hit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import util.LogUtil;

import bean.Card;
import bean.HandCard;

public class HitStrategy implements IHitStrategy{

	private Map<Condition, Action> mTable;
	private String mFileName;
	private Card mOpenCard;
	
	public HitStrategy(File file){
		if(file != null)
			mFileName = file.getPath();
		
		loadData();
	}
	
	public HitStrategy(String str){
		mFileName = str;
		loadData();
	}
	
	private void loadData() {
		if(mTable == null) mTable = new HashMap<Condition, Action>();
		String data = readToBuffer();
		String[] times = data.split("\n");
		for (String str : times) {
			String[] slections = str.trim().split("[ ]+");
			String[] points = slections[0].split(",");
			int pointA = Integer.parseInt(points[0]);
			int pointB = Integer.parseInt(points[1]);
			String action = slections[1].substring(slections[1].indexOf('{') + 1, slections[1].indexOf('}'));
			String[] actions = action.split(",");
			for (int i = 0; i < actions.length; i++) {
				Condition condition = new Condition();
				condition.pointA = pointA;
				condition.pointB = pointB;
				condition.openPoint = i + 2;
				int index = Integer.parseInt(actions[i]);
				mTable.put(condition, Action.values()[index]);
			}
		}
	}
	
	public void printTable(){
		if(mTable == null) LogUtil.printMessage("table is null");
		Iterator<Condition> it = mTable.keySet().iterator();
		while(it.hasNext()) {
			Condition condition = it.next();
			Action action = mTable.get(condition);
			LogUtil.printMessage(condition.toString() + ", Action: " + action.toString());
		}
	}
	
	public String readToBuffer() {
		StringBuffer buffer = new StringBuffer();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(mFileName)));
			String line = reader.readLine();
			while (line != null) {
				buffer.append(line);
				buffer.append("\n");
				line = reader.readLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null)
					reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return buffer.toString();
	}

	@Override
	public void setOpenCard(Card card) {
		mOpenCard = card;
	}

	
	@Override
	public Action nextStep(HandCard handCard) {
		List<Card> cards = handCard.handCards;
		Condition condition = new Condition();
		condition.openPoint = mOpenCard.getPoint();

		if (cards.size() == 2) {
			int pA = cards.get(0).getPoint();
			int pB = cards.get(1).getPoint();

			if (pA == pB) {
				condition.pointA = pA;
				condition.pointB = pB;
			} else if (pA == 11 || pB == 11) {
				condition.pointA = 11;
				condition.pointB = pA != 11 ? pA : pB;
			} else {
				condition.pointA = -1;
				condition.pointB = pA + pB;
			}
		} else {
			condition.pointA = -1;
			int pointB = 0;
			for (int i = 0; i < cards.size(); i++) {
				pointB += cards.get(i).getPoint();
			}
			condition.pointB = pointB;
		}
		
		Iterator<Condition> it = mTable.keySet().iterator();
		while(it.hasNext()) {
			Condition con = it.next();
			if(condition.equals(con)) {
				return mTable.get(con);
			}
		}
		return Action.STAND;
	}
	
	/** please reference strategy table */
	public Action nextStep(Condition condition) {
		Iterator<Condition> it = mTable.keySet().iterator();
		while(it.hasNext()) {
			Condition con = it.next();
			if(condition.equals(con)) {
				return mTable.get(con);
			}
		}
		return Action.STAND;
	}
	
	public boolean contains(Condition condition){
		if(mTable == null) return false;
		return mTable.containsKey(condition);
	}
	
	public static void main(String[] args) {
		File file = new File("strategy");
		File[] mStrategyFiles = file.listFiles();
		
		HitStrategy strategy = new HitStrategy(mStrategyFiles[0]);
//		strategy.printTable();
		
		Condition condition = new Condition(-1, 17, 5);
		Action nextStep = strategy.nextStep(condition);
		System.out.println(nextStep == Action.STAND);
		
		System.out.println(nextStep.toString());
	}

}
