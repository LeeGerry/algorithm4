package me.auburn.edu.alg4.ch5_1;

import java.util.Arrays;

import edu.princeton.cs.algs4.Insertion;

/**
 * 高位优先的字符串排序
 */
public class MSDSort {
	private static int R = 256;		// 基数
	private static final int M = 15;// 小数组的切换阈值
	private static String[] aux;	// 数据分类的辅助数组
	private static int charAt(String s, int d){
		if(d < s.length())	return s.charAt(d);
		else	 return -1;
	}
	public static void sort(String[] a){
		int N = a.length;
		aux = new String[N];
		sort(a, 0, N-1, 0);
	}
	/** 以第d个字符为键将a[low]至a[high]排序*/
	private static void sort(String[] a, int low, int high, int d) {
//		if(high <= low + M){
//			Insertion.sort(a, low, high);
//			return ;
//		}
		if(high <= low)	return ;
		int[] count = new int[R+2]; // 计算频率
		for(int i = low; i<=high; i++)
			count[charAt(a[i], d)+2]++;
		
		for(int r = 0; r<R+1; r++)	// 将频率转换为索引
			count[r+1] += count[r];
		
		for(int i = low; i<= high; i++) // 数据分类
			aux[count[charAt(a[i], d)+1]++] = a[i];
		
		for(int i = low; i<=high; i++)	//回写
			a[i] = aux[i - low];
		
		for(int r = 0; r<R; r++)		// 递归的以每个字符为键进行排序
			sort(a, low+count[r], low + count[r+1] - 1, d+1);
	}
	
	public static void main(String[] args) {
		String[] inputs = {"she","sells","seashells","by","the",
				"sea","shore","the","shells",
				"she","shells","are","surely", "seashells"};
		System.out.println("最初:"+Arrays.toString(inputs));
		sort(inputs);
		System.out.println("最终:"+Arrays.toString(inputs));
	}
}
