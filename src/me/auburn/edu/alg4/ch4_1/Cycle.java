package me.auburn.edu.alg4.ch4_1;

import java.io.File;

import edu.princeton.cs.algs4.In;

/**
 * 给定的图是无环图吗
 * 检测自环:假设没有自环，没有平行边
 */
public class Cycle {
	private boolean[] marked;
	private boolean hasCycle;
	public Cycle(Graph g){
		marked = new boolean[g.getVertexCount()];
		for(int i = 0;i<g.getVertexCount();i++)
			if(!marked[i])	dfs(g, i, i);
	}
	private void dfs(Graph g, int v, int u) {
		marked[v] = true;
		for(int w: g.adj(v))
			if(!marked[w])	dfs(g, w, v); // 若w没被标记过，那么从w继续递归深搜，把w的父节点作为第二参数
			else if(w != u) hasCycle = true; // 若w被标记过，那么若无环，w必然和父节点相同，否则就是有环
	}
	/** 是否含有环*/
	public boolean hasCycle(){return hasCycle;}
	public static void main(String[] args) {
		String dir = Cycle.class.getPackage().getName().replace(".", "/");
		String pathCycle = Cycle.class.getClassLoader().getResource(dir+"/tinyG.txt").getPath();
		String pathNoCycle = Cycle.class.getClassLoader().getResource(dir+"/cycle_test.txt").getPath();
		In in = new In(new File(pathCycle));
		Graph g = new Graph(in);
		Cycle c = new Cycle(g);
		System.out.println(c.hasCycle());
		In in2 = new In(new File(pathNoCycle));
		Graph g2 = new Graph(in2);
		Cycle c2 = new Cycle(g2);
		System.out.println(c2.hasCycle());
	}
}
