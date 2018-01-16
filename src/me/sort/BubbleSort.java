package me.sort;

/**
 * 冒泡排序
 * 时间：O(n^2)；空间O(1)
 * 稳定，因为存在两两比较，不存在跳跃
 * 排序时间与输入无关
 */
public class BubbleSort<T> extends Sort<T> {
	@Override
	public void sort(Comparable[] array) {
		int len = array.length;
		for(int i = 0; i<len-1; i++){
			for(int j = len-1; j>i; j--){
				if(less(array[j], array[j-1]))
					exchange(array, j, j-1);
			}
		}
	}
	public static void main(String[] args) {
		new BubbleSort<Integer>().test(testArray);
	}
}
