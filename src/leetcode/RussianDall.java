package leetcode;

import java.util.Arrays;
import java.util.Comparator;

public class RussianDall {
	private int binarySearch(int[] dp, int start, int end, int target){
		while(start + 1 < end){
			int mid = start + (end - start) / 2;
			if(dp[mid] == target)		return mid;
			else if(dp[mid] < target)	start = mid;
			else						end = mid;
		}
		if(dp[start] >= target)		return start;
		return end;
	}
	public int maxEnvelopes(int[][] envelopes) {
		if(envelopes == null || envelopes.length == 0)	return 0;
		Arrays.sort(envelopes, (a,b)->a[0] == b[0] ? b[1]-a[1] : a[0] - b[0]);
		int[] dp = new int[envelopes.length];
		int result = 0;
		for(int[] en: envelopes){
			int i = binarySearch(dp, 0, result, en[1]);
			dp[i] = en[1];
			if(i == result)	result++;
		}
		return result;
	}
	public static void main(String[] args) {
		int[][] arr = {
				{5,4},{6,4},{6,7},{2,3}
		};
		System.out.println(new RussianDall().maxEnvelopes(arr));
	}
}
