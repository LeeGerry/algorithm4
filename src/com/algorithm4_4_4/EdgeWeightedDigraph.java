package com.algorithm4_4_4;

import java.io.File;
import java.util.Iterator;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

/**
 * 加权有向图的数据类型
 */
public class EdgeWeightedDigraph {
	private int vCount;		//顶点总数
	private int eCount;		//边的总数
	private Bag<DirectedEdge>[] adj;//邻接表
	public EdgeWeightedDigraph(int v){
		this.vCount = v;
		this.eCount = 0;
		adj = new Bag[v];
		for(int i = 0;i<v;i++)
			adj[i] = new Bag<>();
	}
	public EdgeWeightedDigraph(In in){
		this(in.readInt());
		int e = in.readInt();
		for(int i = 0; i<e;i++){
			int v = in.readInt();
			int w = in.readInt();
			double weight = in.readDouble();
			DirectedEdge edge = new DirectedEdge(v, w, weight);
			addEdge(edge);
		}
	}
	public void addEdge(DirectedEdge e){
		adj[e.from()].add(e);
		this.eCount++;
	}
	public Iterable<DirectedEdge> adj(int v){ return adj[v];}
	public Iterable<DirectedEdge> edges(){
		Bag<DirectedEdge> bag = new Bag<>();
		for(int i=0;i<vCount;i++){
			for(DirectedEdge e: adj[i])
				bag.add(e);
		}
		return bag;
	}
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("顶点数:"+vCount).append("\n").append("边数:"+eCount).append("\n");
		Iterator<DirectedEdge> it = edges().iterator();
		while(it.hasNext())
			sb.append(it.next().toString()+"\n");
		return sb.toString();
	}
	public int getVCount(){return vCount;}
	public int getECount(){return eCount;}
	public static void main(String[] args) {
		String path = EdgeWeightedDigraph.class.getClassLoader().getResource("com/algorithm4_4_4/tinyEWG.txt").getPath();
		In in = new In(new File(path));
		EdgeWeightedDigraph graph = new EdgeWeightedDigraph(in);
		System.out.println("所构建的加权有向图为:\n"+graph.toString());
		System.out.println("测试adj(0)");
		Iterator<DirectedEdge> it = graph.adj(0).iterator();
		while(it.hasNext())
			System.out.println(it.next());
	}
}
