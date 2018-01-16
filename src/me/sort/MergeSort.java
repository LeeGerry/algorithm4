package me.sort;
/**
* 归并排序：自顶向下
* 		分治思想的最经典的一个例子。
* 		这段递归代码是归纳证明算法能够正确地将数组排序的基础：
* 			如果它能将两个子数组排序，它就能通过归并两个子数组来讲整个数组排序
*/
public class MergeSort<T> extends Sort<T>{
	private static Comparable[] auxiliary;
	@Override
	public void sort(Comparable[] array) {
		auxiliary = new Comparable[array.length];
		sort(array, 0, array.length-1);
	}
	
	private void sort(Comparable[] array, int low, int high) {
		if(high <= low)		return;
		int mid = low + (high - low) / 2;
		sort(array, low, mid);		//将左半边排序
		sort(array, mid + 1, high);	//将右半边排序
		merge(array, low, mid, high);//归并结果
	}

	private void merge(Comparable[] a, int low, int mid, int high){
		// 将a[low...mid]和a[mid+1...high]归并
		int i = low, j = mid + 1;
		// 先将所有元素复制到aux中，然后再归并会a中。
		for(int k = low; k <= high; k++)
			auxiliary[k] = a[k];
		for(int k = low; k <= high; k++)//归并回到a[low...high]
			if(i > mid)	
				a[k] = auxiliary[j++];	// 左半边用尽，取右半边的元素
			else if	(j > high)
				a[k] = auxiliary[i++];	// 右半边用尽，取左半边的元素
			else if	(less(auxiliary[j], auxiliary[i]))
				a[k] = auxiliary[j++];	// 右半边当前元素小于左半边当前元素，取右半边的元素
			else	
				a[k] = auxiliary[i++];	// 左半边当前元素小于又半边当前元素，取左半边的元素
	}
	public static void main(String[] args) {
		new MergeSort().test(testArray);
	}
}
