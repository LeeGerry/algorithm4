package leetcode.matrix;
/**
 * LeetCode 311
 */
public class SparseMatrixMultiply {
	public int[][] mutiply(int[][]A, int[][]B){
		int m = A.length, n = A[0].length;
		int nB = B[0].length;
		int[][] result = new int[m][nB];
		
		for(int i=0;i<m;i++){
			for(int k=0;k<n;k++){
				if(A[i][k] != 0){
					for(int j=0; j<nB; j++){
						if(B[k][j] != 0)	result[i][j] += A[i][k] * B[k][j];
					}
				}
			}
		}
		
		return result;
	}
}
