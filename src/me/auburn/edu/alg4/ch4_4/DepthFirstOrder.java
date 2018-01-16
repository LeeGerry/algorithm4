package me.auburn.edu.alg4.ch4_4;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import edu.princeton.cs.algs4.In;
import me.auburn.edu.alg4.ch4_1.Config;

/**
 * 图的深搜之前中后序遍历
 */
public class DepthFirstOrder {
	private boolean[] marked;
	private Queue<Integer> pre; // 所有顶点的前序排列
	private Queue<Integer> post; // 所有顶点的后序排列
	private Stack<Integer> reversePost;// 所有顶点的逆后序排列
	public DepthFirstOrder(EdgeWeightedDigraph g){
		pre = new LinkedList<>();
		post = new LinkedList<>();
		reversePost = new Stack<>();
		marked = new boolean[g.getVcount()];
		for(int v = 0; v<g.getVcount();v++)
			if(!marked[v]) 	dfs(g, v);
	}
	private void dfs(EdgeWeightedDigraph g, int v){
		pre.offer(v);
		marked[v] = true;
		Iterable<DirectedEdge> adj = g.adj(v);
		for(DirectedEdge w: adj)
			if(!marked[w.to()])	dfs(g, w.to());
		post.offer(v);
		reversePost.push(v);
	}
	public Iterable<Integer> pre(){return pre;}
	public Iterable<Integer> post(){return post;}
	public Stack<Integer> reversePost(){return reversePost;}
	public static void main(String[] args) {
		String dir = DepthFirstOrder.class.getPackage().getName().replace(".", "/");
		String path = DepthFirstOrder.class.getClassLoader().getResource(dir+"/tinyEWDAG.txt").getPath();
		In in = new In(new File(path));
		EdgeWeightedDigraph graph = new EdgeWeightedDigraph(in);
		System.out.println(graph.toString());
		DepthFirstOrder dfo = new DepthFirstOrder(graph);
		System.out.println(dfo.pre());
		System.out.println(dfo.post());
		Stack<Integer> s = dfo.reversePost();
		StringBuilder sb = new StringBuilder();
		while(!s.isEmpty()){
			sb.append(s.pop()).append(" ");
		}
		System.out.println("reversePost: "+sb.toString());
//		int[][] edges = {{1,0}};
//		Digraph d = new Digraph(2,edges);
//		DepthFirstOrder order = new DepthFirstOrder(d);
//		Stack<Integer> stack = order.reversePost();
//		int[] result = new int[stack.size()];
//		int i = 0;
//		for(int v: stack) result[i++] = v; 
//		System.out.println(Arrays.toString(result));
	}
}
