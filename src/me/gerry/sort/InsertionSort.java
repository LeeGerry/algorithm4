package me.gerry.sort;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class InsertionSort<T> extends Sort<T> {
	@Override
	public void sort(Comparable<T>[] a) {
		int len = a.length;
		for (int i = 1; i < len; i++) {
			// 把a[i] 插入到a[i-1], a[i-2], a[i-3]...中
			// j从i开始，依次向前找，找到要插入的位置，停止，进行插入
			for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
				exchange(a, j, j-1);
//				System.out.println(Arrays.toString(a));
			}
		}
	}
	public static void main(String[] args) {
		int n = 500000;
		Integer[] items = new Integer[n];
		for (int i = 0; i < n; i++) {
			items[i] = (int)(StdRandom.uniform()*1000);
		}
		StdRandom.shuffle(items);
		Integer[] items1 = Arrays.copyOf(items, items.length);
		SelectionSort<Integer> ss = new SelectionSort<>();
		InsertionSort<Integer> is = new InsertionSort<>();
		Stopwatch s = new Stopwatch();
		is.test(items);
		double time = s.elapsedTime();
		System.out.println("插入排序："+time);
		double time1 = s.elapsedTime();
		ss.test(items1);
		System.out.println("选择排序："+time1);
		System.out.println(time1/time);
	}
}
