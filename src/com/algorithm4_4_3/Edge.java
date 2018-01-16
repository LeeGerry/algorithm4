package com.algorithm4_4_3;
/**
 * 带权重的边的数据模型
 */
public class Edge implements Comparable<Edge>{
	private int v;			//顶点1
	private int w;			//顶点2
	private double weight; 	//权重
	
	public Edge(int v, int w, double weight){
		this.v = v;this.w = w;this.weight = weight;
	}
	public double weight(){return weight;}
	public int either(){return v;}
	public int another(int vertex){
		if(vertex == v)	return w;
		else if(vertex == w) return v;
		else throw new RuntimeException("wrong edge");
	}
	public int compareTo(Edge e){
		if(this.weight < e.weight()) return -1;
		else if(this.weight > e.weight()) return 1;
		else return 0;
	}
	public String toString(){
		return String.format("%d--%d %.2f", v, w, weight);
	}
}
