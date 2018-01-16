package com.algorithm4_4_2;

import java.io.File;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;

public class DirectedCycle {
	private boolean[] marked;
	private int[] edgeTo;
	private Stack<Integer> cycle;	//有向环中的所有顶点（若存在）
	private boolean[] onStack;		//递归调用的栈上的所有顶点
	
	public DirectedCycle(Digraph g){
		onStack = new boolean[g.getVCount()];
		edgeTo = new int[g.getVCount()];
		marked = new boolean[g.getVCount()];
		for(int i = 0; i< g.getVCount();i++)
			if(!marked[i]) dfs(g, i);
	}
	public boolean hasCycle(){return cycle != null;}
	public Iterable<Integer> cycle(){return cycle;}
	private void dfs(Digraph g, int v) {
		onStack[v] = true;
		marked[v] = true;
		for(int w: g.adj(v)){
			if(this.hasCycle())	return;
			else if(!marked[w]){
				edgeTo[w] = v;
				dfs(g, w);
			}else if(onStack[w]){
				cycle = new Stack<>();
				for(int x = v; x!= w; x=edgeTo[x])
					cycle.push(x);
				cycle.push(w);
				cycle.push(v);
			}
		}
		onStack[v] = false;
	}
	public static void main(String[] args) {
		String path = "/Users/liguorui/Documents/javaee/algorith4/src/com/algorithm4/digraph/tinyDG.txt";
		DirectedCycle dc = new DirectedCycle(new Digraph(new In(new File(path))));
		System.out.println(dc.hasCycle());
		System.out.println(dc.cycle().toString());
	}
}
