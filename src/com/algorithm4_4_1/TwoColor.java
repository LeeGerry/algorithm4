package com.algorithm4_4_1;

import java.io.File;

import edu.princeton.cs.algs4.In;

public class TwoColor {
	private boolean[] marked;
	private boolean[] color;
	private boolean isTwoColorable = true;
	public TwoColor(Graph g){
		marked = new boolean[g.getVCount()];
		color = new boolean[g.getVCount()];
		for(int s=0; s<g.getVCount(); s++){
			if(!marked[s])	dfs(g,s);
		}
	}
	private void dfs(Graph g, int v) {
		marked[v] = true;
		for(int w: g.adj(v)){
			if(!marked[w]){
				color[w] = !color[v];
				dfs(g, w);
			}else if(color[w] == color[v]){
				isTwoColorable = false;
			}
		}
	}
	public boolean isBipartite(){
		return isTwoColorable;
	}
	public static void main(String[] args) {
//		String path = "/Users/liguorui/Documents/javaee/algorith4/src/com/algorithm4/cycle_test.txt";
		String path = "/Users/liguorui/Documents/javaee/algorith4/src/com/algorithm4/color_test.txt";
		Graph g = new Graph(new In(new File(path)));
		TwoColor c = new TwoColor(g);
		System.out.println(c.isBipartite());
	}
}
