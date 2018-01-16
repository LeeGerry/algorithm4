package me.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 桶排序
 */
public class BucketSort {
	private static final int DEFAULT_BUCKET_SIZE = 5;
	public static void sort(Integer[] array){
		sort(array, DEFAULT_BUCKET_SIZE);
	}
	public static void sort(Integer[] array, int size) {
		if(array == null || array.length == 0)	return;
		// 找最大最小值
		int min = array[0], max = array[0];
		for(int i=1; i<array.length; i++){
			if(array[i]<min)		min = array[i];
			else if(array[i] > max)	max = array[i];
		}
		
		// 初始化桶
		int bucketCount = (max - min) / size + 1;
		List<List<Integer>> buckets = new ArrayList<>(bucketCount);
		for(int i = 0; i < bucketCount; i++)
			buckets.add(new ArrayList<Integer>());
		
		// 把输入数组均匀分布进buckets
		for(int i = 0; i<array.length; i++){
			int current = array[i];
			int index = (current - min) / size;
			buckets.get(index).add(current);
		}
		
		// 对每个桶进行排序，并且每个桶中的数据放置回数组
		int currentIndex = 0;
		for(int i = 0; i < buckets.size(); i++){
			List<Integer> currentBucket = buckets.get(i);
			Integer[] bucketArray = new Integer[currentBucket.size()];
			bucketArray = currentBucket.toArray(bucketArray);
			Arrays.sort(bucketArray);
			for(int j = 0; j< bucketArray.length; j++)
				array[currentIndex++] = bucketArray[j];
		}
	}
	public static void main(String[] args) {
		Integer[] array = {3,213,3,4,5,32,3,88,10};
		sort(array);
		System.out.println(Arrays.toString(array));
	}
}
