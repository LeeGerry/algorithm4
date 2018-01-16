package me.auburn.edu.alg4.ch4_1;

import java.io.File;
import java.util.LinkedList;

import edu.princeton.cs.algs4.In;

/**
 * 连通分量
 * 	marked数组来寻找一个顶点作为每个连通分量中深搜的起点。
 * 	
 *  递归的深搜第一次调用的参数是顶点0，会标记所有与0连通的顶点。
 *  然后构造函数中的for循环会查找每个没有被标记的顶点并递归调用dfs来标记和它相邻的所有顶点。
 *  另外，还使用了一个以顶点作为索引的数组id[]，将同一个连通分量重的顶点和连通分量的标识符关联起来。该数组使得connected()方法变得简单。
 */
public class ConnectedComponent {
	private boolean[] marked;
	private int[] id;
	private int count;
	public ConnectedComponent(Graph g){
		marked = new boolean[g.getVertexCount()];
		id = new int[g.getVertexCount()];
		for(int s = 0; s < g.getVertexCount(); s++)
			if(!marked[s]){	// 找出一个未被标记的顶点，并递归调用dfs来标记并区分出所有和它连通的顶点。如此重复直到所有点被标记
				dfs(g, s); 
				count++;	// 每次递归结束返回，连通分量数量加一
			}
	}
	private void dfs(Graph g, int s) {
		marked[s] = true;
		id[s] = count;		// 若s属于第count个连通分量，则id[s]置为count
		for(int w: g.adj(s))
			if(!marked[w])	dfs(g, w);
	}
	/** v和w连通吗*/
	public boolean connected(int v, int w){ return id[v] == id[w]; }
	/** v所在的连通分量标识符（0 ~ count-1）*/
	public int id(int v){return id[v];}
	/** 连通分量数*/
	public int count(){return count;}
	public static void main(String[] args) {
		String dir = ConnectedComponent.class.getPackage().getName().replace(".", "/");
		String path = ConnectedComponent.class.getClassLoader().getResource(dir+"/tinyG.txt").getPath();
		In in = new In(new File(path));
		Graph g = new Graph(in);
		ConnectedComponent cc = new ConnectedComponent(g);
		int count = cc.count();
		System.out.println("g's total number of connected component : "+ count);
		LinkedList<Integer>[] components = new LinkedList[count];
		for(int i = 0; i<count; i++)	
			components[i] = new LinkedList<>();
		for(int v = 0; v<g.getVertexCount(); v++)
			components[cc.id(v)].add(v);
		for(int i = 0; i<count;i++){
			System.out.print("the index of component " + i + ": ");
			for(int v: components[i])
				System.out.print(v+" ");
			System.out.println();
		}
	}
}
