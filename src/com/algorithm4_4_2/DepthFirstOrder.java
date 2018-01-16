package com.algorithm4_4_2;

import java.io.File;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class DepthFirstOrder {
	private boolean[] marked;
	private Queue<Integer> pre;//所有顶点的前序排列
	private Queue<Integer> post;//所有顶点的后序排列
	private Stack<Integer> reversePost;//所有顶点的逆后序排列
	public DepthFirstOrder(Digraph g){
		pre = new Queue<>();
		post = new Queue<>();
		reversePost = new Stack<>();
		
		marked = new boolean[g.getVCount()];
		for(int v = 0;v<g.getVCount();v++)
			if(!marked[v]) dfs(g, v);
	}
	private void dfs(Digraph g, int v) {
		pre.enqueue(v);
		marked[v] = true;
		for(int w: g.adj(v))
			if(!marked[w])	dfs(g, w);
		post.enqueue(v);
		reversePost.push(v);
	}
	public Iterable<Integer> pre(){return pre;}
	public Iterable<Integer> post(){return post;}
	public Iterable<Integer> reversePost(){return reversePost;}
	public static void main(String[] args) {
		String path = "/Users/liguorui/Documents/javaee/algorith4/src/com/algorithm4/digraph/tinyDG.txt";
		DepthFirstOrder dfo = new DepthFirstOrder(new Digraph(new In(new File(path))));
		System.out.println(dfo.pre());
		System.out.println(dfo.post());
		System.out.println(dfo.reversePost());
	}
}
