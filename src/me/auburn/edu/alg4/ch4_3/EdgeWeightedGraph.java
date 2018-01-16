package me.auburn.edu.alg4.ch4_3;

import java.util.LinkedList;

import me.auburn.edu.alg4.ch4_1.Config;

/**
 * 加权无向图
 */
public class EdgeWeightedGraph {
	private int vCount; // 顶点总数
	private int eCount; // 边的总数
	private LinkedList<Edge>[] adj; // 邻接表

	public EdgeWeightedGraph(int v) {
		this.vCount = v;
		this.eCount = 0;
		this.adj = new LinkedList[v];
		for (int i = 0; i < v; i++)
			adj[i] = new LinkedList<>();
	}

	public EdgeWeightedGraph(int v, double[][] edges) {
		this(v);
		int e = edges.length;
		for (int i = 0; i < e; i++) {
			int start = (int) edges[i][0];
			int end = (int) edges[i][1];
			double weight = edges[i][2];
			Edge ed = new Edge(start, end, weight);
			addEdge(ed);
		}
	}
	public int getVertexCount(){return vCount;}
	public int getEdgeCount(){return eCount;}
	private void addEdge(Edge edge) {
		int v = edge.either(), w = edge.another(v);
		adj[v].add(edge);
		adj[w].add(edge);
		eCount++;
	}

	/** 顶点v的邻接表 */
	public Iterable<Edge> adj(int v) {
		return adj[v];
	}
	/** 加权无向图中的所有边*/
	public Iterable<Edge> edges() {
		LinkedList<Edge> edges = new LinkedList<>();
		for (int v = 0; v < vCount; v++) 
			for (Edge e : adj(v)) 
				if(e.another(v) > v)	edges.add(e);
		return edges;
	}
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("v count: ").append(vCount).append(Config.NEWLINE)
		.append("e count: ").append(eCount).append(Config.NEWLINE);
		for(Edge e:edges())
			sb.append(e.toString()).append(Config.NEWLINE);
		return sb.toString();
	}
	public static void main(String[] args) {
		EdgeWeightedGraph g = new EdgeWeightedGraph(8, Config.directedWeightEdges);
		System.out.println(g.toString());
	}
}
