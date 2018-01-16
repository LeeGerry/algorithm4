package ch2.sub3;

import ch1.interf.Sort;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 快速排序
 */
public class Quick extends Sort{
	private static int M = 5;
	public void sort(Comparable[] a, int lo, int hi) {
		StdRandom.shuffle(a); //消除对输入的依赖
		sortHelper(a, 0, a.length - 1);
	}

	public void sortHelper(Comparable[] a, int lo, int hi) {
		if (hi <= lo)
			return;
		int j = partition(a, lo, hi); // 切分
		sortHelper(a, lo, j - 1); // 将左半部分排序
		sortHelper(a, j + 1, hi); // 将右半部分排序
	}

	// 将数组切分为a[lo..j-1], a[j], a[j+1..hi]
	private int partition(Comparable[] a, int lo, int hi) {
		int i = lo, j = hi+1; // 左右扫描指针
		Comparable v = a[lo]; 	// 切分元素
		while(true){
			while(less(a[++i],v)) if(i==hi) break;//左指针向右移，直到找到大于切分元素；若移动到最右边停止
			while(less(v,a[--j])) if(j==lo) break;//右指针向左移，直到找到小于切分元素；若到最左边停止
			if(i>=j) break;//如果两个指针重合或者走过头，停止
			exch(a,i,j);//交换
		}
		exch(a, lo, j);//交换切分元素和j处的元素
		return j;//把切点index返回出去

	}

	public static void main(String[] args) {
		new Quick().test();
	}
}
