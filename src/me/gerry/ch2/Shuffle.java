package me.gerry.ch2;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdRandom;

public class Shuffle {
	public static void shuffle(Comparable[] a) {
		int n = a.length;
		for(int i = 0;i<n;i++){
			int r = StdRandom.uniform(i+1);
			exchange(a, i, r);
		}
	}

	private static void exchange(Comparable[] a, int i, int r) {
		Comparable temp = a[i];
		a[i] = a[r];
		a[r] = temp;
	}
	public static void main(String[] args) {
		Integer[] arr = {1,2,3,4,5,6,7,8,9};
		Shuffle.shuffle(arr);
		System.out.println(Arrays.toString(arr));
	}
}
