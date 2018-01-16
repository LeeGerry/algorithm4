package com.algorithm4_4_2;

import java.io.File;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

public class DirectedDFS {
	private boolean[] marked;
	public DirectedDFS(Digraph g, int s){
		marked = new boolean[g.getVCount()];
		dfs(g, s);
	}
	private void dfs(Digraph g, int v) {
		marked[v] = true;
		for(int w: g.adj(v))
			if(!marked[w]) dfs(g, w);
	}
	
	public DirectedDFS(Digraph g, Iterable<Integer> sources){
		marked = new boolean[g.getVCount()];
		for(int s: sources)
			if(!marked[s])	dfs(g, s);
	}
	
	public boolean markde(int v){return marked[v];}
	public static void main(String[] args) {
		String path = "/Users/liguorui/Documents/javaee/algorith4/src/com/algorithm4_4_2/tinyDG.txt";
		Digraph g = new Digraph(new In(new File(path)));
		Bag<Integer> sources = new Bag<>();
		sources.add(1);sources.add(2);sources.add(6);
		DirectedDFS reachable = new DirectedDFS(g, sources);
		for(int v = 0;v<g.getVCount();v++)
			if(reachable.markde(v)) System.out.print(v + " ");
		System.out.println();
	}
}
