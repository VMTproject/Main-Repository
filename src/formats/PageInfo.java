package formats;

public class PageInfo {
	public int pageWidth;
	public int pageHeight;
	public int topMargin;
	public int leftMargin;
	public int bottomMargin;
	public int rightMargin;
	public int titleSize;
	public int subtitleSize;
	public int headerSize;
	public int noteSize;
	
	public PageInfo() {
		pageWidth = 8400;
		pageHeight = 11880;
		topMargin = 1000;
		leftMargin = 800;
		bottomMargin = 1000;
		rightMargin = 800;
		titleSize = 250;
		subtitleSize = 200;
		headerSize = 150;
		noteSize = 130;
	}
	
	public void setPageSize(int width, int height) {
		this.pageWidth = width;
		this.pageHeight = height;
	}
	
	public void setMargin(int top, int left, int bottom, int right) {
		this.topMargin = top;
		this.leftMargin = left;
		this.bottomMargin = bottom;
		this.rightMargin = right;
	}
	
	public void setFontSize(int title, int subtitle, int header, int note) {
		this.titleSize = title;
		this.subtitleSize = subtitle;
		this.headerSize = header;
		this.noteSize = note;
	}
}
