package leetcode.matrix;

/**
 * LeetCode 361 https://www.youtube.com/watch?v=ykOJqf4TWmU
 * Given a 2D grid, each cell is either a wall 'Y', an enemy 'X' or empty '0' (the number zero), 
   return the maximum enemies you can kill using one bomb.
   
   The bomb kills all the enemies in the same row and column from the planted point 
   until it hits the wall since the wall is too strong to be destroyed.
	
   Note that you can only put the bomb at an empty cell.

Example:

	For the given grid
		0 X 0 0
		X 0 Y X
		0 X 0 0
	return 3. (Placing a bomb at (1,1) kills 3 enemies)
	
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
