package me.auburn.edu.alg4.ch4_4;

import java.io.File;
import java.util.Stack;

import edu.princeton.cs.algs4.In;

/**
 * 拓扑排序
 */
public class Topological {
	private Stack<Integer> order;//顶点的拓扑排序
	public Topological(EdgeWeightedDigraph d){
		DirectedCycle cycleFinder = new DirectedCycle(d);
		if(!cycleFinder.hasCycle()){
			DepthFirstOrder dfs = new DepthFirstOrder(d);
			order = dfs.reversePost();
		}
	}
	/** 拓扑有序的所有顶点*/
	public Stack<Integer> order(){return order;}
	/** g是有向无环图*/
	public boolean isDAG(){return order != null;}
	public static void main(String[] args) {
		String dir = Topological.class.getPackage().getName().replace(".", "/");
		String path = Topological.class.getClassLoader().getResource(dir+"/tinyEWDAG.txt").getPath();
		In in = new In(new File(path));
		EdgeWeightedDigraph graph = new EdgeWeightedDigraph(in);
		Topological t = new Topological(graph);
		Stack<Integer> s = t.order();
		StringBuilder sb = new StringBuilder();
		while(!s.isEmpty()){
			sb.append(s.pop()).append(" ");
		}
		System.out.println(sb.toString());
	}
}
