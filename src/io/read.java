package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import analysis.StringStandardize;

public class read  {
	public static String readFromTxt(String location) throws IOException {
		File file = new File(location);
		long filesize = file.length();
		if(filesize > Integer.MAX_VALUE) {
			System.out.println("大于2GiB，无法读取");
		}
		FileInputStream fi = new FileInputStream(file);
		byte[] buffer = new byte[(int) filesize];
		
		int offset = 0;
		int numRead = 0;
		while (offset < buffer.length
		&& (numRead = fi.read(buffer, offset, buffer.length - offset)) >= 0) {
			offset += numRead;
		}
		if (offset != buffer.length) {
			System.out.println("出现错误");
		}
		fi.close();
		
		String result = new String(buffer, "UTF8");
		
		result = StringStandardize.allTrim(result);
		
		return result;
	}
}
