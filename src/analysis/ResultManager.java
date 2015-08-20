package analysis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ResultManager {

	public static final String ROOT_DIRCTORY = "result/";
	private static ResultManager mManager = new ResultManager();
	private List<ResultItem> mItems;
	
	private ResultManager(){
		mItems = new ArrayList<ResultItem>();
		File file = new File(ROOT_DIRCTORY);
		if(!file.exists())
			file.mkdirs();
	}
	
	public static ResultManager getInstance(){
		return mManager;
	}
	
	public void writeResultToFile(List<ResultItem> list){
		if(list == null) return;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-hh-mm");
		String fileName = format.format(new Date()) + ".txt";
		try {
			File file = new File(ROOT_DIRCTORY + fileName);
			if(file.exists()) {
				file.delete();
			}
			FileWriter writer = new FileWriter(file);
			for (ResultItem resultItem : list) {
				writer.write(resultItem.toString());
				writer.append('\n');
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void addResutItem(ResultItem item){
		if(item != null) {
			mItems.add(item);
		}
	}
	
	public void saveResutItemList(List<ResultItem> items){
		if(items != null) {
			mItems.addAll(items);
		}
	}
	
	public void wirteResultsToFile(){
		writeResultToFile(mItems);
	}
	
	public List<ResultItem> getResultItems(){
		return mItems;
	}
	
	private String readResultStringFromFiles(String filePath){
		if(filePath == null) return null;
		File file = new File(filePath);
		if(!file.exists()) return null;
		BufferedReader reader = null;
		StringBuffer buffer = new StringBuffer();
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
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
	
	public List<ResultItem> readResultFromFiles(String filePath){
		String string = readResultStringFromFiles(filePath);
		if(string.length() == 0) return null;
		List<ResultItem> results = new ArrayList<ResultItem>();
		String[] split = string.split("\n");
		for (String content : split) {
			ResultItem item = new ResultItem();
			String[] fields = content.split(",[ ]+");
			item.id = Integer.parseInt(fields[0]);
			item.gameId = Integer.parseInt(fields[1]);
			item.playerId = Integer.parseInt(fields[2]);
			item.playerName = fields[3];
			item.originChip = Integer.parseInt(fields[4]);
			item.currentTotalBet = Integer.parseInt(fields[5]);
			item.result = fields[6];
			item.currentRevenue = Integer.parseInt(fields[7]);
			item.tax = Integer.parseInt(fields[8]);
			item.currentChip = Integer.parseInt(fields[9]);
			item.costTime = Integer.parseInt(fields[10]);
			item.playedTimes = Integer.parseInt(fields[11]);
			item.playingTime = Long.parseLong(fields[12]);
			
			results.add(item);
		}
		return results;
	}
	

	public static void main(String[] args) {
		ResultManager manager = ResultManager.getInstance();
		List<ResultItem> items = new ArrayList<ResultItem>();
		ResultItem item = new ResultItem();
		item.id = 1;
		item.playerName = "micheal";
		item.originChip = 1000;
		item.currentTotalBet = 20;
		item.tax = 1;
		item.currentRevenue = 20;
		item.currentChip = 1000 + 20 - 1;
		item.result = "win";
		item.playedTimes = 1;
		item.playingTime = new Date().getTime();
		item.costTime = 120;
		items.add(item);
		
//		manager.writeResultToFile(items);
		
		File[] files = new File(ROOT_DIRCTORY).listFiles();
		if(files.length > 0) {
			File file = files[0];
			String path = file.getPath();
			List<ResultItem> list = manager.readResultFromFiles(path);
			for (ResultItem resultItem : list) {
				System.out.println(resultItem.toString());
			}
		}
	}
}
