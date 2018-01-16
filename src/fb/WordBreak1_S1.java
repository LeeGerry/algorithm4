package fb;

import java.util.ArrayList;

public class WordBreak1_S1 {
	public boolean wordBreak(String s, ArrayList<String> list){
		return wordBreakHelper(s, list, 0);//从0开始作为起始位置递归
	}
	private boolean wordBreakHelper(String s, ArrayList<String> list, int start) {
		if(start == s.length())	//如果递归查到最后，返回true
			return true;
		boolean result = false;
		for(int end = start+1; end<=s.length();end++)//从起点开始向后查
			if(list.contains(s.substring(start,end)))//如果发现一个单词在字典中
				result |= wordBreakHelper(s, list, end);//则从该单词的下一个字符位置开始作为起点继续递归，结果与result做或运算
		return result;
	}
	public static void main(String[] args) {
		String s = "heisastudentis";
		ArrayList<String> list = new ArrayList<>();
		list.add("student");list.add("he");list.add("a");list.add("is");list.add("wan");
		System.out.println(new WordBreak1_S1().wordBreak(s, list));
	}
}
