package formats;

public class Tokens {
	public static final int TEXT = 0;
	public static final int COMMAND = 1;
	public static final int BEGIN = 2; //�鿪ʼ���ḽ����
	public static final int END = 3;
	public static final int LINE_BREAK = 4;
	public static final int SPACE = 5;
	public static final int COMMENT = 6;
	public static final int NUMBER = 6;
	
	public int tokenCat; //���
	public String tokenInf;
	
	public Tokens(int category, String information) {
		tokenCat = category;
		tokenInf = information;
	}
}
