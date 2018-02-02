package leetcode.matrix;
/**
 * https://leetcode.com/problems/rotate-image/
 */
public class RotateImage {
	
	public void rotate(int[][] matrix) {
		for(int layer = 0; layer < matrix.length/2; layer++){
			int first = layer;
			int last = matrix.length - 1 - layer;
			for(int i = first; i<last; i++){
				int offset = i - first;
				// 存储上边
				int top = matrix[first][i];
				// 左到上
				matrix[first][i] = matrix[last-offset][first];
				// 下到左
				matrix[last-offset][first] = matrix[last][last-offset];
				// 右到下
				matrix[last][last-offset] = matrix[i][last];
				// 上到右
				matrix[i][last] = top;
			}
		}
	}
	
}
