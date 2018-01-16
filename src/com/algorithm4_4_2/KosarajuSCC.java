package com.algorithm4_4_2;

import java.io.File;

import edu.princeton.cs.algs4.In;
/**
 * 强连通分量的实现
 */
public class KosarajuSCC {
	private boolean[] marked; 	//已访问过的顶点
	private int[] id;			//强连通分量的标识符
	private int count;			//强连通分量的数量
	public KosarajuSCC(Digraph g){
		marked = new boolean[g.getVCount()];
		id = new int[g.getVCount()];
		DepthFirstOrder order=  new DepthFirstOrder(g.reverse());
		for(int s: order.reversePost())
			if(!marked[s])
			{ dfs(g,s); count++; }
	}
	private void dfs(Digraph g, int v) {
		marked[v] =  true;
		id[v] = count;
		for(int w: g.adj(v))
			if(!marked[w])	dfs(g, w);
	}
	/**判断v和w是否强连通*/
	public boolean stronglnConnected(int v, int w){return id[v] == id[w];}
	/**v所在强连通分量的标识符*/
	public int id(int v){return id[v];}
	/**强连通分量的总数*/
	public int count(){return count;}
	public static void main(String[] args) {
		String filePath = "/Users/liguorui/Documents/javaee/algorith4/src/com/algorithm4_4_2/tinyDG.txt";
		Digraph d = new Digraph(new In(new File(filePath)));
		KosarajuSCC scc = new KosarajuSCC(d);
		int strongCount = scc.count();
		System.out.println("强连通分量的个数："+strongCount);
		for(int i=0;i<strongCount;i++){
			System.out.print("第"+i+"个强连通分量：");
			for(int v=0;v<d.getVCount();v++){
				if(scc.id(v) == i)
					System.out.print(v+" ");
			}
			System.out.println();
		}
	}
}
