package com.algorithm4_4_4;

import edu.princeton.cs.algs4.Stack;

/**
 * 加权有向图 最短路径查找查找API
 */
public class SP {
	private int start;				//起点
	private EdgeWeightedDigraph g;	//加权有向图
	private double[] distTo;
	private DirectedEdge[] edgeTo;
	public SP(EdgeWeightedDigraph g, int s){
		this.g = g;
		this.start = s;
	}
	//从顶点start到v得距离，如果不存在则返回无穷大
	public double distTo(int v){
		return distTo[v];
	}
	//是否存在从顶点s到v得路径
	public boolean hasPathTo(int v){
		return distTo[v] < Double.POSITIVE_INFINITY;
	}
	//从顶点s到v得路径，如果不存在，则为null
	public Iterable<DirectedEdge> pathTo(int v){
		if(!hasPathTo(v))	return null;
		Stack<DirectedEdge> path = new Stack<>();
		for(DirectedEdge e = edgeTo[v]; e!= null;e= edgeTo[e.from()])
			path.push(e);
		return path;
	}
}
