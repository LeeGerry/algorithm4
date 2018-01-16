package me.auburn.edu.alg4.ch4_2;

import java.util.Stack;

import me.auburn.edu.alg4.ch4_1.Config;

/**
 * 拓扑排序
 */
public class Topological {
	private Stack<Integer> order;//顶点的拓扑排序
	public Topological(Digraph d){
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
		String path = "/Users/liguorui/Documents/javaee/algorith4/src/me/auburn/edu/alg4/ch4_2/jobs.txt";
		SymbolGraph sg = new SymbolGraph(path, "/");
		Topological t = new Topological(sg.graph());
		Stack<Integer> s = t.order();
		StringBuilder sb = new StringBuilder();
		while(!s.isEmpty()){
			sb.append(sg.name(s.pop())).append(Config.NEWLINE);
		}
		System.out.println(sb.toString());
	}
}
