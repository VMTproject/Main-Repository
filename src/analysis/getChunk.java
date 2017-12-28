package analysis;

public class getChunk { //从文件中提取中各种区块
	public static String getBlockLocation(String original, String find) {
		String result = "";
		int primaryOffset = original.indexOf("\\" + find);
		String a = original.substring(primaryOffset);
		
		int b = a.indexOf('{'); //从第一个大括号开始
		
		for(int i = b + 1; i <= a.length(); i++) {
			result = a.substring(b + 1, i); //长度每次加1
			if(getLevel(a, i + 1) == 0) {
				break;
			}
		}
		
		return result.trim();
	}
	
	public static int getLevel(String original, int offset) {//计算某一点层次
		int a = 0;
		if(offset == 0) {
			return 0;
		}
		char[] part = original.substring(0, offset).toCharArray();
		
		for(int i = 0; i < part.length; i++) {
			if(part[i] == '{') {
				a++;
			} else if(part[i] == '}') {
				a--;
			}
		}
		return a;
	}
}
