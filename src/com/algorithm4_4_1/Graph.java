package com.algorithm4_4_1;

import java.io.File;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
/**
 * 无向图的数据类型
 */
public class Graph {
	private int vCount; //顶点数
	private int eCount; //边数
	private Bag<Integer>[] adj;
	public Graph(int vCount){
		this.vCount = vCount; this.eCount = 0;
		adj = new Bag[vCount]; //初始化邻接表数组
		for (int i = 0; i < vCount; i++) {
			adj[i] = new Bag<Integer>(); //为每个顶点初始化邻接表
		}
	}
	public Graph(In in){
		this(in.readInt());
		int e = in.readInt(); 		//读出边数
		for(int i = 0;i<e;i++){
			int v = in.readInt(); 	//读一个顶点
			int w = in.readInt(); 	//读另一顶点
			addEdge(v, w); 			//增加一条边
		}
	}
	public void addEdge(int v, int w) {
		adj[v].add(w); //在顶点v的邻接表中增加w
		adj[w].add(v); //在顶点w的邻接表中增加v
		this.eCount++; //边数加一
	}
	// 计算v的度
	public static int degree(Graph g, int v){
		int degree = 0;
		for(int w: g.adj(v))	degree++;
		return degree;
	}
	// 计算最大的度
	public static int maxDegree(Graph g){
		int max = 0;
		for(int v = 0; v<g.getVCount();v++){
			int d = degree(g, v);
			max = d>max?d:max;
		}
		return max;
	}
	// 计算平均度
	public static int avgDegree(Graph g){return 2*g.getECount()/g.getVCount();}
	//计算自环个数
	public static int numberOfSelfLoops(Graph g){
		int count = 0;
		for(int v=0;v<g.getVCount();v++)
			for(int w:g.adj(v))
				if(v == w) count++;
		return count/2;//每一条边计算了2遍
	}
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(vCount+ " vertices, "+ eCount+" edges\n");
		for(int v=0; v<vCount;v++){
			sb.append(v+": ");
			for(int w: adj(v))
				sb.append(w + " ");
			sb.append("\n");
		}
		return sb.toString();
	}
	public int getVCount(){return this.vCount;}
	public int getECount(){return this.eCount;}
	public Iterable<Integer> adj(int v){return adj[v];} //顶点v的邻接表迭代器
	public static void main(String[] args) {
		String path = Graph.class.getClassLoader().getResource("com/algorithm4_4_1/tinyG.txt").getPath();
		In in = new In(new File(path));
		Graph g = new Graph(in);
		System.out.println(g.toString());
	}
}
