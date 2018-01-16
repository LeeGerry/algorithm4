package com.algorithm4_4_4;

/**
 * 无环加权有向图的最短路径算法
 */
public class AcyclicSP {
	private DirectedEdge[] edgeTo;
	private double[] distTo;
	public AcyclicSP(EdgeWeightedDigraph g, int s){
		edgeTo = new DirectedEdge[g.getVCount()];
		distTo = new double[g.getVCount()];
		for(int v =0;v<g.getVCount();v++)
			distTo[v] = Double.POSITIVE_INFINITY;
		distTo[s] = 0.0;
		
	}
}
