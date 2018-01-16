package me.auburn.edu.alg4.ch4_2;

import java.util.Stack;

import me.auburn.edu.alg4.ch4_1.Config;

/**
 * 强连通分量的实现
 */
public class KosarajuSCC {
	private boolean[] marked;	// 已访问过的顶点
	private int[] id;			// 强连通分量标识符
	private int count;			// 强连通分量的数量
	private KosarajuSCC(Digraph g){
		marked = new boolean[g.getVertexCount()];
		id = new int[g.getVertexCount()];
		DepthFirstOrder dfo = new DepthFirstOrder(g.reverse());
		Stack<Integer> stack = dfo.reversePost();
		while(!stack.isEmpty()){
			int s = stack.pop();
			if(!marked[s]){
				dfs(g,s);
				count++;
			}
		}
	}
	private void dfs(Digraph g, int v) {
		marked[v] = true;
		id[v] = count;
		for(int w: g.adj(v))
			if(!marked[w]) dfs(g,w);
	}
	/** 判断v和w是否强连通*/
	public boolean isStrongConnected(int v, int w){return id[v] == id[w];}
	/** v所在强连通分量的标识符*/
	public int id(int v){return id[v];}
	/** 强连通分量的总数*/
	public int count(){return count;}
	public static void main(String[] args) {
		Digraph d = new Digraph(13, Config.directEdges);
		KosarajuSCC cc = new KosarajuSCC(d);
		int strongConnectCount = cc.count();
		System.out.println("强连通分量个数："+strongConnectCount);
		for(int i = 0; i<strongConnectCount; i++){
			System.out.print("第"+i+"个强连通分量：");
			for(int v = 0; v < d.getVertexCount(); v++)
				if(cc.id(v) == i) System.out.print(v + " ");
			System.out.println();
		}
	}
}
