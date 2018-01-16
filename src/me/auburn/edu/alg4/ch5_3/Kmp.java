package me.auburn.edu.alg4.ch5_3;

public class Kmp {
	public static int kmp(String text, String pattern) {
		int tLen = text.length(), pLen = pattern.length();
		int i = 0, j = 0;
		int[] next = buildNextArray(pattern);
		while (i < tLen && j < pLen) {
			if (j == -1 || text.charAt(i) == pattern.charAt(j)) {
				i++;
				j++;
			} else {
				j = next[j];
			}
		}
		return j == pLen ? i - j : -1;
	}
	public static int[] buildNextArray(String pattern){
		int pLen = pattern.length();
		int[] next = new int[pLen];
		next[0] = -1;
		int k = -1, j = 0;
		while(j < pLen - 1){
			if(k == -1 || pattern.charAt(j) == pattern.charAt(k)){
				k++;
				j++;
				next[j] = k;
			}else{
				k = next[k];
			}
		}
		return next;
	}
	public static int[] buildNextArrayOptimized(String pattern){
		int pLen = pattern.length();
		int[] next = new int[pLen];
		next[0] = -1;
		int k = -1, j = 0;
		while(j < pLen - 1){
			if(k == -1 || pattern.charAt(j) == pattern.charAt(k)){
				k++;
				j++;
				if(pattern.charAt(j)!= pattern.charAt(k))
					next[j] = k;
				else
					next[j] = next[k];
			}else{
				k = next[k];
			}
		}
		return next;
	}
	public static void main(String[] args) {
		String text = "BBC ABCDAB ABCDABCDABDE";
		String pattern = "ABCDABD";
		System.out.println(kmp(text, pattern));
	}
}
