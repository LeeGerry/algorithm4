package com.algorithm4_4_4;

/**
 * 加权有向边的数据模型
 */
public class DirectedEdge {
	private int start; // 边的起点
	private int end; // 边的终点
	private double weight;// 边的权重

	public DirectedEdge(int start, int end, double weight) {
		this.start = start;
		this.end = end;
		this.weight = weight;
	}

	public double weight() {
		return this.weight;
	}

	public int from() {
		return this.start;
	}

	public int to() {
		return this.end;
	}

	public String toString() {
		return String.format("%d->%d: %.2f", start, end, weight);
	}
}
