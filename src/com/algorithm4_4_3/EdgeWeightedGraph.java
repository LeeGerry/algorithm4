package com.algorithm4_4_3;

import java.io.File;
import java.util.Iterator;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
/**
 * 加权无向图的模型
 */
public class EdgeWeightedGraph {
	private int vCount;		//顶点总数
	private int eCount;		//边的总数
	private Bag<Edge>[] adj;//邻接表
	public int getVCount(){return vCount;}
	public int getECount(){return eCount;}
	public EdgeWeightedGraph(int v){
		this.vCount = v;
		this.eCount = 0;
		adj = new Bag[v];
		for(int i = 0; i<v; i++)//构造函数中初始化邻接表数组
			adj[i] = new Bag<>();
	}
	public EdgeWeightedGraph(In in){
		this(in.readInt());
		int e = in.readInt();
		for(int i = 0; i < e; i++){//构造函数遍历每一条边
			int v = in.readInt();//顶点1
			int w = in.readInt();//顶点2
			double weight = in.readDouble();//权重
			Edge edge = new Edge(v, w, weight);
			addEdge(edge);
		}
	}
	//向邻接表中增加一条边
	private void addEdge(Edge e) {
		int v = e.either(), w = e.another(v);
		adj[v].add(e);
		adj[w].add(e);
		eCount++;
	}
	//顶点v的邻接表
	public Iterable<Edge> adj(int v){return adj[v];}
	//加权无向图中的所有边
	public Iterable<Edge> edges(){
		Bag<Edge> b = new Bag<>();
		for(int v = 0; v< vCount; v++)
			for(Edge e: adj[v])
				if(e.another(v) > v)	b.add(e);
		return b;
	}
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("V count: ").append(this.vCount)
			.append("\n")
			.append("E count: ").append(this.eCount)
			.append("\n");
		for(int i = 0; i<vCount; i++){
			Bag<Edge> b = adj[i];
			sb.append(i+":");
			for(Edge e: b){
				int v = e.either();
				sb.append(v+"->"+e.another(v)+"("+e.weight()+")    ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	public static void main(String[] args) {
		String f = EdgeWeightedGraph.class.getClassLoader().getResource("tinyEWG.txt").getPath(); 
		In in = new In(new File(f));
		EdgeWeightedGraph g = new EdgeWeightedGraph(in);
		System.out.println("测试toString: "+g.toString());
		System.out.println("============================");
		System.out.println("测试edges():");
		Iterator<Edge> it = g.edges().iterator();
		while(it.hasNext()){
			System.out.println(it.next().toString());
		}
		System.out.println("============================");
		System.out.println("测试adj(0):");
		Iterator<Edge> ite = g.adj(0).iterator();
		while(ite.hasNext())
			System.out.println(ite.next());
	}
}
