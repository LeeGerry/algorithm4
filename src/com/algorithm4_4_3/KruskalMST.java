package com.algorithm4_4_3;

import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.UF;

/**
 * 最小生成树的Kruskal算法
 */
public class KruskalMST {
	private Queue<Edge> mst;	//最小生成树的边
	public KruskalMST(EdgeWeightedGraph g){
		mst = new Queue<>();
		MinPQ<Edge> pq = new MinPQ<Edge>();
		for(Edge e: g.edges())
			pq.insert(e);		//遍历边构造优先级队列
		UF uf = new UF(g.getVCount());//用并查集来保存已扫描到的顶点
		//循环条件：队列不空 并且 最小生成树的队列中边的条数小于定点数-1
		while(!pq.isEmpty() && mst.size() < g.getVCount()-1){
			Edge e = pq.delMin();//最小权重边出对
			int v = e.either(), w = e.another(v);//得到两个顶点
			if(uf.connected(v, w)) continue;//若两个顶点已经连通，也就是失效边，直接跳过
			uf.union(v, w);					//否则的话把这两个顶点连通
			mst.enqueue(e);					//把这条边加入最小生成树
		}
	}
	public Iterable<Edge> edges(){ return mst;}
	public double weight(){
		double result = 0;
		for(Edge e: mst)
			result += e.weight();
		NumberFormat format = DecimalFormat.getInstance();
		return Double.parseDouble(format.format(result));
	}
	public static void main(String[] args) {
		String f = EdgeWeightedGraph.class.getClassLoader().getResource("tinyEWG.txt").getPath(); 
		In in = new In(new File(f));
		EdgeWeightedGraph g = new EdgeWeightedGraph(in);
		KruskalMST kMst = new KruskalMST(g);
		System.out.println("最小生成树：");
		for(Edge e: kMst.edges())
			System.out.println(e);
		System.out.println("总权重："+kMst.weight());
	}
}
