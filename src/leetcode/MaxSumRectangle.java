package leetcode;

/**
 * https://leetcode.com/problems/max-sum-of-rectangle-no-larger-than-k/description/
 * https://www.youtube.com/watch?v=yCQN096CwWM
 * 
 */
public class MaxSumRectangle {
	class Result{
		int maxSum;
		int leftBound,rightBound,upperBound,lowerBound;
		@Override
		public String toString() {
			return "Result [maxSum=" + maxSum + ", leftBound=" + leftBound + ", rightBound=" + rightBound
					+ ", upperBound=" + upperBound + ", lowerBound=" + lowerBound + "]";
		}
	}
	private KadaneResult kadane(int[] arr){
		int max = 0;
		int maxStart = -1;
		int maxEnd = -1;
		int currentStart = 0;
		int maxSoFar = 0;
		for(int i = 0; i<arr.length;i++){
			maxSoFar += arr[i];
			if(maxSoFar < 0){
				maxSoFar = 0;
				currentStart = i+1;
			}
			if(max < maxSoFar){
				maxStart = currentStart;
				maxEnd = i;
				max = maxSoFar;
			}
		}
		return new KadaneResult(max, maxStart, maxEnd);
	}
	public Result maxSumSubmatrix(int[][] matrix, int k) {
		int row = matrix.length;
		int col = matrix[0].length;
		int[] temp = new int[row];
		Result result = new Result();
		for(int left = 0; left<col;left++){
			for(int i = 0; i<row; i++)	temp[i] = 0;
			for(int right = left; right < col; right++){
				for(int i = 0;i<row; i++)	temp[i] += matrix[i][right];
				KadaneResult kadaneResult = kadane(temp);
				if(kadaneResult.maxSum > result.maxSum){
					result.maxSum = kadaneResult.maxSum;
					result.leftBound = left;
					result.rightBound = right;
					result.upperBound = kadaneResult.start;
					result.lowerBound = kadaneResult.end;
				}
			}
		}
		return result;
	}
	class KadaneResult{
		int maxSum;
		int start;
		int end;
		public KadaneResult(int maxSum, int start, int end){
			this.maxSum = maxSum;
			this.start = start;
			this.end = end;
		}
	}
	public static void main(String[] args) {
		int[][] matrix = {  { 2,  1, -3, -4,  5},
                			{ 0,  6,  3,  4,  1},
                			{ 2, -2, -1,  4, -5},
                			{-3,  3,  1,  0,  3}
                		};
		MaxSumRectangle re = new MaxSumRectangle();
		Result result = re.maxSumSubmatrix(matrix, 9);
		System.out.println(result);
	}
}
