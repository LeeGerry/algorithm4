package com.algorithm4_4_4;

import java.io.File;

import edu.princeton.cs.algs4.In;

/**
 * 任意顶点对之间的最短路径
 */
public class DijkstraAllPairsSP {
	private DijkstraSP[] all;

	public DijkstraAllPairsSP(EdgeWeightedDigraph g) {
		all = new DijkstraSP[g.getVCount()];
		for (int i = 0; i < g.getVCount(); i++)
			all[i] = new DijkstraSP(g, i);
	}
	//s 到 t的最短路径
	public Iterable<DirectedEdge> path(int s, int t) {
		return all[s].pathTo(t);
	}
	// s 到 t 的最短路径距离
	public double dist(int s, int t) {
		return all[s].distTo(t);
	}
	public static void main(String[] args) {
		String path = DijkstraAllPairsSP.class.getClassLoader().getResource("com/algorithm4_4_4/tinyEWG.txt")
				.getPath();
		In in = new In(new File(path));
		EdgeWeightedDigraph g = new EdgeWeightedDigraph(in);
		DijkstraAllPairsSP dasp = new DijkstraAllPairsSP(g);
		int start = 0, end = 6;
		System.out.println(start+"to"+end+"("+dasp.dist(start, end)+")");
		System.out.println(dasp.path(start, end));
	}
}
