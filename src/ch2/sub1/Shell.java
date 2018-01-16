package ch2.sub1;

import ch1.interf.Sort;

/**
 * 希尔排序
 */
public class Shell extends Sort{
	public void sort(Comparable[] a,int lo, int hi) {
		int n = hi;
		int h = 1;
		while (h < n / 3)
			h = 3 * h + 1;
		while (h >= 1) {
			// 将数组变为h有序
			for (int i = h; i < n; i++) {
				// 将a[i]插入到a[i-h], a[i - 2*h], a[i - 3*h]...之中
				for (int j = i; j >= h && less(a[j], a[j - h]); j -= h)
					exch(a, j, j - h);
			}

			h = h / 3;
		}
	}
	public static void main(String[] args) {
		new Shell().test();
	}
}
