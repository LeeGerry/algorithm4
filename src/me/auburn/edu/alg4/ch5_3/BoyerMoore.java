package me.auburn.edu.alg4.ch5_3;

public class BoyerMoore {
	private int[] right;
	private String pat;

	public BoyerMoore(String pat) {
		this.pat = pat;
		int M = pat.length();
		int R = 256;
		right = new int[R];
		for (int i = 0; i < R; i++)
			right[i] = -1;
		for (int j = 0; j < M; j++) {
			right[pat.charAt(j)] = j;
		}
	}

	public int search(String txt) {
		int N = txt.length();
		int M = pat.length();
		int skip = 0;
		for(int i = 0; i<=N-M; i+= skip){
			skip = 0;
			for(int j = M-1; j>=0; j--){
				if(pat.charAt(j) != txt.charAt(i+j)){
					skip = j - right[txt.charAt(i+j)];
					if(skip < 1) skip = 1;
					break;
				}
			}
			if(skip == 0) return i;
		}
		return -1;
	}

	public static void main(String[] args) {
		String p = "examplel";
		String txt = "this is an exam, not an example";
		int index = new BoyerMoore(p).search(txt);
		System.out.println(index);
	}
}
