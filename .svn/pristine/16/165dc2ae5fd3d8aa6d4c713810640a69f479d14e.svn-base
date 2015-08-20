package util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LogUtil {

	private static final String LOG_PATH = "log";
	
	static{
		File file = new File(LOG_PATH);
		if(!file.exists()) file.mkdirs();
	}
	
	public static void printMessage(String str) {
		System.out.println(str);
	}
	
	public static void printMessage(String str, boolean toFile) {
		if(toFile) appendMethodB(LOG_PATH + "/log.txt", str + "\n");
		printMessage(str);
	}

	public static void appendMethodB(String fileName, String content) {
		try {
			FileWriter writer = new FileWriter(fileName, true);
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
