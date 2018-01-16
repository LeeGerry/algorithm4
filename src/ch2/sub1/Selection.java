package ch2.sub1;

import ch1.interf.Sort;

/**
 * 选择排序
 */
public class Selection extends Sort{
	public void sort(Comparable[] a, int lo, int hi){
		int n = hi;
		for(int i=0;i<n;i++){
			int min = i;//存放最小值索引
			for(int j=i+1;j<n;j++){//从索引的下一个元素开始遍历
				if(less(a[j], a[min]))//比较元素是否小于最小值索引
					min = j;//如果小，则更新最小值索引
			}
			//内循环遍历一遍以后,min存放的是当前一遍找到的最小值的索引，和外循环的位置进行交换
			exch(a, i, min);
		}
	}
	
	public static void main(String[] args) {
		new Selection().test();
	}
}
