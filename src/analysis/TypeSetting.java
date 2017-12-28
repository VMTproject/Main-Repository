package analysis;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import main.Note;

public class TypeSetting {
	public static Note[] getCoordinate(double Left, double Width, double Up,
			double Top, double Bottom, double Size, String[] source, double[][] width,
			int[] changeLine, int numberOfVerse) {
		Note[] result;
		int numberOfNote, numberOfBar;
		double PaddingUnit, x, y;
		numberOfNote = 0;
		numberOfBar = 0;
		int i = 0;
		int h = 0, m = 0;
		int page = 1;
		
		x = Left;
		y = Up;
		for(int a = 0; a < source.length; a++) {
			numberOfNote += source[a].length();
		}
		
		result = new Note[numberOfNote + source.length];
		
		for(int a = 1; a <= changeLine.length; a++) {
			System.out.println("Line " + a + " on page " + page);
			double totalEm = 0, totalPadding = 0;
			numberOfNote = 0;
			h = changeLine[a-1];
			if(a == changeLine.length) {
				numberOfBar = source.length - changeLine[a-1];
			} else {
				numberOfBar = changeLine[a] - changeLine[a-1];
			}
			String[] origin = new String[numberOfBar];
			for(int b = 0; b < numberOfBar; b++) {
				origin[b] = source[h + b];
				numberOfNote += origin[b].length();
				totalEm += width[h + b][0];
				totalPadding += width[h + b][1];
			}
			
			PaddingUnit = (Width-Size*(totalEm+numberOfBar))/(totalPadding+numberOfBar-1);
			if(a == changeLine.length & PaddingUnit > Size) {
				PaddingUnit = Size;
			}
			
			int j = 0, k = 0, l = 0;
			char current;
			for(i = 1; i<= numberOfNote; i++) {
				current = origin[j].charAt(k);
				k++;
				if(current > 47 && current < 56) {//音符
					result[m+l] = new Note(String.valueOf(current), page, x, y);
					x += (Size + PaddingUnit * 2);
					l++;
				} else if (current == 45) { //连接号代表增时线
					result[m+l] = new Note("G", page, x, y);
					x += (Size + PaddingUnit * 2);
					l++;
				} else if (current == 39) {
					result[m+l] = new Note("!", page, x - Size - PaddingUnit * 2, y + Size);
					l++;
				} else if (current == 34) {
					result[m+l] = new Note("@", page, x - Size - PaddingUnit * 2, y + Size);
					l++;
				} else if (current == 94) {
					result[m+l] = new Note("#", page, x - Size - PaddingUnit * 2, y + Size);
					l++;
				} else if (current == 44) {
					result[m+l] = new Note("D", page, x - Size - PaddingUnit * 2, y - Size);
					l++;
				} else if (current == 58) {
					result[m+l] = new Note("E", page, x - Size - PaddingUnit * 2, y - Size);
					l++;
				} else if (current == 59) {
					result[m+l] = new Note("F", page, x - Size - PaddingUnit * 2, y - Size);
					l++;
				} else if (current == 95) {
					if(result[m+l-1].content.equals("D")) {
						result[m+l-1].content = "\u0025";
					} else if(result[m+l-1].content.equals("E")) {
						result[m+l-1].content = "\u0026";
					} else if(result[m+l-1].content.equals("F")) {
						result[m+l-1].content = "\u0027";
					} else {
						result[m+l] = new Note("\u0024", page, x - Size - PaddingUnit * 2,
								y - Size);
						l++;
					}
					x -= PaddingUnit;
				} else if (current == 61) { //2下划线代表2减时线
					if(result[m+l-1].content.equals("D")) {
						result[m+l-1].content = "\\)";
					} else if(result[m+l-1].content.equals("E")) {
						result[m+l-1].content = "\\*";
					} else if(result[m+l-1].content.equals("F")) {
						result[m+l-1].content = "\\+";
					} else {
						result[m+l] = new Note("\\(", page, x - Size - PaddingUnit * 2, y - Size);
						l++;
					}
					x -= PaddingUnit * 2;
				} else if (current == 47) { //3下划线代表3减时线
					if(result[m+l-1].content == "D") {
						result[m+l-1].content = "-";
					} else if(result[m+l-1].content == "E") {
						result[m+l-1].content = "\\。";
					} else if(result[m+l-1].content == "F") {
						result[m+l-1].content = "/";
					} else {
						result[m+l] = new Note(",", page, x - Size - PaddingUnit * 2, y - Size);
						l++;
					}
					x -= PaddingUnit * 2;
				} else if (current == 92) { //4下划线代表4减时线
					if(result[m+l-1].content == "D") {
						result[m+l-1].content = "A";
					} else if(result[m+l-1].content == "E") {
						result[m+l-1].content = "B";
					} else if(result[m+l-1].content == "F") {
						result[m+l-1].content = "C";
					} else {
						result[m+l] = new Note("@", page, x - Size - PaddingUnit * 2, y - Size);
						l++;
					}
					x -= PaddingUnit * 2;
				} else if (current == 46) { //point
					if(result[m+l-1].content == "H") {
						result[m+l] = new Note("H", page, result[m+l-1].coordinateX + Size / 2, y);
						x += (Size + PaddingUnit) / 2;
						l++;
					} else {
						result[m+l] = new Note("H", page, result[m+l-1].coordinateX + Size, y);
						x += (Size + PaddingUnit) / 2;
						l++;
					}
				}
				if(k == origin[j].length()) {
					if(a == changeLine.length && j == numberOfBar - 1) {
						result[m+l] = new Note("\\", page, x, y);
					} else {
						result[m+l] = new Note("|", page, x, y);
					}
					x += Size + PaddingUnit;
					l++;
					j++;
					k = 0;
					if(j == numberOfBar + 1) {
						break;
					}
				}
			}
			m += l;
			x = Left;
			y -= Size * (numberOfVerse + 4.7);
			if(y < Bottom + Size * (numberOfVerse + 3)) {
				page++;
				System.out.println("Page " + (page-1) + " is full, opening page " + page);
				y = Top - Size;
			}
		}
		return result;
	}
	
	public static int appearNumber(String srcText, String findText) {
	    int count = 0;
	    Pattern p = Pattern.compile(findText);
	    Matcher m = p.matcher(srcText);
	    while (m.find()) {
	        count++;
	    }
	    return count;
	}
}
