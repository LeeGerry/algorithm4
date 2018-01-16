package me.auburn.edu.alg4.ch4_1;

import java.io.File;
import java.util.LinkedList;

import edu.princeton.cs.algs4.In;

/**
 * 图的深度优先搜索算法
 */
public class DepthFirstSearch {
	private int count;
	private boolean[] marked; // 数组存储每个顶点是否被遍历过
	/**
	 * 从顶点s开始对g进行深搜
	 * @param g
	 * @param s
	 */
	public DepthFirstSearch(Graph g, int s) {
		marked = new boolean[g.getVertexCount()];
		dfs(g, s);
	}
	/** 深搜*/
	private void dfs(Graph g, int s) {
		marked[s] = true;					// 1.标记顶点s
		count++;							// 2.count数加一
		LinkedList<Integer> list = g.adj(s);// 3.获取s的邻接表
		for(int w: list)					// 4.对邻接表进行遍历
			if(!isMarked(w))	dfs(g,w);	// 5.如果遍历到的顶点没有被标记过，对该顶点继续递归深搜
	}
	/** 顶点w是否和起点s相连通*/
	public boolean isMarked(int w){return marked[w];}
	
	/** 与起点s连通的顶点数量*/
	public int count(){return count;}
	
	public static void main(String[] args) {
		String dir = DepthFirstSearch.class.getPackage().getName().replace(".", "/");
		String path = DepthFirstSearch.class.getClassLoader().getResource(dir+"/tinyG.txt").getPath();
		In in = new In(new File(path));
		Graph g = new Graph(in);
		int start = 0;
		DepthFirstSearch search = new DepthFirstSearch(g, start);
		System.out.print("start vertex: "+ start+". ");
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i< g.getVertexCount(); i++)
			if(search.isMarked(i)) sb.append(" "+ i);
		System.out.println("Connected " + sb.toString());
		// 如果和s连通的顶点数量和图的顶点数量相同，说明是连通图
		if(search.count() == g.getVertexCount())	System.out.println("g is a connected graph.");
		else System.out.println("g is not a connected graph.");
	}
}
