package me.gerry.sort;

import edu.princeton.cs.algs4.StdRandom;

public class SelectionSort<T> extends Sort<T> {
	@Override
	public void sort(Comparable<T>[] a) {
		int n = a.length;
		for (int i = 0; i < n; i++) {
			int min = i;
			for (int j = i + 1; j < n; j++)
				if (less(a[j], a[min]))
					min = j;
			exchange(a, i, min);
		}
	}

	public static void main(String[] args) {
		int n = 10;
		Integer[] items = new Integer[n];
		for (int i = 0; i < n; i++) {
			items[i] = (int)(StdRandom.uniform()*1000);
		}
		SelectionSort<Integer> ss = new SelectionSort<>();
		ss.test(items);
	}
}
