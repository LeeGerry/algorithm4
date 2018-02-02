package leetcode.matrix;
/**
 * https://leetcode.com/problems/spiral-matrix-ii/description/
 * Time n^2
 * Space n^2
 */
public class SpiralMatrix2 {
	public int[][] generateMatrix(int n){
		int[][] matrix = new int[n][n];
		int rowBegin = 0;
		int colBegin = 0;
		int rowEnd = n-1;
		int colEnd = n-1;
		
		int num = 1;
		
		while(rowBegin <= rowEnd && colBegin <= colEnd){
			// 向右
			for(int i = colBegin; i<=colEnd; i++)
				matrix[rowBegin][i] = num++;
			rowBegin++;
			
			// 向下
			for(int i = rowBegin; i<=rowEnd; i++)
				matrix[i][colEnd] = num++;
			colEnd--;
			
			// 向左
			for(int i = colEnd; i>=colBegin; i--)
				matrix[rowEnd][i] = num++;
			rowEnd--;
			
			// 向上
			for(int i = rowEnd; i>=rowBegin; i--)
				matrix[i][colBegin] = num++;
			colBegin++;
		}
		return matrix;
	}
}
