package me.auburn.edu.alg4.ch4_4;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;

import edu.princeton.cs.algs4.In;
import me.auburn.edu.alg4.ch4_1.Config;

/**
 * 加权有向图
 */
public class EdgeWeightedDigraph {
	private int vCount;
	private int eCount;
	private LinkedList<DirectedEdge>[] adj;
	public EdgeWeightedDigraph(int v){
		this.vCount = v;
		this.eCount = 0;
		adj = new LinkedList[v];
		for(int i = 0; i<v; i++)
			adj[i] = new LinkedList<>();
	}
	public EdgeWeightedDigraph(In in){
		this(in.readInt());
		int e = in.readInt();
		for(int i = 0; i<e; i++){
			int start = in.readInt();
			int end = in.readInt();
			double weight = in.readDouble();
			DirectedEdge edge = new DirectedEdge(start, end, weight);
			addEdge(edge);
		}
	}
	public void addEdge(DirectedEdge edge) {
		adj[edge.from()].add(edge);
		this.eCount++;
	}
	public Iterable<DirectedEdge> adj(int v){return adj[v];}
	public Iterable<DirectedEdge> edges(){
		LinkedList<DirectedEdge> result = new LinkedList<>();
		for(int i = 0; i<vCount; i++){
			for(DirectedEdge e: adj[i])
				result.add(e);
		}
		return result;
	}
	public int getVcount(){return this.vCount;}
	public int getEcount(){return this.eCount;}
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("vetex count: "+ vCount).append(Config.NEWLINE).append("edge count: "+eCount).append(Config.NEWLINE);
		Iterator<DirectedEdge> it = edges().iterator();
		while(it.hasNext())
			sb.append(it.next().toString()).append(Config.NEWLINE);
		return sb.toString();
	}
	public static void main(String[] args) {
		String dir = EdgeWeightedDigraph.class.getPackage().getName().replace(".", "/");
		String path = EdgeWeightedDigraph.class.getClassLoader().getResource(dir+"/tinyEWG.txt").getPath();
		In in = new In(new File(path));
		EdgeWeightedDigraph graph = new EdgeWeightedDigraph(in);
		System.out.println(graph.toString());
		System.out.println("adj(0):");
		Iterator<DirectedEdge> it = graph.adj(0).iterator();
		while(it.hasNext())
			System.out.println(it.next().toString());
	}
}
