package cn.zing.boot.demo.util;

import java.io.File;
import java.io.FileReader;

public class FileUtil {

	public static String read(File file) throws Exception {
		StringBuilder content = new StringBuilder();
		int num = 0;
		char[] buf = new char[1024];
		FileReader fr = new FileReader(file);
		while ((num = fr.read(buf)) != -1) {
			content.append(new String(buf, 0, num));
		}
		fr.close();
		return content.toString();
	}
}
