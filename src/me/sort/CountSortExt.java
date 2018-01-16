package me.sort;
/**
 * 计数排序的扩展
 */
public class CountSortExt {
	private int[] temp;		// 辅助数组
	public CountSortExt(int[] a){
		int max = max(a);
		temp = new int[max+1];
		for(int i = 0; i<a.length; i++)
			temp[a[i]] += 1;
		for(int i = 1; i<temp.length; i++)
			temp[i] += temp[i-1];
	}
	private int max(int[] a) {
		int max = a[0];
		for(int cur: a)
			if(max < cur)	max = cur;
		return max;
	}
	/**返回(a,b]之间元素的个数*/
	public int getCountBetweenAandB(int a, int b){
		return temp[b] - temp[a];
	}
	public static void main(String[] args) {
		int[] arr = {1,2,2,3,2,8,0};
		CountSortExt e = new CountSortExt(arr);
		System.out.println(e.getCountBetweenAandB(1, 8));
	}
}
