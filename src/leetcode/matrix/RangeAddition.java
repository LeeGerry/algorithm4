package leetcode.matrix;
/**
 * LeetCode 370 Range Addition
 * Given:
 * 		length = 5,
 *  	updates = [
 *      	[1,  3,  2],
 *      	[2,  4,  3],
 *      	[0,  2, -2]
 *   	]
 * Output:
 * 		[-2, 0, 3, 5, 3]
 *
 */
public class RangeAddition {
	// time: O(k+n) space:O(n)
	public int[] getModifiedArray(int length, int[][] updates){
		int[] result = new int[length];
		for(int[] update: updates){
			int value = update[2];
			int start = update[0];
			int end = update[1];
			result[start] += value;
			if(end + 1 < length)	
				result[end + 1] -= value;
		}
		for(int i = 1; i<length; i++)
			result[i] += result[i-1];
		return result;
	}
}
