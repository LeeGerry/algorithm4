package me.auburn.edu.alg4.ch4_3;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;

import me.auburn.edu.alg4.ch4_1.Config;

/**
 * Prim算法的即时实现
 */
public class PrimMST {
	private Edge[] edgeTo;	// 距离树最近的边
	private double[] distTo;// distTo[w] = edgeTo[w].weight();
	private boolean[] marked;
	private HashMap<Integer,Double> pq;
	public PrimMST(EdgeWeightedGraph g){
		edgeTo = new Edge[g.getVertexCount()];
		distTo = new double[g.getVertexCount()];
		marked = new boolean[g.getVertexCount()];
		for(int v=0; v<g.getVertexCount(); v++)
			distTo[v] = Double.POSITIVE_INFINITY;
		pq = new HashMap<>();
		distTo[0] = 0.0;	
		pq.put(0,0.0);		// 把顶点0和到自身的weight（为0）放入map
		while(pq.size()!=0){	// 这里用map来模仿队列，存入顶点和权值
			int v = findIndexOfMinValue(pq);// 找到队列中的最小权值对应的那个顶点
			pq.remove(v);	// 移除（出队）
			visit(g, v);	// 对v进行扫描
		}
	}
	private void visit(EdgeWeightedGraph g, int v) {
		marked[v] = true;
		for(Edge e: g.adj(v)){
			int w = e.another(v);
			if(marked[w])	continue;// v-w失效，跳过
			if(e.weight() < distTo[w]){
				edgeTo[w] = e;	//连接w和树的最佳边变为e
				distTo[w] = e.weight();	//更新权重
				pq.put(w, e.weight());	//更新pq中的权值（若存在则覆盖）
			}
		}
	}
	public Iterable<Edge> getMst(){
		ArrayList<Edge> edges = new ArrayList<>();
		for(Edge e: edgeTo){
			if(e!=null)	edges.add(e);
		}
		return edges;
	}
	
	public double getMstWeight(){
		double weight = 0d;
		for(double d: distTo){
			if(d < Double.MAX_VALUE)
				weight += d;
		}
		NumberFormat format = DecimalFormat.getInstance();
		return Double.parseDouble(format.format(weight));
	}
	private int findIndexOfMinValue(HashMap<Integer, Double> map){
		if(map == null || map.size() == 0)	throw new RuntimeException("map error");
		int minIndex = -1;
		double min = Double.MAX_VALUE;
		for(Entry<Integer, Double> e:map.entrySet()){
			if(e.getValue() < min){
				min = e.getValue();
				minIndex = e.getKey();
			}
		}
		return minIndex;
	}
	public static void main(String[] args) {
		EdgeWeightedGraph g = new EdgeWeightedGraph(8, Config.directedWeightEdges);
		PrimMST p = new PrimMST(g);
		for(Edge e: p.getMst())
			System.out.println(e);
		System.out.println("total weight: "+p.getMstWeight());
	}
}
