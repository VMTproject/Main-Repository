package formats;

public class JpFont {
	public String chineseFont;//GBK/BIG5��������
	public String latinFont;//ISO-8859-1����
	public String westernFont;//ISO-8859-1������������
	
	public JpFont(String font) {
		this.chineseFont = font;
		this.latinFont = font;
		this.westernFont = font;
	}
	
	public JpFont(String chineseFont, String latinFont) { //ISO-8859-1����������ʹ����ͬ����
		this.chineseFont = chineseFont;
		this.latinFont = latinFont;
		this.westernFont = latinFont;
	}
	
	public JpFont(String chineseFont, String latinFont, String westernFont) {
		this.chineseFont = chineseFont;
		this.latinFont = latinFont;
		this.westernFont = westernFont;
	}
}
