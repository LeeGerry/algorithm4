package me.auburn.edu.alg4.ch5_0;

import java.io.File;

import edu.princeton.cs.algs4.Alphabet;
import edu.princeton.cs.algs4.In;

public class Count {
	public static void main(String[] args) {
		String dir = Count.class.getPackage().getName().replace(".", "/");
		String path = Count.class.getClassLoader().getResource(dir+"/abra.txt").getPath();
		File f = new File(path);
		In in = new In(f);
		String input = in.readAll();
		String st = "ABCDR";
		Alphabet alpha = new Alphabet(st);
		int R = alpha.radix();
		int[] count = new int[R];
		int n = input.length();
		for(int i=0; i<n; i++)
			if(alpha.contains(input.charAt(i)))
				count[alpha.toIndex(input.charAt(i))]++;
		for(int c = 0; c<R; c++)
			System.out.println(alpha.toChar(c) + " " + count[c]);
	}
}
