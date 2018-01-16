package me.sort;

import java.util.Arrays;

/**
 * 基数排序
 */
public class RadixSort {
	public static void sort(Integer[] array){
		sort(array, 10);
	}

	private static void sort(Integer[] array, int radix) {
		if(array == null || array.length == 0)	return;
		// 找最大最小值
		int min = array[0], max = array[0];
		for(int i = 1; i<array.length; i++){
			if(array[i] < min)		min = array[i];
			else if(array[i] > max)	max = array[i];
		}
		
		
		int exponent = 1;
		int off = max - min;
		// 对每一位进行计数排序
		while(off / exponent >= 1){
			countingSortByDigit(array, radix, exponent, min);
			exponent *= radix;
		}
	}

	private static void countingSortByDigit(Integer[] array, int radix, int exponent, int min) {
		int bucketIndex;
		int[] buckets = new int[radix];
		int[] output = new int[array.length];
		// 初始化桶
		for(int i=0; i<radix; i++)
			buckets[i] = 0;
		// 统计频率
		for(int i = 0; i<array.length; i++){
			bucketIndex = (int)(((array[i] - min) / exponent) % radix);
			buckets[bucketIndex]++;
		}
		// 统计
		for(int i = 1; i< radix; i++)
			buckets[i] += buckets[i-1];
		// 移动记录
		for(int i = array.length - 1; i>=0; i--){
			bucketIndex = (int)(((array[i] - min) / exponent) % radix);
			output[--buckets[bucketIndex]] = array[i];
		}
		// 拷贝回去
		for(int i =0; i<array.length;i++){
			array[i] = output[i];
		}
	}
	public static void main(String[] args) {
		Integer[] array = {312,213,43,4,52,32,3,88,101};
		sort(array);
		System.out.println(Arrays.toString(array));
	}
}
