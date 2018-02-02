package leetcode.matrix;
/**
 * LeetCode329
 * https://leetcode.com/problems/longest-increasing-path-in-a-matrix/description/
 * time: m*n	space: m*n
 */
public class LongestIncreasePath {
	public int longestIncreasingPath(int[][] matrix){
		if(matrix == null || matrix.length == 0)	return 0;
		int result = 0;
		int m = matrix.length;
		int n = matrix[0].length;
		int[][] cache = new int[m][n];
		
		for(int i=0; i<m; i++){
			for(int j = 0;j<n; j++){
				int max = dfs(matrix, Integer.MIN_VALUE, i, j, m, n, cache);
				result = Math.max(result, max);
			}
		}
		
		return result;
	}

	private int dfs(int[][] matrix, int min, int i, int j, int m, int n, int[][] cache) {
		if(i<0 || j<0 || i>=m|| j>=n || matrix[i][j]<=min)	return 0;
		if(cache[i][j] != 0)	return cache[i][j];
		min = matrix[i][j];
		int up = dfs(matrix, min, i-1, j, m, n, cache) + 1;
		int down = dfs(matrix, min, i+1, j, m, n, cache) + 1;
		int left = dfs(matrix, min, i, j-1, m, n, cache) + 1;
		int right = dfs(matrix, min, i, j+1, m, n, cache) + 1;
		int max = Math.max(up, Math.max(down, Math.max(left, right)));
		cache[i][j] = max;
		return max;
	}
}
