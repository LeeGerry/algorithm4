package leetcode.matrix;

import java.util.PriorityQueue;

class Tuple{
	int x;
	int y;
	int val;
	public Tuple(int x, int y, int val){
		this.x = x;
		this.y = y;
		this.val = val;
	}
}
public class KthSmallestElement {
	// time: nlogn	space:n
	public int kthSmallest(int[][] matrix, int k){
		PriorityQueue<Tuple> pq = new PriorityQueue<>(matrix[0].length, (a,b) -> (a.val-b.val));;
		for(int i=0; i<matrix.length; i++)	pq.offer(new Tuple(0, i, matrix[0][i]));
		for(int i=0; i<k-1; i++){
			Tuple t = pq.poll();
			if(t.x == matrix.length - 1)	continue;
			pq.offer(new Tuple(t.x + 1, t.y, matrix[t.x + 1][t.y]));
		}
		return pq.poll().val;
	}
	// time: n*log(max-min) 	space: 1
	public int kthSmallest2(int[][] matrix, int k){
		int n = matrix.length;
		int left = matrix[0][0];
		int right = matrix[n-1][n-1];
		while(left + 1 < right){
			int mid = left + (right - left) / 2;
			int num = count(matrix, mid);
			if(num >= k)	right = mid;
			else			left = mid;
		}
		if(count(matrix, right) <= k-1)	return right;
		return left;
	}

	private int count(int[][] matrix, int target) {
		int n = matrix.length;
		int res = 0;
		int i = n-1, j= 0;
		while(i>=0 && j<n){
			if(matrix[i][j] < target){
				res += i+1;
				j++;
			}else{
				i--;
			}
		}
		return res;
	}
}
