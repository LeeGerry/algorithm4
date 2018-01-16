package fb;

import java.util.HashMap;

/**
 * leetcode 325
 */
public class MaxSizeSubArray {
	public int maxSubArrayLen(int[] nums, int k) {
		int max = 0;
		int sum = 0;
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			sum = sum + nums[i];
			if (sum == k)
				max = i + 1;
			else if (map.containsKey(sum - k)){
				int temp = i - map.get(sum - k);
				max = Math.max(max, temp);
			}
			
			if (!map.containsKey(sum))
				map.put(sum, i);
		}
		return max;
	}
	public static void main(String[] args) {
		int[] nums = {1, -1, 5, -2, 3};
		int size = new MaxSizeSubArray().maxSubArrayLen(nums, 3);
		System.out.println(size);
	}
}
