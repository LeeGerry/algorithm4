package com.algorithm4_4_3;


import java.io.File;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
/**
 * 最小生成树的Prim算法的延时实现
 */
public class LazyPrimMST {
	private boolean[] marked;	//最小生成树的顶点
	private Queue<Edge> mst;	//最小生成树的边
	private MinPQ<Edge> pq;		//横切边队列
	public LazyPrimMST(EdgeWeightedGraph g){
		pq = new MinPQ<>();
		marked = new boolean[g.getVCount()];
		mst = new Queue<Edge>();
		visit(g, 0);		//假设g是连通的
		while(!pq.isEmpty()){
			Edge e = pq.delMin(); 				//从队列中得到权重最小的边
			int v = e.either(), w = e.another(v);
			if(marked[v] && marked[w]) continue;//跳过失效的边
			mst.enqueue(e);						//将边添加到最小生成树中
			if(!marked[v]) visit(g, v);			//将顶点v添加到树中
			if(!marked[w]) visit(g, w);			//将顶点w添加到树中
		}
	}
	//标记顶点v并将所有链接v和未被标记顶点的边加入队列
	private void visit(EdgeWeightedGraph g, int v) {
		marked[v] = true;
		for(Edge e: g.adj(v))
			if(!marked[e.another(v)]) pq.insert(e);
	}
	
	public Iterable<Edge> edges(){return mst;}
	public double weight(){
		double weight = 0;
		for(Edge e: mst)
			weight += e.weight();
		return weight;
	}
	public static void main(String[] args) {
		String f = EdgeWeightedGraph.class.getClassLoader().getResource("tinyEWG.txt").getPath(); 
		In in = new In(new File(f));
		EdgeWeightedGraph g = new EdgeWeightedGraph(in);
		LazyPrimMST lazy_mst = new LazyPrimMST(g);
		Iterable<Edge> mst = lazy_mst.edges();
		System.out.println("最小生成树树的边:");
		for(Edge e:mst)
			System.out.println(e);
		System.out.println("总权重:"+lazy_mst.weight());
	}
}
