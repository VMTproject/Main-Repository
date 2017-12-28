package analysis.tokenize;

import java.util.ArrayList;

import analysis.tokenize.CharacterType;
import formats.Tokens;

public class Scanner {
	public static void tokenize (String original) {
		int currentMode = Tokens.TEXT;
		String currentString = "";
		ArrayList<Tokens> list = new ArrayList<Tokens> ();
		int a = original.length();//字符串长度
		
		char current;
		for(int i = 0; i < a; i++) {
			current = original.charAt(i); //选取当前字符
			int b = CharacterType.returnCharType(current);//字符类型
			switch(b) {
			
			case CharacterType.ESCAPE://命令或转义字符
				if(currentMode != Tokens.COMMENT) {
					if(CharacterType.returnCharType(original.charAt(i+1)) == CharacterType.LETTER) {
						//命令
						addToken(list, currentMode, currentString);
						currentString = "";
						currentMode = Tokens.COMMAND;
					} else if(CharacterType.returnCharType(original.charAt(i+1)) == CharacterType.SPACE) {
						//空格断词
						addToken(list, currentMode, currentString);
						currentString = "";
						currentMode = Tokens.TEXT;
						i += 1;//跳过下一个字符
					} else {
						//转义字符
						if(currentMode != Tokens.TEXT) {
							addToken(list, currentMode, currentString);
							currentString = "";
							currentMode = Tokens.TEXT;
						}
						currentString = currentString + judgeEscapeSeq(original.charAt(i+1));
						i += 1;//跳过下一个字符
					}
				}
				break;
			
			case CharacterType.BEGIN_OF_GROUP://it is the beginning of a group
				if(currentMode != Tokens.COMMENT) {
					addToken(list, currentMode, currentString);
					currentString = "";
					addToken(list, Tokens.BEGIN, "哈哈");
					currentMode = Tokens.TEXT;
				}
				break;
				
			case CharacterType.END_OF_GROUP:
				if(currentMode != Tokens.COMMENT) {
					addToken(list, currentMode, currentString);
					currentString = "";
					addToken(list, Tokens.END, "呜呜");
					currentMode = Tokens.TEXT;
				}
				break;
				
			case CharacterType.LINE_END:
				if(currentMode != Tokens.COMMENT) {
					addToken(list, currentMode, currentString);
				}
				currentString = "";
				addToken(list, Tokens.LINE_BREAK, "哇!");
				currentMode = Tokens.TEXT;
				break;
				
			case CharacterType.SPACE:
				if(currentMode != Tokens.COMMENT) {
					addToken(list, currentMode, currentString);
					currentString = "";
					currentMode = Tokens.TEXT;
				}
				break;
				
			case CharacterType.LETTER:
				currentString += current;
				break;
				
			case CharacterType.OTHER:
				if(currentMode == Tokens.COMMAND) {
					addToken(list, currentMode, currentString);
					currentString = "";
					currentMode = Tokens.TEXT;
				} else {
					currentString += current;
				}
				break;
			
			case CharacterType.COMMENT:
				if(currentMode != Tokens.COMMENT) {
					addToken(list, currentMode, currentString);
					currentString = "";
				}
				currentMode = Tokens.COMMENT;
				break;
			}
		}
		addToken(list, currentMode, currentString);
		
		for(int i=0; i < list.size(); i++) {
			Tokens currentToken = list.get(i);
			
			switch(currentToken.tokenCat) {
			
			case Tokens.TEXT:
				System.out.println("TOKEN TEXT    "+currentToken.tokenInf);
				break;
				
			case Tokens.COMMAND:
				System.out.println("TOKEN COMMAND "+currentToken.tokenInf);
				break;
				
			case Tokens.BEGIN:
				System.out.println("BEGIN OF GROUP");
				break;
				
			case Tokens.END:
				System.out.println("END OF GROUP");
				break;
				
			case Tokens.LINE_BREAK:
				System.out.println("LINE BREAK");
				break;
				
			case Tokens.NUMBER:
				System.out.println("TOKEN NUMBER  "+currentToken.tokenInf);
				break;
			}
		}
	}
	
	public static char judgeEscapeSeq(char input) {
		char a = 0x00;
		if(input == '\\' || input == '{' || input == '}' || input == '%') {
			a = input;
		}
		return a;
	}
	
	public static void addToken(ArrayList<Tokens> obj, int tokenType, String item) {
		if(!(item.equals(""))) {
			switch(tokenType) {
			
			case Tokens.TEXT:
				if(isNumber(item))
					obj.add(new Tokens(Tokens.NUMBER, item));
				else
					obj.add(new Tokens(Tokens.TEXT, item));
				break;
			case Tokens.COMMAND:
				obj.add(new Tokens(Tokens.COMMAND, item));
				break;
				
			case Tokens.BEGIN:
				obj.add(new Tokens(Tokens.BEGIN, ""));
				break;
				
			case Tokens.END:
				obj.add(new Tokens(Tokens.END, ""));
				break;
				
			case Tokens.LINE_BREAK:
				obj.add(new Tokens(Tokens.LINE_BREAK, ""));
				break;
			}
		}
	}
	
	public static boolean isNumber(String arg) {
		String trans = arg.replaceAll(",", ".");
		char first = trans.charAt(0);//第一项
		if(!((first >= '0' && first <= '9') || first == '+' || first == '-'
				|| first == '.')) {//不符合要求
			return false;
		}
		boolean isFloat = false;
		for(int i = 1; i < trans.length(); i++) {//每个字符考察
			char current = trans.charAt(i);//当前字符
			if(current >= '0' && current <= '9') {}
			else if(current == '.') {
				if(isFloat) 
					return false;
				else
					isFloat = true;
			} else
				return false;
		}
		return true;
	}
}