package ch2.sub2;

import java.io.File;

import ch1.interf.Sort;
import edu.princeton.cs.algs4.In;
/**
 * 自底向上的归并排序
 */
public class MergeBU extends Sort{
	private static Comparable[] aux;	// 归并所需要的辅助数组
	public void merge(Comparable[] a, int lo, int mid, int hi) {
		// 将a[lo..mid] 和 a[mid+1..hi]归并
		int i = lo, j = mid + 1;	//i是前半部分的当前索引；j是后半部分的当前索引
		for (int k = lo; k <= hi; k++)//遍历数组，拷贝一份存入辅助数组
			aux[k] = a[k];
		for (int k = lo; k <= hi; k++) {//遍历从lo 到 hi，更新原数组中的第k个位置
			if (i > mid)		// 如果前半部分的当前索引已经超出mid，说明前半部分已经排好，只需把后半部分依次放入即可
				a[k] = aux[j++]; // 依次放入后半部分的元素
			else if (j > hi)	// 如果后半部分的当前索引超出hi，说明后半部分已经排好，只需把前半部分依次放入
				a[k] = aux[i++];// 依次放入前半部分的元素
			else if (less(aux[j], aux[i]))//以上两者都不是，则需要在前后两部分中选取一个当前比较小的，去放入a数组
				a[k] = aux[j++];//后面的j处元素小，就取该值放入a，并把j指针后移
			else
				a[k] = aux[i++];//前面的i处元素小，就取该值放入a，并把i指针后移
		}
	}
	
	public void sort(Comparable[] a, int lo, int hi) {
		int n = hi;
		aux = new Comparable[n]; // 一次性分配空间
		for(int sz = 1; sz < n; sz += sz){
			for(int i = 0; i < n-sz; i+= sz+sz)
				merge(a, i, i+sz-1, Math.min(i+sz+sz-1, n-1));
		}
		
	}

	public static void main(String[] args) {
		new MergeBU().test();
	}
}
