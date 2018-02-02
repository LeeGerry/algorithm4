package leetcode.matrix;

import java.util.ArrayList;
/**
 * LeetCode 296 Best Meeting Point
 * https://www.youtube.com/watch?v=vCRnwe0L0sg&index=12&list=PLvyIyKZVcfAkMmWl2SIreK_2UINBDq-U7
 * time: O(m*n) space: O(max(m,n))
 */
public class BestMeetingPoing {
	public int minTotalDistance(int[][] grid){
		int m = grid.length;
		int n = grid[0].length;
		ArrayList<Integer> I = new ArrayList<>();
		ArrayList<Integer> J = new ArrayList<>();
		for(int i = 0; i<m;i++){
			for(int j = 0;j<n;j++){
				if(grid[i][j] == 1)
					I.add(i);
			}
		}
		for(int j=0;j<n; j++){
			for(int i=0;i<m;i++){
				if(grid[i][j] == 1)
					J.add(j);
			}
		}
		return min(I) + min(J);
	}

	private int min(ArrayList<Integer> list) {
		int i = 0, j = list.size();
		int sum = 0;
		while(i<j){
			sum += list.get(j--) - list.get(i++);
		}
		return sum;
	}
}
