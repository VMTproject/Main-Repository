package analysis.tokenize;

public class CharacterType {
	public static final int ESCAPE = 0;			//转义字符 (\)
	public static final int BEGIN_OF_GROUP = 1;	//组开始 ({)
	public static final int END_OF_GROUP = 2;	//组结束 (})
//	public static final int ALT_BEGIN = 3;		//�����鿪ʼ��<��
//	public static final int ALT_END = 4;		//�����������>��
	public static final int LINE_END = 5;		//换行(\r等)
//	public static final int SPACE = 6;			//�ո�\u0020, \u3000, etc��
//	public static final int LATIN_LETTER = 7;	//������ĸ
//	public static final int OTHER_LETTER = 8;	//��ʱ��ʹ��
	public static final int IGNORED = 9;			//忽略(\0等)
	public static final int SPACE = 10;			//空格
	public static final int LETTER = 11;			//拉丁字母
	public static final int OTHER = 12;			//其他
//	public static final int NORMAL = 13;		//����
	public static final int COMMENT = 14;		//注释字符(%等)
	public static final int INVALID = 15;		//非法字符(0x7F等)
//	public static final String ChinesePunctList = "�������������������������������������������������������������������ۣ�";
	
	public static int returnCharType(char processed) {
		int fin = OTHER;
		if(processed == '\\') {
			fin = ESCAPE;
		} else if(processed == '{') {
			fin = BEGIN_OF_GROUP;
		} else if(processed == '}') {
			fin = END_OF_GROUP;
//		} else if(processed == '<') {
//			fin = ALT_BEGIN;
//		} else if(processed == '>') {
//			fin = ALT_END;
		} else if(processed == '\n') {
			fin = LINE_END;
		} else if(processed == '\0') {
			fin = IGNORED;
		} else if(processed == ' ') {
			fin = SPACE;
		} else if((processed >= 'A' && processed <= 'Z') ||
				(processed >= 'a' && processed <= 'z')) {
			fin = LETTER;
			/*
		} else if((processed >= '��' && processed <= '��') ||
				(processed >= '��' && processed <= '��')) {
			fin = OTHER_LETTER;
		} else if(processed >= '0' && processed <= '9') {
			fin = DIGIT;
		} else if(ChinesePunctList.indexOf(processed) >= 0) {
			fin = CHINESE_PUNCT;
		} else if(processed >= '\u3400' && processed <= '\u9FEA') {
			fin = CHINESE_CHAR;
			*/
		} else if(processed == '%') {
			fin = COMMENT;
		} else if(processed == 0x7F) {
			fin = INVALID;
		}
		return fin;
	}
}