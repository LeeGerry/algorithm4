package me.auburn.edu.alg4.ch5_3;

public class BruteForce {
	public static int brute(String text, String pattern) {
		int tLen = text.length();
		int pLen = pattern.length();
		int i = 0, j = 0;
		while (i < tLen && j < pLen) {
			if (text.charAt(i) == pattern.charAt(j)) {
				i++;
				j++;
			} else {
				i = i - j + 1;
				j = 0;
			}
		}
		return j == pLen ? i - j : -1;
	}
	public static void main(String[] args) {
		String text = "BBC ABCDAB ABCDABCDABDE";
		String pattern = "ABCDABD";
		System.out.println(brute(text, pattern));
	}
}
