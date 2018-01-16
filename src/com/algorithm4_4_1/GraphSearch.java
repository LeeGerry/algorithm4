package com.algorithm4_4_1;
/**
 * 图搜索类，参数：对于图g，进行顶点s的搜索
 */
public class GraphSearch {
	private Graph g;
	private int s;
	public GraphSearch(Graph g, int s){
		this.g = g;
		this.s = s;
	}
	/**
	 * v是否和s连通
	 * @param v
	 * @return
	 */
	public boolean marked(int v){
		return false;
	}
	/**
	 * 计算和s相连通的顶点的个数
	 * @return
	 */
	public int count(){
		return -1;
	}
}
