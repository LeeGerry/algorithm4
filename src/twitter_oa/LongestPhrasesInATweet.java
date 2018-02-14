package twitter_oa;
import java.util.ArrayList;

/**
 * Give an array with integer and a number k, 
 * return the length of longest sub array having the sum <= k
 * 
 * Example
 *  	input:{1,2,3} k=3
 *  	output:2
 *  explain:	
 *  	sub arrays: {1}, {2}, {3}, {1, 2}, {2, 3}, {1, 2, 3}
 *  	sum <= k:	{1}, {2}, {3}, {1, 2}
 */
public class LongestPhrasesInATweet {
	
	public static int lengthOfLongestSubArray(int[] arr, int k){
		int maxLen = 0;
		ArrayList<Integer> list = new ArrayList<>();
		int sum = 0;
		for(int i = 0; i<arr.length; i++){
			list.add(arr[i]);
			sum += arr[i];
			if(sum <= k){
				maxLen = Math.max(maxLen, list.size());
			}else{
				sum -= list.get(0);
				list.remove(0);
			}
		}
		return maxLen;
	}
	public static void main(String[] args) {
		int[] array = {1,4,2,3,3,1,1,1};
		int[] array1 = {1,2,3};
		System.out.println(lengthOfLongestSubArray(array1, 3));
		System.out.println(lengthOfLongestSubArray(array, 11));
	}
}
