package me.auburn.edu.alg4.ch5_0;

import java.io.File;

import edu.princeton.cs.algs4.In;

public class Count2 {
	public static void main(String[] args) {
		String dir = Count2.class.getPackage().getName().replace(".", "/");
		String path = Count2.class.getClassLoader().getResource(dir+"/pi.txt").getPath();
		File f = new File(path);
		In in = new In(f);
		String st = "0123456789";
		String input = in.readAll();
		Alphabet alpha = new Alphabet(st);
		int[] a = alpha.toIndices(input);
		int n = input.length();
		int R = alpha.radix();
		int[] count = new int[R];
		for(int i = 0; i<n; i++){
			count[a[i]]++;
		}
		for(int c = 0; c < R; c++){
			System.out.println(alpha.toChar(c) + " "+ count[c]);
		}
	}
}
