package me.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 快排序:三项切分的快速排序
 */
public class Quick3WaySort<T> extends Sort<T> {
	@Override
	public void sort(Comparable<T>[] array) {
		shuffle(array);
		System.out.println("打乱后:"+Arrays.toString(array));
		sort(array, 0, array.length - 1);
	}
	private void sort(Comparable[] array, int low, int high) {
		if(high <= low)	return;
		int lt = low, i = low + 1, gt = high;
		Comparable<T> v = array[low];
		while(i <= gt){
			int cmp = array[i].compareTo(v);
			if(cmp < 0)			exchange(array, lt++, i++);
			else if(cmp > 0)	exchange(array, i, gt--);
			else				i++;
		} // 现在array[low ... lt-1] < v = a[lt ... gt] < array[gt+1 .. high]成立
		sort(array, low, lt-1);
		sort(array, gt+1, high);
	}
	private void shuffle(Comparable<T>[] a){
		Random random = new Random();
		for(int i = 0; i<a.length;i++){
			int r = i + random.nextInt(a.length - i);
			exchange(a, i, r);
		}
	}
	public static void main(String[] args) {
		Integer[] chars = {18,2,23,23,18,23,2,18,18,23,2,18}; 
		new Quick3WaySort<Integer>().test(chars);
	}
}
