package com.algorithm4_4_1;

import java.io.File;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * 深度优先搜索
 */
public class DepthFirstSearch {
	private boolean[] marked;
	private int count;
	public DepthFirstSearch(Graph g, int s){
		marked = new boolean[g.getVCount()];
		dfs(g, s);
	}
	private void dfs(Graph g, int s) {
		marked[s] = true;
		StdOut.println(s + "被标记");
		count++;
		for(int w: g.adj(s)){
			System.out.println(w + "被扫描到");
			if(!marked[w]) {
				dfs(g, w);
			}else{
				System.out.println(w + "已经标记过");
			}
		}
			
	}
	public boolean marked(int w)	{return marked[w];}
	public int count()	{return count;}
	public static void main(String[] args) {
		In in = new In(new File("/Users/liguorui/Documents/javaee/algorith4/src/com/algorithm4/tinyG.txt"));
		Graph g = new Graph(in);
		DepthFirstSearch s = new DepthFirstSearch(g, 0);//从顶点0开始深度优先搜索
	}
}
