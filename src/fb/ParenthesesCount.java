package fb;
/**
 * 括号匹配问题：给定一个字符串全部是左右括号，计算需要加多少个左右括号才能完全匹配
 * 比如给出")("返回2，需要1个左括号和一个右括号
 */
public class ParenthesesCount {
	public static int getInvalidCount(String str){
		int right = 0, left = 0;
		for(int i=0; i<str.length(); i++){
			char c = str.charAt(i);
			if(c == '(')	left++;
			else if(c == ')'){
				if(right != 0) left--;
				else			right++;
			}
		}
		System.out.println(left+","+right);
		return left+right;
	}
	public static void main(String[] args) {
		String s = ")()(()((";
		String s1 = ")(";
		getInvalidCount(s);
	}
}
