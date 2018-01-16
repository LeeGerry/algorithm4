package me.auburn.edu.alg4.ch4_2;

import java.util.LinkedList;

import me.auburn.edu.alg4.ch4_1.Config;
import me.auburn.edu.alg4.ch4_1.GraphUtils;

/**
 * 有向图
 */
public class Digraph {
	private int vCount;
	private int eCount;
	private LinkedList[] adj;
	public Digraph(int v){
		this.vCount = v;
		this.eCount = 0;
		adj = new LinkedList[v];
		for(int i = 0;i<v;i++)
			adj[i] = new LinkedList<>();
	}
	public Digraph(int v, int[][] edges){
		this(v);
		int e = edges.length;
		for(int i = 0; i<e;i++){
			int start = edges[i][0];
			int end = edges[i][1];
			addEdge(start, end);
		}
	}
	public void addEdge(int start, int end) {
		adj[start].add(end);
		eCount++;
	}
	public int getVertexCount(){ return vCount; }
	public int getEdgeCount(){ return eCount; }
	/** v指出的所有边所连接的顶点*/
	public LinkedList<Integer> adj(int v){return adj[v];}
	/** 返回该图的反向图*/
	public Digraph reverse(){
		Digraph reverse = new Digraph(this.vCount);
		for(int start = 0; start < vCount; start++){
			for(int end: adj(start))
				reverse.addEdge(end, start);
		}
		return reverse;
	}
	public static void main(String[] args) {
		Digraph d = new Digraph(13, Config.directEdges);
		System.out.println(DigraphUtils.graphToString(d));
		System.out.println(DigraphUtils.graphToString(d.reverse()));
	}
}
