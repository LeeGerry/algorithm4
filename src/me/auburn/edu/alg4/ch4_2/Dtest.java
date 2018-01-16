package me.auburn.edu.alg4.ch4_2;

public class Dtest {
	public static void main(String[] args) {
		int[][] edges = {{0,1},{1,0}};
		Digraph d = new Digraph(2,edges);
		System.out.println(DigraphUtils.graphToString(d));
		DirectedCycle cycle = new DirectedCycle(d);
		boolean hasCycle = cycle.hasCycle();
		if(hasCycle) System.out.println(cycle.getCycle().toString());
		else	System.out.println("no cycle");
	}
}
