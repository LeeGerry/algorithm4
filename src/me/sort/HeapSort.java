package me.sort;

import java.util.Arrays;
/**
 * 堆排序
 */
public class HeapSort {
	public static void sort(Comparable[] a){
		int n = a.length - 1; // index=0的位置不使用, n是最后一个index
		buildHeap(a, n);
		while(n>1){
			exchange(a,1,n--);
			sink(a,1,n);
		}
	}
	/**
	 * 构造堆
	 */
	private static void buildHeap(Comparable[] a, int n) {
		for(int k = n/2; k>=1; k--)	
			sink(a, k, n);
	}

	private static void exchange(Comparable[] a, int i, int j) {
		Comparable temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	private static void sink(Comparable[] a, int k, int n) {
		while(2*k <= n){
			int j = 2*k;
			if(j<n && less(a,j,j+1)) j++;
			if(!less(a,k,j))	break;
			exchange(a,k,j);
			k = j;
		}
	}
	private static boolean less(Comparable[] a, int i, int j){
		return a[i].compareTo(a[j])<0;
	}
	public static void main(String[] args) {
		// index=0的位置不使用
        String[] strings = { " ", "s","o", "r", "t", "e", "x", "a", "m", "p", "l", "e" };
        sort(strings);
        System.out.println(Arrays.toString(strings));
	}
}
