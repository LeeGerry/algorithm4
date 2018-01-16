package me.auburn.edu.alg4.ch4_1;

import java.io.File;
import java.util.LinkedList;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
/**
 * 广搜找到最短路径
 * 		对于从s可达的任意顶点v，广搜都能找到一条从s到v的最短路径
 * 		（没有其他从s到v的路径所含边比这条路径更少）
 * 广搜所需时间在最坏情况下和（v + e）成正比。
 */
public class BreadthFirstPaths {
	private boolean[] marked;
	private int[] edgeTo;
	private int start;
	public BreadthFirstPaths(Graph g, int s){
		this.start = s;
		marked = new boolean[g.getVertexCount()];
		edgeTo = new int[g.getVertexCount()];
		bfs(g, s);
	}
	private void bfs(Graph g, int s) {
		Queue<Integer> queue = new Queue<>();	
		marked[s] = true; 	// 标记起点
		queue.enqueue(s);	// 起点入队
		while(!queue.isEmpty()){
			int head = queue.dequeue();	// 从队列中取出队首
			LinkedList<Integer> list = g.adj(head);	// 得到队首的邻接表
			for(int w: list){ 	//遍历邻接表
				if(!marked[w]){	// 若当前节点没有被标记过
					edgeTo[w] = head;	// 1.存入路径
					marked[w] = true;	// 2.进行标记
					queue.enqueue(w);	// 3.节点入队
				}
			}
		}
	}
	/** 从起点s到顶点v是否存在通路*/
	public boolean hasPathTo(int v){return marked[v];}
	/** 返回从起点s到顶点v的一条最短路径*/
	public Stack<Integer> pathTo(int v){
		if(!hasPathTo(v))	return null; // 若不存在到v的路径，返回Null
		Stack<Integer> path = new Stack<>();
		for(int x = v; x!=start; x=edgeTo[x])
			path.push(x);
		path.push(start);
		return path;
	}
	public static void main(String[] args) {
		String dir = BreadthFirstPaths.class.getPackage().getName().replace(".", "/");
		String path = BreadthFirstPaths.class.getClassLoader().getResource(dir+"/tinyG.txt").getPath();
		In in = new In(new File(path));
		Graph g = new Graph(in);
		int start = 5;
		BreadthFirstPaths bfPath = new BreadthFirstPaths(g, start);
		for(int i = 0; i<g.getVertexCount();i++){
			if(i == start) continue;
			if(!bfPath.hasPathTo(i)){
				System.out.println(start + " to "+ i + " : not connected.");
				continue;
			}
			StringBuilder sb = new StringBuilder();
			sb.append(start + " to "+ i + " : ");
			Stack<Integer> p = bfPath.pathTo(i);
			while(!p.isEmpty()){
				sb.append(p.pop() + "->");
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.deleteCharAt(sb.length() - 1);
			System.out.println(sb.toString());
		}
	}
}
