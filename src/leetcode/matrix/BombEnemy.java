package leetcode.matrix;

/**
 * LeetCode 361 https://www.youtube.com/watch?v=ykOJqf4TWmU
 */
public class BombEnemy {
	public int maxKilledEnemies(char[][] grid) {
		if (grid == null || grid.length == 0)
			return 0;
		int m = grid.length;
		int n = grid[0].length;
		int rowCount = 0;
		int[] colCounts = new int[n];
		int result = 0;

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (j == 0 || grid[i][j - 1] == 'W') {
					rowCount = 0;
					for (int k = j; k < n && grid[i][k] != 'W'; k++) {
						rowCount += grid[i][k] == 'E' ? 1 : 0;
					}
				}
				if (i == 0 || grid[i - 1][j] == 'W') {
					colCounts[j] = 0;
					for (int k = i; k < m && grid[k][j] != 'W'; k++) {
						colCounts[j] += grid[k][j] == 'E' ? 1 : 0;
					}
				}
				if (grid[i][j] == '0') {
					result = Math.max(result, colCounts[j] + rowCount);
				}
			}
		}

		return result;
	}
	public static void main(String[] args) {
		char[][] chars = {
				{'0', 'E', '0', '0'},
				{'E', '0', 'W', 'E'},
				{'0', 'E', '0', '0'}
		};
		BombEnemy be = new BombEnemy();
		System.out.println(be.maxKilledEnemies(chars));
	}
}
