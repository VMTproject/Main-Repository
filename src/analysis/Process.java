package analysis;

public class Process {
	public void processByByte(String raw) {
		int l = raw.length();
		boolean isPaperDefined = false;
		boolean isFontDefined = false;
		char currentChar;
		for(int i = 0; i < l; i++) {
			currentChar = raw.charAt(i);
			if(currentChar == '\\') {
				//�����������ȡ
			} else {
				if(!isPaperDefined) { //��ֽ��δ����
					System.out.println("ֽ������δ���壬ʹ��Ĭ��ֵ��A4ֽ��210 �� 297�������ܱ߾�20����");
				}
				
				if(!isFontDefined) {
					System.out.println("����δ���壬ʹ��Ĭ��ֵ��������+3����������+1��������ͨ�����Ρ�0���ױ�˵����-1");
				}
			}
		}
	}
}
