package me.auburn.edu.alg4.ch4_4;

import java.io.File;
import java.util.Stack;

import edu.princeton.cs.algs4.In;

/**
 * 有向图中的环
 */
public class DirectedCycle {
	private boolean[] marked;
	private int[] edgeTo;
	private Stack<Integer> cycle;	// 有向环中的所有顶点
	private boolean[] onStack;		// 递归调用的栈上的所有顶点
	public DirectedCycle(EdgeWeightedDigraph digraph){
		onStack = new boolean[digraph.getVcount()];
		edgeTo = new int[digraph.getVcount()];
		marked = new boolean[digraph.getVcount()];
		for(int i = 0; i< digraph.getVcount(); i++){
//			Iterable<DirectedEdge> edges = digraph.adj(i);
//			for(DirectedEdge edge: edges){
//				int from = edge.from(); 
				if(!marked[i]) dfs(digraph, i);
//			}
		}
	}
	private void dfs(EdgeWeightedDigraph graph, int v) {
		onStack[v] = true;				// 在当前栈标记v
		marked[v] = true;				// mark v
		Iterable<DirectedEdge> edges = graph.adj(v);
		for(DirectedEdge e : edges){
			if(this.hasCycle())	return;	// 若有环，直接返回
			else if(!marked[e.to()]){			// 无环，且当前节点没有被mark
				edgeTo[e.to()] = v;			// 设置进当前路径
				dfs(graph, e.to());			// 继续深搜
			}else if(onStack[e.to()]){		// 无环，且当前节点被mark了，然后当前节点又出现在当前栈，说明产生了环
				cycle = new Stack<>();	
				for(int x = v; x!=e.to(); x=edgeTo[x])	// 把当前产生的环的所有顶点入栈
					cycle.push(x);
				cycle.push(e.to());						// 终点入栈
				cycle.push(v);						// 起点入栈，起点和终点相同
			}
		}
		onStack[v] = false;							// 恢复原状
	}
	public boolean hasCycle() {return cycle != null;}
	public Stack<Integer> getCycle(){return cycle;}
	public static void main(String[] args) {
		String dir = DirectedCycle.class.getPackage().getName().replace(".", "/");
		String path = DirectedCycle.class.getClassLoader().getResource(dir+"/tinyEWDAG.txt").getPath();
		In in = new In(new File(path));
		EdgeWeightedDigraph graph = new EdgeWeightedDigraph(in);
		DirectedCycle dc = new DirectedCycle(graph);
		System.out.println(dc.hasCycle());
	}
}
