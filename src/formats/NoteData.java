package formats;

public class NoteData {
	byte category;//��������
	byte code;//һ�ֽڴ���
	byte supplementary;//����
	//��������˵,XXX���¼��ߣ�XX�����㣩X��0��1�ͣ�XX���ӵ㣩
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
