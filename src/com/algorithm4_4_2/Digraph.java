package com.algorithm4_4_2;

import java.io.File;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

public class Digraph {
	private int vCount; //顶点数
	private int eCount; //边数
	private Bag<Integer>[] adj;//邻接表数组
	public Digraph(int v){
		this.vCount = v;
		this.eCount = 0;
		adj = new Bag[v];
		for(int i=0;i<v;i++)//初始化邻接表数组
			adj[i] = new Bag<>();
	}
	public Digraph(In in){
		this(in.readInt());
		int e = in.readInt();
		for(int i = 0;i<e;i++){
			int v = in.readInt();
			int w = in.readInt();
			this.addEdge(v, w);
		}
	}
	public int getVCount(){return this.vCount;}
	public int getECount(){return this.eCount;}
	public void addEdge(int v, int w){
		adj[v].add(w);//此处与无向图不同，只增加一条边，是有向边
		eCount++;
	}
	public Iterable<Integer> adj(int v){ return adj[v]; }
	public Digraph reverse(){
		Digraph rev = new Digraph(this.vCount);
		for(int v=0; v<this.vCount; v++)//遍历顶点
			for(int w: adj(v))//遍历顶点的邻接表
				rev.addEdge(w, v);//把顶点和邻接表顶点翻转一下加入新的图
		return rev;
	}
	@Override
	public String toString() {
		String s = this.vCount+ " vertices, "+this.eCount+" edges\n";
		for(int v=0; v<this.vCount; v++){
			s += v + ": ";
			for(int w: this.adj(v))
				s += w + " ";
			s += "\n";
		}
		return s;
	}
	public static void main(String[] args) {
		String path = "/Users/liguorui/Documents/javaee/algorith4/src/com/algorithm4/digraph/tinyDG.txt";
		Digraph d = new Digraph(new In(new File(path)));
		System.out.println(d.toString());
		System.out.println(d.reverse().toString());
	}
}
