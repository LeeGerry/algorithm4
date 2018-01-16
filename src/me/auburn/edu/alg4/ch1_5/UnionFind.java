package me.auburn.edu.alg4.ch1_5;

import java.io.File;

import edu.princeton.cs.algs4.In;

/**
 * union-find的实现
 */
public class UnionFind {
	private int[] id;	// 分量ID
	private int count; 	// 分量数量
	public UnionFind(int n){
		count = n;
		id = new int[n];
		for(int i=0; i<n; i++)
			id[i] = i;
	}
	/** 得到分量数量*/
	public int getConnectedCount(){return count;}
	/** 判断p和q是否在同一个分量中*/
	public boolean isConnected(int p, int q){return find(p) == find(q);}
	/** 查询p在哪个分量中*/
	public int find(int p){return id[p];}
	/** 连接p和q*/
	public void union(int p, int q){}
	public static void main(String[] args) {
		String dir = UnionFind.class.getPackage().getName().replace(".", "/");
		System.out.println(dir);
		String path = UnionFind.class.getClassLoader().getResource(dir+"/tinyUF.txt").getPath();
		In in = new In(new File(path));
		int n = in.readInt();
		UnionFind uf = new UnionFind(n);
		String line;
		while((line = in.readLine()) != null){
			if(line.isEmpty())	continue;
			String[] ints = line.split(" ");
			int p = Integer.parseInt(ints[0]);
			int q = Integer.parseInt(ints[1]);
			if(uf.isConnected(p, q))	continue;
			uf.union(p, q);
			System.out.println(p + " " + q);
		}
		System.out.println(uf.getConnectedCount() + " components.");
	}
}
