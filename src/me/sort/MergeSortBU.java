package me.sort;

/**
 * 自底向上的归并排序
 * 		会多次遍历整个数组，根据子数组大小进行两两归并。
 * 		子数组的大小size初始值为1，每次加倍。
 * 		最后一个子数组的大小只有在数组大小是size的偶数被时才会等于size，否则会比size小。
 * @param <T>
 */
public class MergeSortBU<T> extends Sort<T>{
	private static Comparable[] aux;
	@Override
	public void sort(Comparable<T>[] a) {
		int n = a.length;
		aux = new Comparable[n];
		//进行lgN次两两归并
		for(int size = 1; size < n; size = size + size)
			for(int low = 0; low < n - size; low += size+size)
				merge(a, low, low+size-1, Math.min(low+size + size-1, n-1));
	}
	@SuppressWarnings("unchecked")
	private void merge(Comparable<T>[] a, int low, int mid, int high){
		int i = low, j = mid + 1;
		for(int k = low; k <= high; k++)
			aux[k] = a[k];
		for(int k = low; k <= high; k++){
			if(i > mid)
				a[k] = aux[j++];
			else if(j > high)
				a[k] = aux[i++];
			else if(less(a[j], a[i]))
				a[k] = aux[j++];
			else
				a[k] = aux[i++];
		}
	}
	public static void main(String[] args) {
		new MergeSortBU<Integer>().test(testArray);
	}
}
