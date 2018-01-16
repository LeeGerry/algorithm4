package me.gerry.ch2;

import java.util.Arrays;

import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.StdRandom;

public class Selection {
	public static void sort(Comparable[] a){
		int n = a.length;
		for(int i=0;i<n;i++){
			int min = i;
			for(int j = i+1;j<n;j++){
				if(less(a[j], a[min]))	min = j;
			}
			exchange(a, i, min);
		}
	}

	private static void exchange(Comparable[] a, int i, int min) {
		Comparable tem = a[i];
		a[i] = a[min];
		a[min] = tem;
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
