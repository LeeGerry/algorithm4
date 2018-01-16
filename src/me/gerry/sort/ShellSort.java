package me.gerry.sort;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdRandom;

public class ShellSort<T> extends Sort<T> {
	@Override
	public void sort(Comparable<T>[] a) {
		int n = a.length;
		int h = 1;
		while (h < n / 3)
			h = 3 * h + 1;
		while (h >= 1) {
			for (int i = h; i < n; i++) {
				for (int j = i; j >= h && less(a[j], a[j - h]); j -= h){
					System.out.println("change ["+j +"]:"+a[j]+ " and ["+(j-h)+"]:"+a[j-h]);
					exchange(a, j, j - h);
					System.out.println(Arrays.toString(a));
				}
					
			}
			h = h / 3;
		}
	}

	public static void main(String[] args) {
		int n = 20;
		Integer[] items = new Integer[n];
		for (int i = 0; i < n; i++) {
			items[i] = (int) (StdRandom.uniform() * 100);
		}
		StdRandom.shuffle(items);
		System.out.println(Arrays.toString(items));
		Sort<Integer> ss = new ShellSort<>();
		ss.sort(items);
		System.out.println(Arrays.toString(items));
	}
}
