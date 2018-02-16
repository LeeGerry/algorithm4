package leetcode.matrix;

import java.util.LinkedList;
import java.util.Queue;

/**
 * LeetCode 317: https://www.youtube.com/watch?v=L3R0Yb4ET8k&t=756s
 * Time: O(m^2 * n^2) Space: O(m*n)
 * 				 {1,0,2,0,1},
				 {0,0,0,0,0},
				 {0,0,1,0,0}

	对二维数组，0表示通路，1表示城市，2表示障碍物，找到一个0的位置，使到每个城市的距离最短，只能上下左右走，一步表示1，返回结果是最小距离。
	如图，[1,2]处是0点，到三个城市的距离和最短，分别是3+3+1=7，返回7.
	
	例2：		{1,0,0,0,1},
				{0,0,0,2,2},
				{0,0,1,0,0}
	[0,2]处到三个城市距离和最短，2+2+2 = 6，返回6
	
	解法: 两层循环遍历每个点，如果是城市，就进行广搜。
		buildNum表示总共的城市数目
		dist[i][j]表示[i][j]处的0点到所有城市的距离和，每次都在已有的基础上进行累加。
		nums[i][j]表示[i][j]处的0点到所有城市的城市个数，每次多一个城市并且能到该城市，就自增
		BFS以后，从dist数组中找出最小值（满足是0点，距离!=0,该点到达城市的数目==buildNum）
 */
public class ShortestDistanceFromBuildings {
	public int shortestDistance(int[][] grid) {
		if (grid == null | grid.length == 0)
			return -1;
		int m = grid.length;
		int n = grid[0].length;
		int[][] dist = new int[m][n];
		int[][] nums = new int[m][n];
		
		int buildingNum = 0;
		
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 1) { // 如果是城市
					buildingNum++; // 城市数目++
					bfs(grid, i, j, dist, nums); //进行深搜
				}
			}
		}
		
		int res = Integer.MAX_VALUE;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 0 && dist[i][j] != 0 && nums[i][j] == buildingNum) {
					res = Math.min(res, dist[i][j]);
				}
			}
		}
		return res == Integer.MAX_VALUE ? -1 : res;
	}

	private void bfs(int[][] grid, int row, int col, int[][] dist, int[][] nums) {
		int m = grid.length;
		int n = grid[0].length;
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] { row, col });
		int[][] dirs = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } }; // 上右下左四个方向
		boolean[][] visited = new boolean[m][n];
		int distance = 0;
		while (!queue.isEmpty()) {
			distance++; // 广搜层次
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				int[] cur = queue.poll();
				for (int k = 0; k < dirs.length; k++) { // (上右下左)四次
					int x = cur[0] + dirs[k][0];
					int y = cur[1] + dirs[k][1]; // 计算要搜索的方向
					// 边界越界，访问过，不是0点，都不用进行
					if (x >= 0 && x < m && y >= 0 && y < n && !visited[x][y] && grid[x][y] == 0) {
						visited[x][y] = true;
						dist[x][y] += distance;
						nums[x][y]++;
						queue.offer(new int[] { x, y });
					}
				}
			}
		}
	}
	public static void main(String[] args) {
		int[][] grid = {
				{1,0,0,0,1},
				{0,0,0,2,2},
				{0,0,1,0,0}
		};
		int[][] grid1 = {
				{1,0,2,0,1},
				{0,0,0,0,0},
				{0,0,1,0,0}
		};
		System.out.println(new ShortestDistanceFromBuildings().shortestDistance(grid1));
	}
}
