package me.gerry.ch2;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdRandom;

public class Shell {
	public static void sort(Comparable[] a) {
		int len = a.length;
		int h = 1;
		while (h < len / 3)
			h = 3 * h + 1;
		while (h >= 1) {
			for (int i = h; i < len; i++) {
				for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
					exchange(a, j, j - h);
				}
			}
			h = h / 3;
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
		Shell.sort(s);
		System.out.println(Arrays.toString(s));
		Double[] d = new Double[10];
		for (int i = 0; i < d.length; i++) {
			d[i] = StdRandom.uniform();
		}
		System.out.println(Arrays.toString(d));
		Shell.sort(d);
		System.out.println(Arrays.toString(d));
	}
}
