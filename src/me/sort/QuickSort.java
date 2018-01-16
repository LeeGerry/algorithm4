package me.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 快排序
 */
public class QuickSort<T> extends Sort<T> {
	@Override
	public void sort(Comparable<T>[] array) {
		//shuffle(array);
		System.out.println("打乱后:"+Arrays.toString(array));
		sort(array, 0, array.length - 1);
	}
	private void sort(Comparable<T>[] array, int low, int high) {
		if(high <= low)	return;
		int j = partition(array, low, high);	// 切分
		sort(array, low, j-1);				// 将左半部分array[low, ... , j-1]进行排序
		sort(array, j+1, high);				// 将右半部分array[j+1, ... , high]进行排序
	}
	private int partition(Comparable<T>[] array, int low, int high) {
		// 将数组切分为array[low, ... , i-1], array[i], array[i+1, ... , high]
		int i = low, j = high+1;		//左右扫描指针
		Comparable v = array[low];	
		while(true){
			//扫描左右，检查扫描是否结束并交换元素
			while(less(array[++i], v))	if(i == high)	break;//左指针向右找到一个大于v的位置
			while(less(v, array[--j]))	if(j == low)		break;//右指针向左找到一个小于v的位置
			if(i >= j)	break;	// 如果左指针重叠或者超过右指针，跳出
			exchange(array, i, j); // 交换左右指针位置的元素
		}
		exchange(array, low, j);
		return j;
	}
	private void shuffle(Comparable<T>[] a){
		Random random = new Random();
		for(int i = 0; i<a.length;i++){
			int r = i + random.nextInt(a.length - i);
			exchange(a, i, r);
		}
	}
	public static void main(String[] args) {
		new QuickSort<Integer>().test(testArray);
	}
}
