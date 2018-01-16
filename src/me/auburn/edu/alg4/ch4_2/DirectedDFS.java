package me.auburn.edu.alg4.ch4_2;

import java.util.Arrays;
import java.util.LinkedList;

import me.auburn.edu.alg4.ch4_1.Config;

/**
 * 有向图的可达性:
 * 		该DFS的实现使得用例能够判断从给定的一个或者一组顶点能够到达哪些其他顶点。
 */
public class DirectedDFS {
	private boolean[] marked;
	/** 在有向图digraph中找出从start可达的所有顶点*/
	public DirectedDFS(Digraph digraph, int start){
		marked = new boolean[digraph.getVertexCount()];
		dfs(digraph, start);
	}
	/** 在有向图digraph中找出从source集合中的所有顶点 可达的所有顶点*/
	public DirectedDFS(Digraph digraph, Iterable<Integer> sources){
		marked = new boolean[digraph.getVertexCount()];
		for(int s: sources){
			if(!marked[s])
				dfs(digraph, s);
		}
	}
	private void dfs(Digraph digraph, int v) {
		marked[v] = true;
		for(int w: digraph.adj(v))
			if(!marked[w]) 	dfs(digraph, w);
	}
	/** 顶点v是可达的吗*/
	public boolean marked(int v){return marked[v];}
	public static void main(String[] args) {
		Digraph d = new Digraph(13, Config.directEdges);
		int start = 0;
		DirectedDFS dfs = new DirectedDFS(d, start);
		System.out.print("从"+start+"可达的顶点:");
		for(int i = 0; i<d.getVertexCount();i++){
			if(dfs.marked(i)) System.out.print(i+" ");
		}
		System.out.println();
		System.out.println("--------------");
		LinkedList<Integer> list = new LinkedList<>();
		list.add(1);list.add(2);list.add(6);
		System.out.print("从集合"+Arrays.toString(list.toArray())+"可达的顶点:");
		DirectedDFS dfs2 = new DirectedDFS(d, list);
		for(int i = 0; i<d.getVertexCount();i++){
			if(dfs2.marked(i))	System.out.print(i+" ");
		}
	}
}
