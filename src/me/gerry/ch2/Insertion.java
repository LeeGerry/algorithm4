package me.gerry.ch2;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdRandom;

public class Insertion {
	public static void sort(Comparable[] a) {
		int len = a.length;
		for (int i = 0; i < len; i++) {
			for (int j = i; j > 0; j--) {
				if (less(a[j], a[j - 1]))
					exchange(a, j, j - 1);
				else
					break;
			}
		}
	}

	private static void exchange(Comparable[] a, int j, int i) {
		Comparable temp = a[j];
		a[j] = a[i];
		a[i] = temp;
	}

	private static boolean less(Comparable a, Comparable b) {
		return a.compareTo(b) < 0;
	}
	public static void main(String[] args) {
		String[] s = {"abe","de","ae","ba","Not", "Chinese", "chain"};
		System.out.println(Arrays.toString(s));
		Selection.sort(s);
		System.out.println(Arrays.toString(s));
		
		Double[] d = new Double[10];
		for (int i = 0; i < d.length; i++) {
			d[i] = StdRandom.uniform();
		}
		System.out.println(Arrays.toString(d));
		Insertion.sort(d);
		System.out.println(Arrays.toString(d));
	}
}
