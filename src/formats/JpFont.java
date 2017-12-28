package formats;

public class JpFont {
	public String chineseFont;//GBK/BIG5汉字字体
	public String latinFont;//ISO-8859-1字体
	public String westernFont;//ISO-8859-1以外西文字体
	
	public JpFont(String font) {
		this.chineseFont = font;
		this.latinFont = font;
		this.westernFont = font;
	}
	
	public JpFont(String chineseFont, String latinFont) { //ISO-8859-1和其他西文使用相同字体
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
