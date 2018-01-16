package com.algorithm4_4_4;

import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.Stack;

/**
 * 最短路径的Dijkstra算法实现
 */
public class DijkstraSP {
	private DirectedEdge[] edgeTo; // edgeTo[v] = last edge on shortest s->v
									// path
	private double[] distTo; // distTo[v] = distance of shortest s->v path
	private IndexMinPQ<Double> pq; // priority queue of vertices

	public DijkstraSP(EdgeWeightedDigraph g, int s) {
		edgeTo = new DirectedEdge[g.getVCount()];
		distTo = new double[g.getVCount()];
		pq = new IndexMinPQ<>(g.getVCount());
		for (int v = 0; v < g.getVCount(); v++) // 对distTo[]进行初始化，初始化为无穷大
			distTo[v] = Double.POSITIVE_INFINITY;
		distTo[s] = 0.0; // 起点到起点设置为0
		pq.insert(s, 0.0); // 起点加入队列
		while (!pq.isEmpty()) {
			int v = pq.delMin();// 取出最小权重的顶点
			for (DirectedEdge e : g.adj(v))// 遍历该顶点的邻接表的每条边
				relax(e); // 进行放松
		}
	}

	private void relax(DirectedEdge e) {
		int v = e.from(), w = e.to();
		if (distTo[w] > distTo[v] + e.weight()) {// 如果 新扫描到的边的权重加上之前路径距离 小于
													// 旧距离，则更新
			distTo[w] = distTo[v] + e.weight();// 把旧距离换上新的
			edgeTo[w] = e; // 到达w的边也更新一下
			if (pq.contains(w))
				pq.decreaseKey(w, distTo(w));// 更新队列
			else
				pq.insert(w, distTo(w));
		}
	}

	// return the length of a shortest path from the source vertex
	public double distTo(int v) {
		NumberFormat d = DecimalFormat.getInstance();
		return Double.parseDouble(d.format(distTo[v]));
	}

	public boolean hasPathTo(int v) {
		return distTo[v] < Double.POSITIVE_INFINITY;
	}

	// return a shortest path from the source vertex
	public Iterable<DirectedEdge> pathTo(int v) {
		if (!hasPathTo(v))
			return null;// 如果没有通路返回空
		Stack<DirectedEdge> path = new Stack<>();// 用一个栈来存放路径
		for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()])
			path.push(e);// 遍历edgeTo数组，从终点开始，倒着找，一直找到起点，一一入栈
		return path;// 栈作为结果返回
	}

	public static void main(String[] args) {
		String path = DijkstraSP.class.getClassLoader().getResource("com/algorithm4_4_4/tinyEWG.txt")
				.getPath();
		In in = new In(new File(path));
		EdgeWeightedDigraph g = new EdgeWeightedDigraph(in);
		int start = 0;
		DijkstraSP dsp = new DijkstraSP(g, start);
		for (int t = 0; t < g.getVCount(); t++) {
			if (dsp.hasPathTo(t)) {
				System.out.print(String.format("%d to %d (%.2f) ", start, t, dsp.distTo[t]));
				for (DirectedEdge e : dsp.pathTo(t))
					System.out.print(e + " ");
			} else
				System.out.println(start + "to" + t + "no path");
			System.out.println();
		}
	}
}
