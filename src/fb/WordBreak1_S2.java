package fb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class WordBreak1_S2 {
	public boolean wordBreak(String s, List<String> wordDict){
		int[] mem = new int[s.length()+1];
		Arrays.fill(mem, -1);
		return word_break(s, new HashSet<>(wordDict), 0, mem) == 1;
	}

	private int word_break(String s, HashSet<String> wordDict, int start, int[] mem) {
		if(mem[start] >= 0)
			return mem[start];
		int res = 0;
		if(start == s.length())
			res = 1;
		for(int end = start+1; end<=s.length(); end++)
			if(wordDict.contains(s.substring(start, end)))
				res |= word_break(s, wordDict, end, mem);
		mem[start] = res;
		System.out.println(Arrays.toString(mem));
		return res;
	}
	
	public static void main(String[] args) {
		String s = "heisastudentis";
		ArrayList<String> list = new ArrayList<>();
		list.add("student");list.add("he");list.add("a");list.add("is");list.add("wan");
		System.out.println(new WordBreak1_S2().wordBreak(s, list));
	}
}
