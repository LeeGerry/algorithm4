package me.auburn.edu.alg4.ch5_1;

import java.util.Arrays;
/**
 * LSD: 低位优先的字符串排序算法
 */
public class LSDSort {
	/** 通过前w个字符将a[]排序*/
	public static void sort(String[] a, int w){
		int n = a.length;
		int R = 256;
		String[] aux = new String[n]; // 辅助数组
		// 根据第d个字符用键索引计数法排序
		for(int d = w-1; d >= 0; d--){
			int[] count = new int[R+1];	
			// 计算出现频率
			for(int i = 0; i < n; i++)
				count[a[i].charAt(d) + 1]++;
			// 将频率转换为索引
			for(int r = 0; r < R; r++)
				count[r+1] += count[r];
			// 将元素分类
			for(int i = 0; i < n; i++)
				aux[count[a[i].charAt(d)]++] = a[i];
			// 回写
			for(int i = 0; i < n; i++)
				a[i] = aux[i];
			System.out.print("对于第"+d+"个索引排序后结果:");
			System.out.println(Arrays.toString(a));
			System.out.println(Arrays.toString(count));
		}
	}
	public static void main(String[] args) {
		String[] inputs = {"4PGC938","2IYE230","3CIO720","1ICK750","1OHV845",
				"4JZY524","1ICK750","3CIO720","1OHV845",
				"1OHV845","2RLA629","2RLA629","3ATW723"};
		System.out.println("最初:"+Arrays.toString(inputs));
		sort(inputs, 7);
		System.out.println("最终:"+Arrays.toString(inputs));
	}
}
