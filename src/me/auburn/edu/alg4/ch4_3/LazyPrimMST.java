package me.auburn.edu.alg4.ch4_3;

import java.util.LinkedList;
import java.util.PriorityQueue;

import me.auburn.edu.alg4.ch4_1.Config;

/**
 * 最小生成树的Prim算法的延时实现
 */
public class LazyPrimMST {
	private boolean[] marked;	 	// 辅助数组：顶点标记
	private LinkedList<Edge> mst;	// 最小生成树的边
	private PriorityQueue<Edge> pq;	// 横切边队列
	public LazyPrimMST(EdgeWeightedGraph g){
		pq = new PriorityQueue<Edge>();
		marked = new boolean[g.getVertexCount()];
		mst = new LinkedList<>();
		visit(g,0);
		while(!pq.isEmpty()){
			Edge e = pq.remove();//取队头，得到最小边
			int v = e.either(), w = e.another(v);
			if(marked[v] && marked[w])	continue;//跳过失效边
			mst.offer(e);
			if(!marked[v])	visit(g, v);
			if(!marked[w])	visit(g, w);
		}
	}
	/** 标记顶点v并将所有连接v和未被标记顶点的边加入队列*/
	private void visit(EdgeWeightedGraph g, int v) {
		marked[v] = true;
		for(Edge e: g.adj(v))
			if(!marked[e.another(v)])	pq.add(e);
	}
	/** 返回最小生成树*/
	public Iterable<Edge> getMst(){return mst;}
	/** 最小生成树的权重*/
	public double getMstWeight(){
		double weight = 0;
		for(Edge e: mst)
			weight += e.weight();
		return weight;
	}
	public static void main(String[] args) {
		EdgeWeightedGraph ewg = new EdgeWeightedGraph(8, Config.directedWeightEdges);
		LazyPrimMST lmst = new LazyPrimMST(ewg);
		Iterable<Edge> mst = lmst.getMst();
		for(Edge e:mst)
			System.out.println(e);
		System.out.println("total weight of mst: "+ lmst.getMstWeight());
	}
}
