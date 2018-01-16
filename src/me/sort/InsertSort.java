package me.sort;

/**
 * 插入排序
 * 		1.时间O(n^2);空间O(1)；
 * 		2.排序时间与输入有关：输入的元素个数，输入元素已排序程度；
 * 		3.最好情况：输入数组已经是排序的，时间变为n的线性函数；
 * 		4.最坏情况：输入数组是逆序，时间是n的二次函数
 */
public class InsertSort<T> extends Sort<T> {
	@Override
	public void sort(Comparable<T>[] array) {
		int len = array.length;
		// 把a[i] 插入到a[i-1], a[i-2], a[i-3]...中
		for (int i = 1; i < len; i++) {
			// j从i开始，如果j>0并且j处元素比前面的元素小，则进行交换，j--，继续向前比较
			for (int j = i; j > 0 && less(array[j], array[j-1]); j--)
				exchange(array, j, j-1);
		}
	}

	public static void main(String[] args) {
		new InsertSort().test(testArray);
	}
}
