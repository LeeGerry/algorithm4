package me.auburn.edu.alg4.ch4_1;

import java.io.File;
import java.util.LinkedList;

import edu.princeton.cs.algs4.In;
/**
 * 强连通分量
 */
public class CC {
	private boolean[] marked;
	private int[] id;
	private int count;
	public CC(Graph g){
		marked = new boolean[g.getVertexCount()];
		id = new int[g.getVertexCount()];
		for(int s = 0; s < g.getVertexCount(); s++){
			if(!marked[s]){
				dfs(g,s);
				count++;
			}
		}
	}
	private void dfs(Graph g, int v) {
		marked[v] = true;
		id[v] = count;
		for(int w: g.adj(v))
			if(!marked[w])
				dfs(g,w);
	}
	/** v和w连通吗*/
	public boolean connected(int v, int w)	{ return id[v] == id[w]; }
	/** v所在的连通分量的标识符*/
	public int id(int v)	{ return id[v]; }
	/** 连通分量数*/
	public int count()		{return count;}
	public static void main(String[] args) {
		String dir = CC.class.getPackage().getName().replace(".", "/");
		String path = CC.class.getClassLoader().getResource(dir+"/tinyG.txt").getPath();
		In in = new In(new File(path));
		Graph g = new Graph(in);
		CC cc = new CC(g);
		int m = cc.count();
		System.out.println("number of components: "+ m);
		LinkedList<Integer>[] components = new LinkedList[m];
		for(int i =0;i<m;i++)
			components[i] = new LinkedList<>();
		for(int v = 0; v< g.getVertexCount(); v++)
			components[cc.id(v)].add(v);
		for(int i=0;i<m;i++){
			for(int v: components[i])
				System.out.print(v + " ");
			System.out.println();
		}
	}
}
