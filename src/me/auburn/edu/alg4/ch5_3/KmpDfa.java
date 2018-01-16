package me.auburn.edu.alg4.ch5_3;

public class KmpDfa {
	private String pattern;
	private int[][] dfa;
	public KmpDfa(String pattern){
		this.pattern = pattern;
		int M = pattern.length();
		int R = 256;
		dfa = new int[R][M];
		dfa[pattern.charAt(0)][0] = 1;
		for(int X = 0, j = 1; j < M; j++){
			for(int c = 0; c < R; c++){
				dfa[c][j] = dfa[c][X];
			}
			dfa[pattern.charAt(j)][j] = j + 1;
			X = dfa[pattern.charAt(j)][X];
		}
	}
	public int search(String txt){
		int i, j, N = txt.length(), M = pattern.length();
		for(i = 0, j=0; i< N && j < M; i++){
			j = dfa[txt.charAt(i)][j];
		}
		if(j == M) return i - M;
		else return N;
	}
	public static void main(String[] args) {
		String pattern = "AACAA";
		String txt = "AABRAACADABRAACAADABRA";
		int pos = new KmpDfa(pattern).search(txt);
		System.out.println(txt);
		for(int i = 0; i<pos;i++){
			System.out.print(" ");
		}
		System.out.println(pattern);
	}
}
