package me.gerry.ch2;

import java.io.File;
import java.util.Arrays;

import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Experiment {
	public static void main(String[] args) {
		File dir = new File("/Users/liguorui/Desktop/bootstrap");
		File[] files = dir.listFiles();
		for(File f: files){
			System.out.println(f.getName());
		}
		System.out.println("********");
		Insertion.sort(files);
		for(File f: files){
			System.out.println(f.getName());
		}
	}

	private static void sortString() {
		String[] s = {"ab","de","ae","ba","Not", "Chinese", "chain"};
		System.out.println(Arrays.toString(s));
		Insertion.sort(s);
		System.out.println(Arrays.toString(s));
	}

	private static void sorDouble() {
		Double[] d = new Double[10];
		for (int i = 0; i < d.length; i++) {
			d[i] = StdRandom.uniform();
		}
		Insertion.sort(d);
		for(Double dou: d){
			StdOut.println(dou);
		}
	}
}
