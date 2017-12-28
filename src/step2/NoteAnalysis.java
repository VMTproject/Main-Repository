package step2;

import formats.NoteData;

public class NoteAnalysis {
	public static void getPrimary(String raw) {
		byte[] rawByte = raw.getBytes();
		int a = 0;
		for(int i=0; i < rawByte.length; i++) {
			int b = rawByte[i];
			if((b > 47 && b < 58) || b == 124) {
				a++;
			}
		}
		NoteData[] data = new NoteData[a];
		for(int i=0; i < rawByte.length; i++) {
			byte b = rawByte[i];
			int c = 0;
			if(b > 47 && b < 58) {
				data[c] = new NoteData();
				data[c].setNote(b);
				data[c].print();
				c++;
			} else if(b == 124) {
				data[c] = new NoteData();
				data[c].setBarLine((byte) 0);
				data[c].print();
				c++;
			}
		}
	}
}
