package me.sort;

/**
 * 选择排序
 * 		1.时间O(n^2),空间O(1)
 * 		2.排序时间和输入无关
 * 		3.最好和最坏都是一样的
 * 		4.不稳定，例如{6, 6, 1}.找到最小的是1，和第一个6交换以后，第一个6跑到了后面
 */
public class SelectionSort<T> extends Sort<T>{
	@Override
	public void sort(Comparable<T>[] array) {
		int len = array.length;
		for(int i = 0; i<len; i++){
			int min = i;
			//左边已经排好序，每次从i+1开始找到最小值，并记录位置
			for(int j=i+1; j<len; j++){
				if(less(array[j], array[min]))
					min = j;	// 记录最小值的位置
			}
			exchange(array, min, i);//内循环结束后最小值和i进行交换，确保左边依旧是排好序的状态
		}
	}
	public static void main(String[] args) {
		new SelectionSort().test(testArray);
	}
}
