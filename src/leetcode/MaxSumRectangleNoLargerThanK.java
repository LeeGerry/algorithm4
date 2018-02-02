package leetcode;

import java.util.TreeSet;

/**
 * 
 */
public class MaxSumRectangleNoLargerThanK {
	public int maxSumSubMatrix(int[][] matrix, int k){
		int m = matrix.length, n = 0;
		if(m>0)	n = matrix[0].length;
		if(m * n == 0)	return 0;
		int M = Math.max(m, n);
		int N = Math.min(m, n);
		
		int res = Integer.MIN_VALUE;
		for(int i = 0; i<N; i++){
			System.out.println("i="+i);
			int[] sums = new int[M];
			for(int j = i; j<N; j++){
				System.out.println("j="+j);
				TreeSet<Integer> set = new TreeSet<>();
				int num = 0;
				for(int x = 0; x < M; x++){
					System.out.println("x="+x);
					sums[x] += m>n ? matrix[x][j]:matrix[j][x];
					num += sums[x];
					if(num <= k) res = Math.max(res, num);
					Integer t = set.ceiling(num - k);
					if(t != null)	res = Math.max(res, num - t);
					set.add(num);
					System.out.println(arrToString(sums) + ", res="+res + ", num="+num+", set:"+set.toString());
					System.out.println("--------------------------------");
				}
			}
		}
		return res;
	}
	private String arrToString(int[] ar){
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for(int i: ar){
			sb.append(i + " ");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append("]");
		return sb.toString();
	}
	public static void main(String[] args) {
		int[][] matrix = {  { 2,  1, -3,  5},
                			{ 0,  6,  3,  1},
                			{ 2, -2,  1, -5}
                		};
		MaxSumRectangleNoLargerThanK re = new MaxSumRectangleNoLargerThanK();
		System.out.println(re.maxSumSubMatrix(matrix, 12));
	}
}
