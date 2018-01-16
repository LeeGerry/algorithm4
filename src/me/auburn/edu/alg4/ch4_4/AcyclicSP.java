package me.auburn.edu.alg4.ch4_4;

import java.io.File;
import java.text.NumberFormat;
import java.util.Stack;

import edu.princeton.cs.algs4.In;

/**
 * 无环加权有向图的最短路径算法
 */
public class AcyclicSP {
	private DirectedEdge[] edgeTo;
	private double[] distTo;
	public AcyclicSP(EdgeWeightedDigraph g, int s){
		edgeTo = new DirectedEdge[g.getVcount()];
		distTo = new double[g.getVcount()];
		for(int v = 0; v<g.getVcount(); v++)
			distTo[v] = Double.POSITIVE_INFINITY;
		distTo[s] = 0.0;
		Topological top = new Topological(g);
		Stack<Integer> stack = top.order();
		while(!stack.isEmpty())
			relax(g, stack.pop());
	}
	private void relax(EdgeWeightedDigraph g, int v) {
		for(DirectedEdge e: g.adj(v)){
			int w = e.to();
			if(distTo[w] > distTo[v] + e.weight()){
				distTo[w] = distTo[v] + e.weight();
				edgeTo[w] = e;
			}
		}
	}
	public double distTo(int v){
		NumberFormat format = NumberFormat.getInstance();
		return Double.parseDouble(format.format(distTo[v]));
	}
	public boolean hasPathTo(int v){
		return distTo[v] < Double.POSITIVE_INFINITY;
	}
	public Stack<DirectedEdge> pathTo(int v){
		if(!hasPathTo(v))	return null;
		Stack<DirectedEdge> path = new Stack<>();
		for(DirectedEdge e = edgeTo[v]; e!=null; e= edgeTo[e.from()])
			path.push(e);
		return path;
	}
	public static void main(String[] args) {
		String dir = AcyclicSP.class.getPackage().getName().replace(".", "/");
		String path = AcyclicSP.class.getClassLoader().getResource(dir+"/tinyEWDAG.txt").getPath();
		In in = new In(new File(path));
		EdgeWeightedDigraph graph = new EdgeWeightedDigraph(in);
		int start = 5;
		AcyclicSP sp = new AcyclicSP(graph, start);
		for(int i = 0; i<graph.getVcount(); i++){
			if(sp.hasPathTo(i)){
				System.out.print(start + " to " + i+" ("+sp.distTo(i)+"): ");
				Stack<DirectedEdge> stack = sp.pathTo(i);
				while(!stack.isEmpty()){
					System.out.print(stack.pop().toString() + " ");
				}
			}else{
				System.out.println(start + " to "+ i + " no path.");
			}
			System.out.println();
		}
	}
}
