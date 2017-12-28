package main;

import java.io.*;

import formats.HeadInfo;
import formats.PageInfo;
import analysis.*;
import analysis.tokenize.*;
import step2.*;
import io.*;

public class JianpuInPDF {
	public static void main(String[] args) {
		JinCheng("C:/Users/wangrui/Documents/123.txt");
		//System.out.println(readResult);
	}
	public static void JinCheng(String path) {
		String result = new String();
		try {
			result = read.readFromTxt(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Scanner.tokenize(result);
		
		//PageInfo page = getChunk.getPgSt(result);
		//HeadInfo head = getChunk.getHeadString(result, page);
		//double beginY = head.getNoteStart(page.headerSize, page.noteSize);
		//NoteAnalysis.getPrimary(getChunk.getNoteString(result));
		//try {
		//	write.writeToPDF("C:/Users/wangrui/Documents/gzby.pdf", page, head);
		//} catch (IOException e) {
		//	e.printStackTrace();
		//}
	}
}
