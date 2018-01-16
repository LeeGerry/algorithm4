package me.auburn.edu.alg4.ch4_2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import me.auburn.edu.alg4.ch4_1.Config;

/**
 * 图的深搜之前中后序遍历
 */
public class DepthFirstOrder {
	private boolean[] marked;
	private Queue<Integer> pre; // 所有顶点的前序排列
	private Queue<Integer> post; // 所有顶点的后序排列
	private Stack<Integer> reversePost;// 所有顶点的逆后序排列
	public DepthFirstOrder(Digraph g){
		pre = new LinkedList<>();
		post = new LinkedList<>();
		reversePost = new Stack<>();
		marked = new boolean[g.getVertexCount()];
		for(int v = 0; v<g.getVertexCount();v++)
			if(!marked[v]) 	dfs(g, v);
	}
	private void dfs(Digraph g, int v){
		pre.offer(v);
		marked[v] = true;
		for(int w: g.adj(v))
			if(!marked[w])	dfs(g, w);
		post.offer(v);
		reversePost.push(v);
	}
	public Iterable<Integer> pre(){return pre;}
	public Iterable<Integer> post(){return post;}
	public Stack<Integer> reversePost(){return reversePost;}
	public static void main(String[] args) {
		Digraph d = new Digraph(13, Config.directEdges);
		System.out.println(DigraphUtils.graphToString(d));
		DepthFirstOrder dfo = new DepthFirstOrder(d);
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
