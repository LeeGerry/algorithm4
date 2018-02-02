package leetcode;
/**
 * 题目:	https://leetcode.com/problems/maximum-subarray/description/
 * 参考:	http://danielwan.me/2017/11/kadane-algorithm.html
 * 	   	https://www.youtube.com/watch?v=86CQq3pKSUw
 * 最大子序列问题的DP解法:
 * 		[-2,1,-3,4,-1,2,1,-5,4],找到最大子数组的和
 * 		最大子数组是 [4,-1,2,1] has the largest sum = 6
 */
public class MaximumSubarray {
    public int maxSubArray(int[] nums) {
    	int maxForNow = nums[0];
    	int maxPostFix = nums[0];
    	for(int i = 1; i < nums.length; i++){
    		maxPostFix = Math.max(maxPostFix + nums[i], nums[i]);
    		maxForNow = Math.max(maxForNow, maxPostFix);
    	}
    	return maxForNow;
    }
}
