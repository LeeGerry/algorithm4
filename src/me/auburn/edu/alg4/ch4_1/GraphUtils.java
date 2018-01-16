package me.auburn.edu.alg4.ch4_1;

import java.io.File;

import edu.princeton.cs.algs4.In;

/**
 * 图的基本常用操作工具类
 */
public class GraphUtils {
	/** 计算顶点v的度数*/
	public static int degree(Graph graph, int v){return graph.adj(v).size();}
	/** 计算图中最大的度*/
	public static int maxDegree(Graph graph){
		int max = 0;
		for(int i = 0;i<graph.getVertexCount();i++){
			int currentDegree = degree(graph, i);
			max = currentDegree > max ? currentDegree : max;
		}
		return max;
	}
	/** 计算图的平均度数*/
	public static int avgDegree(Graph g){ return 2 * g.getEdgeCount() / g.getVertexCount(); }
	/** 计算自环的个数*/
	public static int numberOfSelfLoops(Graph g){
		int count = 0;
		for(int v = 0; v < g.getVertexCount(); v++)
			for(int w: g.adj(v))
				if(v == w)	count++;
		return count / 2; // 每条边计算了两次
	}
	public static void main(String[] args) {
		String dir = GraphUtils.class.getPackage().getName().replace(".", "/");
		String path = GraphUtils.class.getClassLoader().getResource(dir+"/tinyG.txt").getPath();
		In in = new In(new File(path));
		Graph g = new Graph(in);
		for (int i = 0; i < g.getVertexCount(); i++) {
			System.out.println(i+" degree : "+GraphUtils.degree(g, i));	
		}
		System.out.println("the max degree is : " + GraphUtils.maxDegree(g));
		System.out.println(g.toString());
		System.out.println("avg degree: "+GraphUtils.avgDegree(g));
		System.out.println("count of self loop: "+GraphUtils.numberOfSelfLoops(g));
	}
}
