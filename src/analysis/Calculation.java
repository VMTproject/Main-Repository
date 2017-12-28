package analysis;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculation {
	public static int getBeat(String data, int noteOfBeat) {
		byte[] dataTrans = data.getBytes();
		int totalTime = 0;
		int currentTime = 0;
		int currentChar, beatTime;
		for (int i = 1; i <= dataTrans.length; i++) {
			currentChar = dataTrans[i-1]; //�õ�һ���ַ�
			if (currentChar > 47 && currentChar < 56) { //���ּ�����
				totalTime += 16;
				currentTime = 16;
			} else if (currentChar == 45) { //���ӺŴ�����ʱ��
				totalTime += 16;
				currentTime = 16;
			} else if (currentChar == 95) { //�»��ߴ�����ʱ��
				totalTime -= 8;
				currentTime = 8;
			} else if (currentChar == 61) { //�ȺŴ���˫��ʱ��
				totalTime -= 12;
				currentTime = 4;
			} else if (currentChar == 47) { //б�ߴ�������ʱ��
				totalTime -= 14;
				currentTime = 2;
			} else if (currentChar == 92) { //��б�ߴ����ļ�ʱ��
				totalTime -= 15;
				currentTime = 1;
			} else if (currentChar == 46) { //��Ŵ�����
				int a, b;
				a = currentTime;
				b = 0;
				if (a == 1) {
					b = 2;
				} else if (a < 4) {
					b = 4;
				} else if (a < 8) {
					b = 8;
				} else if (a < 16) {
					b = 16;
				} else if (a < 32) {
					b = 32;
				}
				a = b - a; //����һ����ʱֵʣ�൥λ
				if (a%2 != 0) {
					System.out.println("Illicit input");
					break;
				}
				totalTime += a / 2;//����ʱֵ
				currentTime += a / 2;
			}
		}
		beatTime = totalTime / 64 * noteOfBeat;
		return beatTime;
	}
	
	public static int correctSemi(String data, int noteOfBeat) {
		int beatTime = 64/noteOfBeat;
		int totalTime = 0;
		int currentTime = 0;
		int currentBeat = 0;
		int numberOfNec = beatTime - 1;
		byte[] dataTrans = data.getBytes();
		byte[] beatEnd = new byte[beatTime - 1];
		int currentChar;
		String Cole;
		for (int i = 1; i <= dataTrans.length; i++) {
			currentChar = dataTrans[i-1]; //�õ�һ���ַ�
			if (currentChar > 47 && currentChar < 56) { //���ּ�����
				totalTime += 16;
				currentTime = 16;											
			} else if (currentChar == 45) { //���ӺŴ�����ʱ��
				totalTime += 16;
				currentTime = 16;
			} else if (currentChar == 95) { //�»��ߴ�����ʱ��
				totalTime -= 8;
				currentTime = 8;
			} else if (currentChar == 61) { //�ȺŴ���˫��ʱ��
				totalTime -= 12;
				currentTime = 4;
			} else if (currentChar == 47) { //б�ߴ�������ʱ��
				totalTime -= 14;
				currentTime = 2;
			} else if (currentChar == 92) { //��б�ߴ����ļ�ʱ��
				totalTime -= 15;
				currentTime = 1;
			} else if (currentChar == 46) { //��Ŵ�����
				int a, b;
				a = currentTime;
				b = 0;
				if (a == 1) {
					b = 2;
				} else if (a < 4) {
					b = 4;
				} else if (a < 8) {
					b = 8;
				} else if (a < 16) {
					b = 16;
				} else if (a < 32) {
					b = 32;
				}
				a = b - a; //����һ����ʱֵʣ�൥λ
				if (a%2 != 0) {
					System.out.println("Illicit input");
					break;
				}
				totalTime += a / 2;//����ʱֵ
				currentTime += a / 2;
			}
			if(totalTime >= 16) {
				totalTime -= 16;
				currentBeat++;
				if(currentBeat < numberOfNec) {
					beatEnd[currentBeat] = dataTrans[i-1];
				}
			}
			Cole = beatEnd.toString();
			numberOfNec = appearNumber(Cole, "=")
					+ appearNumber(Cole, "/")
					+ appearNumber(Cole, "\\");
		}
		return numberOfNec;
	}
	
	public static double[] widthInEm(String data) {
		byte[] dataTrans = data.getBytes();
		double[] width = new double[2];
		int currentChar = 0;
		for (int i = 1; i <= dataTrans.length; i++) {
			currentChar = dataTrans[i-1]; //�õ�һ���ַ�
			if (currentChar > 47 && currentChar < 56) { //���ּ�����
				width[0] += 1.0;
				width[1] += 2.0;
			} else if (currentChar == 45) { //���ӺŴ�����ʱ��
				width[0] += 1.0;
				width[1] += 2.0;
			} else if (currentChar == 95) { //�»��ߴ�����ʱ��
				width[1] -= 1.0;
			} else if (currentChar == 61) { //�ȺŴ���˫��ʱ��
				width[1] -= 2.0;
			} else if (currentChar == 47) { //б�ߴ�������ʱ��
				width[1] -= 2.0;
			} else if (currentChar == 92) { //��б�ߴ����ļ�ʱ��
				width[1] -= 2.0;
			} else if (currentChar == 46) { //��Ŵ�����
				width[0] += 0.5;
				width[1] += 0.5;
			}
		}
		return width;		
	}
	
	public static int[] lineBegin(double paperWidth, double fontSize, double[][] width) {
		int[] lineStart;
		double maxUnit = paperWidth * 4 / fontSize;
		double unitThisLine = 0.0;
		lineStart = new int[width.length];
		int a = 1;
		lineStart[0] = 0;
		for(int i=1; i <= width.length-1; i++) {
			unitThisLine += (width[i-1][0] + width[i-1][1] / 2);
			if((unitThisLine + (width[i][0] + width[i][1] / 2)) > maxUnit - 1.0) {
				lineStart[a] = i;
				a++;
				unitThisLine = 0;
			} else {
				unitThisLine += 1.5;
			}
		}
		int[] lineStartTrans = new int[a];
		for(int i=0; i<a; i++) {
			lineStartTrans[i] = lineStart[i];
		}
		return lineStartTrans;
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
