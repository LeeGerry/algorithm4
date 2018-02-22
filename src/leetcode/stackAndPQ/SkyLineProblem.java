package leetcode.stackAndPQ;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

/**
 * https://leetcode.com/problems/the-skyline-problem/description/
 * https://www.youtube.com/watch?v=7AE-VCGEhtI
 * 
 */
public class SkyLineProblem {
	// time: O(n Log n) space: O(n)
	public List<int[]> getSkyline(int[][] buildings){
		List<int[]> res = new ArrayList<>();
		List<int[]> heights = new ArrayList<>();
		for(int[] b: buildings){
			heights.add(new int[]{b[0], -b[2]});
			heights.add(new int[]{b[1], b[2]});
		}
		Collections.sort(heights, (a,b)->(a[0] == b[0] ? a[1]-b[1]:a[0]-b[0]));
		TreeMap<Integer, Integer> map = new TreeMap<>(Collections.reverseOrder());
		map.put(0, 1);
		int prev = 0;
		for(int[] h: heights){
			if(h[1] < 0){
				map.put(-h[1], map.getOrDefault(-h[1], 0) + 1);
			}else{
				int count = map.get(h[1]);
				if(count == 1)	map.remove(h[1]);
				else			map.put(h[1], count - 1);
			}
			int current = map.firstKey();
			if(prev != current){
				res.add(new int[]{h[0], current});
				prev = current;
			}
		}
		return res;
	}
	public static void main(String[] args) {
		int[][] buildings = {{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}};
		SkyLineProblem sp = new SkyLineProblem();
		System.out.println(sp.getSkyline(buildings));
	}
}
