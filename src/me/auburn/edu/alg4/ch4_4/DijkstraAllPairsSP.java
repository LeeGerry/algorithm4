package me.auburn.edu.alg4.ch4_4;

import java.io.File;
import java.util.Stack;

import edu.princeton.cs.algs4.In;

/**
 * 任意顶点对之间的最短路径
 */
public class DijkstraAllPairsSP {
	private DijkstraSP[] all;
	public DijkstraAllPairsSP(EdgeWeightedDigraph g){
		all = new DijkstraSP[g.getVcount()];
		for(int i = 0; i< g.getVcount(); i++)
			all[i] = new DijkstraSP(g, i);
	}
	/** 从s到t的最短路径*/
	public Stack<DirectedEdge> path(int s, int t){
		return all[s].pathTo(t);
	}
	/** 从s到t的最短路径总权重*/
	public double dist(int s, int t){
		return all[s].distTo(t);
	}
	public static void main(String[] args) {
		String dir = DijkstraAllPairsSP.class.getPackage().getName().replace(".", "/");
		String path = DijkstraAllPairsSP.class.getClassLoader().getResource(dir+"/tinyEWG.txt").getPath();
		In in = new In(new File(path));
		EdgeWeightedDigraph g = new EdgeWeightedDigraph(in);
		DijkstraAllPairsSP sp = new DijkstraAllPairsSP(g);
		int start = 0, end = 6;
		System.out.println(start + " to "+ end +": "+ sp.dist(start, end));
		System.out.println(sp.path(start, end));
	}
}
