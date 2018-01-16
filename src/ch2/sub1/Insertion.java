package ch2.sub1;

import ch1.interf.Sort;

/**
 * 插入排序排序
 */
public class Insertion extends Sort{
	public void sort(Comparable[] a, int lo, int hi) {
		int n = hi;
		for (int i = 1; i < n; i++) {
			//将a[i]插入到a[i-1],a[i-2]...之中
			for (int j = i; j>0 && less(a[j], a[j-1]); j--) {
				exch(a, j, j-1);
			}
		}
	}
	public static void main(String[] args) {
		new Insertion().test();
	}
}
