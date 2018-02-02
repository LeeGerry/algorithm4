package leetcode.matrix;

import java.util.ArrayList;
import java.util.List;
/**
 * https://leetcode.com/problems/spiral-matrix/description/
 */
public class SpiralMatrix {
	public List<Integer> spiralOrder(int[][] matrix){
		List<Integer> result = new ArrayList<>();
		if(matrix.length == 0)	return result;
		int rowBegin = 0;
		int rowEnd = matrix.length - 1;
		int colBegin = 0;
		int colEnd = matrix[0].length -1;
		while(rowBegin <= rowEnd && colBegin <= colEnd){
			// 向右
			for(int j=colBegin; j<=colEnd; j++)
				result.add(matrix[rowBegin][j]);
			rowBegin++;
			
			// 向下
			for(int j = rowBegin; j<=rowEnd; j++)
				result.add(matrix[j][colEnd]);
			colEnd--;
			
			// 向左
			if(rowBegin<=rowEnd)
				for(int j = colEnd; j>=colBegin; j--)
					result.add(matrix[rowEnd][j]);
			rowEnd--;
			
			// 向上
			if(colBegin <= colEnd)
				for(int j = rowEnd; j>=rowBegin; j--)
					result.add(matrix[j][colBegin]);
			colBegin++;
		}
		return result;
	}
}
