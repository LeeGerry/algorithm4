package me.auburn.edu.alg4.ch4_4;

import java.io.File;
import java.text.NumberFormat;
import java.util.Stack;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMinPQ;

/**
 * 最短路径的Dijkstra算法
 */
public class DijkstraSP {
	private DirectedEdge[] edgeTo;		// edgeTo[v]:last edge on shortest s->v path
	private double[] distTo;			// distTo[v]: distance of shortest s->v path
	private IndexMinPQ<Double> pq;		// priority queue of vertices
	public DijkstraSP(EdgeWeightedDigraph graph, int s){
		edgeTo = new DirectedEdge[graph.getVcount()];
		distTo = new double[graph.getVcount()];
		pq = new IndexMinPQ<>(graph.getVcount());
		for(int v = 0; v < graph.getVcount(); v++) 	// 对distTo[]进行初始化为无穷大
			distTo[v] = Double.POSITIVE_INFINITY;
		distTo[0] = 0.0;			// 起点到起点设置为0	
		pq.insert(s, 0.0);			// 起点入队
		while(!pq.isEmpty()){
			int v = pq.delMin();	// 取出最小权重的顶点
			for(DirectedEdge e: graph.adj(v))	// 遍历该顶点的邻接表的每条边
				relax(e);			// 对边进行放松
		}
	}
	private void relax(DirectedEdge e){
		int start = e.from(), end = e.to();
		// 若 新权重（新扫描到的边的权重+之前路径的总权重） 小于 旧权重，则更新
		if(distTo[end] > distTo[start] + e.weight()){	
			distTo[end] = distTo[start] + e.weight();	// 更新旧权重
			edgeTo[end] = e;							// 更新到达end的边
			if(pq.contains(end))	
				pq.decreaseKey(end, distTo[end]);		// 更新队列（含）
			else
				pq.insert(end, distTo[end]);		// 插入新值（不含）
		}
	}
	/** return the length of a shortest path from the source vertex*/
	public double distTo(int v){
		NumberFormat format = NumberFormat.getInstance();
		return Double.parseDouble(format.format(distTo[v]));
	}
	public boolean hasPathTo(int v){
		return distTo[v] < Double.POSITIVE_INFINITY;
	}
	/** return a shortest path from the source vertex*/
	public Stack<DirectedEdge> pathTo(int v){
		if(!hasPathTo(v))	return null; 		// 没有通路则返回空
		Stack<DirectedEdge> path = new Stack<>();	// 用一个栈来存放路径
		// 遍历edgeTo数组，从终点开始找，倒着找，一直找到起点，一一入栈
		for(DirectedEdge e = edgeTo[v]; e!= null; e=edgeTo[e.from()])
			path.push(e);
		return path;
	}
	public static void main(String[] args) {
		String dir = DijkstraSP.class.getPackage().getName().replace(".", "/");
		String path = DijkstraSP.class.getClassLoader().getResource(dir+"/tinyEWG.txt").getPath();
		In in = new In(new File(path));
		EdgeWeightedDigraph graph = new EdgeWeightedDigraph(in);
		int start = 0;
		DijkstraSP sp = new DijkstraSP(graph, start);
		for(int t = 0; t<graph.getVcount(); t++){
			if(sp.hasPathTo(t)){
				System.out.print(String.format("%d to %d (%.2f): ", start, t, sp.distTo(t)));
				Stack<DirectedEdge> stack = sp.pathTo(t);
				while(!stack.isEmpty())
					System.out.print(stack.pop()+ " ");
			}else{
				System.out.println(start + " to "+ t+ " no path.");
			}
			System.out.println();
		}
	}
}
