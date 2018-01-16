package me.gerry.ch3;

import java.util.Arrays;

import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.StdRandom;

public class Merge {
	private static Comparable[] aux;
	private static int CUTOFF = 7;
	private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
		assert isSorted(a, lo, mid);
		assert isSorted(a, mid + 1, hi);
		for (int k = lo; k <= hi; k++)
			aux[k] = a[k];

		int i = lo, j = mid + 1;
		for (int k = lo; k <= hi; k++) {
			if (i > mid)
				a[k] = aux[j++];
			else if (j > hi)
				a[k] = aux[i++];
			else if (less(aux[j], aux[i]))
				a[k] = aux[j++];
			else
				a[k] = aux[i++];
		}
		assert isSorted(a, lo, hi);
	}

	private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
		if (hi <= lo)
			return;
		/**  use insertion sort for small subarrays*/
		if(hi <= lo + CUTOFF - 1){
			Insertion.sort(a,lo,hi);
		}
		
		int mid = lo + (hi - lo) / 2;
		sort(a, aux, lo, mid);
		sort(a, aux, mid + 1, hi);
		/** stop if already sorted. */
		if(!less(a[mid+1], a[mid])) 	return;
		merge(a, aux, lo, mid, hi);
	}

	public static void sort(Comparable[] a) {
		aux = new Comparable[a.length];
		sort(a, aux, 0, a.length - 1);
	}

	private static boolean less(Comparable a, Comparable b) {
		return a.compareTo(b) < 0;
	}

	private static boolean isSorted(Comparable[] a, int lo, int mid) {
		for (int i = 1; i < a.length; i++)
			if (less(a[i], a[i - 1]))
				return false;
		return true;
	}
	public static void main(String[] args) {
		Integer[] d = new Integer[10];
		for (int i = 0; i < d.length; i++) {
			d[i] = (int)(StdRandom.uniform()*100);
		}
		System.out.println(Arrays.toString(d));
		sort(d);
		System.out.println(Arrays.toString(d));
	}
}
