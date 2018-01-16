package me.auburn.edu.alg4.ch4_1;

import java.io.File;
import java.util.LinkedList;

import edu.princeton.cs.algs4.In;

/**
 * 无向图
 */
public class Graph {
	private int vertexCount;			// 顶点数
	private int edgeCount;				// 边数
	private LinkedList<Integer>[] adj;	// 邻接表数组
	public Graph(int v){
		this.adj = new LinkedList[v];
		for(int i = 0; i<v; i++) adj[i] = new LinkedList<>();// 初始化邻接表数组
		this.vertexCount = v;
	}
	public Graph(In in) {
		this(in.readInt());
		int e = in.readInt();//得到边数
		// 读取每条边，进行图的初始化操作
		for(int i = 0; i<e;i++){
			int v = in.readInt(); 	// 起点
			int w = in.readInt(); 	// 终点
			addEdge(v, w);
		}
	}
	/*** 增加一条边*/
	public void addEdge(int start, int end) {
		adj[start].add(end);
		adj[end].add(start);
		this.edgeCount++;
	}
	public int getEdgeCount() { return edgeCount; }
	public int getVertexCount() { return vertexCount; }
	/** 返回顶点v的邻接表*/
	public LinkedList<Integer> adj(int v){return adj[v];}
	/** 把图转化成标准字符串形式*/
	public String toString(){
		String NEWLINE = System.getProperty("line.separator");
		StringBuilder sb = new StringBuilder();
        sb.append("vertex count: ").append(getVertexCount())
                .append(" edge count: ").append(getEdgeCount())
                .append(Config.NEWLINE);
        for (int v = 0; v < getVertexCount();v++){
            LinkedList<Integer> list = adj(v);
            sb.append(v).append(":\t").append("[");
            for (int i=0; i < list.size();i++){
                sb.append(list.get(i)).append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append("]").append(NEWLINE);
        }
        return sb.toString();
	}
	public static void main(String[] args) {
		String dir = Graph.class.getPackage().getName().replace(".", "/");
		String path = Graph.class.getClassLoader().getResource(dir+"/tinyG.txt").getPath();
		In in = new In(new File(path));
		Graph g = new Graph(in);
		System.out.println(g.toString());
	}
}
