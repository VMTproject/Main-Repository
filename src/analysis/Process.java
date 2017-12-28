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
				//视作命令或块读取
			} else {
				if(!isPaperDefined) { //若纸张未定义
					System.out.println("纸张数据未定义，使用默认值：A4纸（210 × 297）、四周边距20毫米");
				}
				
				if(!isFontDefined) {
					System.out.println("字体未定义，使用默认值：标题宋+3、副标题宋+1、其余普通文字宋±0、谱表说明宋-1");
				}
			}
		}
	}
}
