package me.auburn.edu.alg4.ch4_4;
/**
 * 加权有向边
 */
public class DirectedEdge {
	private int start; 		
	private int end;		
	private double weight;
	public DirectedEdge(int start, int end, double weight){
		this.start = start; this.end = end; this.weight = weight;
	}
	public double weight(){
		return this.weight;
	}
	public int from(){
		return this.start;
	}
	public int to(){
		return this.end;
	}
	public String toString(){
		return String.format("%d->%d: %.2f", start, end, weight);
	}
}
