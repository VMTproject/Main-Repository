package formats;

public class NoteData {
	byte category;//符号类型
	byte code;//一字节代码
	byte supplementary;//附加
	//对音符来说,XXX（下加线）XX（附点）X（0高1低）XX（加点）
	public NoteData() {
		category = 0;
		code = 0;
	}
	
	public void setNote(byte asciiCode) {
		category = 0;
		code = asciiCode;
		supplementary = 0;
	}
	
	public void setBarLine(int code) {
		category = 1;
		this.code = (byte)code;
	}
	
	public void setUnderline(int number) {
		supplementary = (byte)((supplementary & 0x1F) + number * 32);
	}
	
	public void print() {
		if(category == 0) {
			System.out.print((char) code);
			System.out.print(" ");
		} else {
			System.out.print(" |\n");
		}
	}
}
