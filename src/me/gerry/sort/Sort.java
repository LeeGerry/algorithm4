package me.gerry.sort;

import java.util.Arrays;

public abstract class Sort<T> {
	public abstract void sort(Comparable<T>[] a);
	protected void exchange(Comparable<T>[] a, int i, int min) {
		Comparable<T> t = a[i];
		a[i] = a[min];
		a[min] = t;
	}

	@SuppressWarnings("unchecked")
	protected boolean less(Comparable<T> a, Comparable<T> b) {
		return a.compareTo((T) b) < 0;
	}

	protected boolean isSorted(Comparable<T>[] a){
		for(int i=1;i<a.length;i++)
			if(less(a[i], a[i-1]))	
				return false;
		return true;
	}
	protected void test(Comparable<T>[] items){
//		System.out.println(Arrays.toString(items));
		System.out.println(isSorted(items));
		sort(items);
		System.out.println(isSorted(items));
//		System.out.println(Arrays.toString(items));
	}
}
