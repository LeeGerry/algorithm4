package ch1.interf;

import java.io.File;

import ch2.sub3.Quick;
import edu.princeton.cs.algs4.In;

public abstract class Sort {
	public void exch(Comparable[] a, int i, int j) {
		Comparable temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	public void show(Comparable[] a) {
		for (int i = 0; i < a.length; i++)
			System.out.print(a[i] + " ");
		System.out.println();
	}

	public boolean isSorted(Comparable[] a) {
		for (int i = 1; i < a.length; i++) {
			if (less(a[i], a[i - 1]))
				return false;
		}
		return true;
	}
	public abstract void sort(Comparable[] a, int lo, int hi) ;
	public boolean less(Comparable a, Comparable b) {
		return a.compareTo(b) < 0;
	}
	public void test(){
		String path = Sort.class.getClassLoader().getResource("ch1/interf/num.txt").getPath();
		In in = new In(new File(path));
		int[] a = in.readAllInts();
		Integer[] arr = new Integer[a.length];
		for (int i = 0; i < a.length; i++) {
			arr[i] = a[i];
		}
		show(arr);
		sort(arr,0,arr.length);
		assert isSorted(arr);
		show(arr);
	}
}
