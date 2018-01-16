package com.algorithm4_4_1;

import java.io.File;

import edu.princeton.cs.algs4.In;

public class Cycle {
	private boolean[] marked;
	private boolean hasCycle;
	public Cycle(Graph g){
		marked = new boolean[g.getVCount()];
		for(int s=0; s<g.getVCount(); s++){
			if(!marked[s])	
				dfs(g, s, s);
		}
	}
	private void dfs(Graph g, int v, int u) {
		marked[v] = true;
		for(int w: g.adj(v)){
			if(!marked[w])	dfs(g, w, v);
			else if(w != u)	hasCycle = true;
		}
	}
	public boolean hasCycle(){ return hasCycle; }
	public static void main(String[] args) {
		String path = "/Users/liguorui/Documents/javaee/algorith4/src/com/algorithm4/cycle_test.txt";
		Graph g = new Graph(new In(new File(path)));
		Cycle c = new Cycle(g);
		System.out.println(c.hasCycle());
	}
}
