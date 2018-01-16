package me.sort;

import java.util.Arrays;

/**
 * 计数排序
 */
public class CountSort {
	public static int[] sort(int[] array){
		int[] result = new int[array.length];	// 存储结果
		int max = max(array);				// 找到待排序数组中的最大值max
		int[] temp = new int[max+1]; 		// 申请一个大小为max+1的辅助数组
		for(int i = 0; i<array.length;i++)	// 遍历待排序数组
			temp[array[i]] = temp[array[i]] + 1;	//以当前值作为索引，把辅助数组索引位置的值自增1
		
		for(int i = 1; i<temp.length;i++)	// 辅助数组从index=1开始遍历
			temp[i] = temp[i] + temp[i-1];  // 当前值+前一个元素的值，赋值给当前值。以此来帮助计算result放置的位置
		// 逆序输出确保稳定--保证相同因素的相对顺序
		for(int i = array.length - 1; i>=0; i--){
			int v = array[i];			// 当前元素
			result[temp[v] - 1] = v;	// 当前元素作为索引，得到辅助数组元素，减一后的结果作为result中的索引，该处放置当前的遍历元素
			temp[v] = temp[v] - 1;		// 辅助数组相应位置减少1，以供下个相同元素索引到正确位置
		}
		return result;
	}
	private static int max(int[] array) {
		int max = array[0];
		for(int i = 1; i < array.length; i++)
			if(array[i] > max)	max = array[i];
		return max;
	}
	public static void main(String[] args) {
		int[] arr = {3,4,1,7,2,8,0};
		int[] result = sort(arr);
		System.out.println(Arrays.toString(result));
	}
}
