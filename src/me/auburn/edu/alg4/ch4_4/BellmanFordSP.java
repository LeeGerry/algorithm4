package me.auburn.edu.alg4.ch4_4;

import java.util.LinkedList;

import me.auburn.edu.alg4.ch4_3.Edge;

/**
 * 基于队列的Bellman-Ford算法
 */
public class BellmanFordSP {
	private double[] distTo;			// 从起点到某个顶点的路径长度
	private DirectedEdge[] edgeTo;		// 从起点到某个顶点的最后一条边
	private boolean[] onQ;				// 该顶点是否存在于队列中
	private LinkedList<Integer> queue;		// 正在被放松的顶点
	private int cost;					// relax()的调用次数
	private Iterable<DirectedEdge> cycle;//edgeTo[]中是否有负权重环
	public BellmanFordSP(EdgeWeightedDigraph g, int s){
		int vCount = g.getVcount();
		distTo = new double[vCount];
		edgeTo = new DirectedEdge[vCount];
		queue = new LinkedList<Integer>();
		for(int v = 0; v<vCount; v++)
			distTo[v] = Double.POSITIVE_INFINITY;
		distTo[0] = 0.0;
		queue.offer(s);
		onQ[s] = true;
		while(!queue.isEmpty() && !hasNegativeCycle()){
			int v = queue.removeFirst();
			onQ[v] = false;
			relax(g, v);
		}
		
	}
	private boolean hasNegativeCycle() {
		return false;
	}
	private void relax(EdgeWeightedDigraph g, int v) {
	}
	public boolean hasPathTo(int v){
		return false;
	}
	private void findNegativeCycle(){
		
	}
	public Iterable<Edge> negativeCycle(){
		return null;
	}
	public static void main(String[] args) {
		
	}
}
