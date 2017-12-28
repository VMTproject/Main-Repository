package analysis;

public class StringStandardize {
	public static String allTrim(String raw) {
		String a = raw;
		a = a.trim();
		a = a.replaceAll("\r\n", "\n");//Windows
		a = a.replaceAll("\r", "\n");//Unix
		a = a.replaceAll("\u00A0", " ");//NBSP
		a = a.replaceAll("\u3000", " ");//全角空格
		a = a.replaceAll(" +", " ");//多个空格
		return a;
	}
	
	public static String wholeWidth(String raw) {
		String a;
		char[] b = raw.toCharArray();
		for(int i = 0; i < b.length; i++) {
			if(b[i] > 0xFF00 && b[i] < 0xFF5F//ȫ��ASCII��Χ
					&& b[i] != 0xFF01//����̾��
					&& b[i] != 0xFF08 && b[i] != 0xFF09//Բ����
					&& b[i] != 0xFF0C//����
					&& b[i] != 0xFF0E//�Ƽ����
					&& b[i] != 0xFF1A//ð��
					&& b[i] != 0xFF1B//�ֺ�
					&& b[i] != 0xFF1F//�ʺ�
					&& b[i] != 0xFF3B && b[i] != 0xFF3D//������
					&& b[i] != 0xFF5E//���˺�
					) {
				b[i] = (char) (b[i] + 0x0020 - 0xFF00);
			} else if(b[i] == 0xFFE0) {
				b[i] = '\u00A2';
			} else if(b[i] == 0xFFE1) {
				b[i] = '\u00A3';
			} else if(b[i] == 0xFFE2) {
				b[i] = '\u00AC';
			} else if(b[i] == 0xFFE4) {
				b[i] = '|';
			} else if(b[i] == 0xFFE5) {
				b[i] = '\u00A5';
			}
		}
		a = b.toString();
		return a;
	}
}
