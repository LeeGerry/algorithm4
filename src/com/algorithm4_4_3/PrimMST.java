package com.algorithm4_4_3;

import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMinPQ;
/**
 * Prim算法的即时实现
 */
public class PrimMST {
	private Edge[] edgeTo; // 距离树最近的边
	private double[] distTo; // distTo[w] = edgeTo[w].weight()
	private boolean[] marked; // 如果v在树中则为true
	private IndexMinPQ<Double> pq;// 有效的横切边
	public PrimMST(EdgeWeightedGraph g){
		edgeTo = new Edge[g.getVCount()];
		distTo = new double[g.getVCount()];
		marked = new boolean[g.getVCount()];
		for(int v = 0; v<g.getVCount();v++)
			distTo[v] = Double.POSITIVE_INFINITY;
		pq = new IndexMinPQ<>(g.getVCount());
		distTo[0] = 0.0;
		pq.insert(0, 0.0);//用顶点0和权重0初始化pq
		while(!pq.isEmpty())
			visit(g, pq.delMin());
	}
	//将顶点v添加到树中，更新数据
	private void visit(EdgeWeightedGraph g, int v) {
		marked[v] = true;
		for(Edge e: g.adj(v)){
			int w = e.another(v);
			if(marked[w]) continue;		//v-w失效，跳过
			if(e.weight() < distTo[w]){
				edgeTo[w] = e;//连接w和树的最佳边变为e
				distTo[w] = e.weight();//更新权重
				if(pq.contains(w))	pq.changeKey(w, distTo[w]);
				else				pq.insert(w, distTo[w]);
			}
		}
	}
	public Iterable<Edge> edges(){
		ArrayList<Edge> edges = new ArrayList<>();
		for(Edge e: edgeTo){
			if(e!=null)
				edges.add(e);
		}
		return edges;
	}
	public double weight(){
		double result = 0.0d;
		for(double d: distTo){
			result += d;
		}
		NumberFormat d = DecimalFormat.getInstance();
		return Double.parseDouble(d.format(result));
	}
	
	public static void main(String[] args) {
		String f = EdgeWeightedGraph.class.getClassLoader().getResource("com/algorithm4_4_3/tinyEWG.txt").getPath(); 
		In in = new In(new File(f));
		EdgeWeightedGraph g = new EdgeWeightedGraph(in);
		PrimMST pm = new PrimMST(g);
		System.out.println("最小生成树为:");
		Iterable<Edge> edges = pm.edges();
		for(Edge e: edges){
			System.out.println(e);
		}
		System.out.println("总权重："+pm.weight());
	}
}
