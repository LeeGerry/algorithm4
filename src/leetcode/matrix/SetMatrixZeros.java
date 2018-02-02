package leetcode.matrix;
/**
 * https://leetcode.com/problems/set-matrix-zeroes/description/
 * 
 */
public class SetMatrixZeros {
	public void setZeros(int[][] matrix){
		boolean[] row = new boolean[matrix.length];
		boolean[] col = new boolean[matrix[0].length];
		
		// 记录值为0的元素所在行索引和列索引
		for(int i = 0; i<matrix.length; i++){
			for(int j = 0; j<matrix[0].length;j++){
				if(matrix[i][j] == 0){
					row[i] = true;
					col[j] = true;
				}
			}
		}
		
		// 若i行或者j列有个元素为0，
		for(int i = 0; i<matrix.length; i++){
			for(int j = 0; j<matrix[0].length;j++){
				if(row[i] || col[j])	matrix[i][j] = 0;
			}
		}
	}
	public void setZeros2(int[][] matrix){
		boolean booRow = false, booCol = false;
		for(int i=0; i<matrix.length; i++){
			for(int j=0; j<matrix[0].length; j++){
				if(matrix[i][j] == 0){
					if(i==0)	booRow = true;
					if(j==0)	booCol = true;
					matrix[0][j] = 0;
					matrix[i][0] = 0;
				}
			}
		}
		for(int i = 1; i<matrix.length; i++){
			for(int j=1; j<matrix[0].length; j++){
				if(matrix[i][0] == 0 || matrix[0][j] == 0)
					matrix[i][j] = 0;
			}
		}
		if(booRow)
			for(int i=0;i<matrix[0].length; i++)	matrix[0][i] = 0;
		if(booCol)
			for(int i=0; i<matrix.length; i++)	matrix[i][0] = 0;
	}
}
