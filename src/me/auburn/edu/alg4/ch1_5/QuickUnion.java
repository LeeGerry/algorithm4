package me.auburn.edu.alg4.ch1_5;

import java.io.File;

import edu.princeton.cs.algs4.In;

/**
 * quick-union
 */
public class QuickUnion {
	private int[] id;	// 分量ID
	private int count; 	// 分量数量
	public QuickUnion(int n){
		count = n;
		id = new int[n];
		for(int i=0; i<n; i++)
			id[i] = i;
	}
	/** 得到分量数量*/
	public int getConnectedCount(){return count;}
	/** 判断p和q是否在同一个分量中*/
	public boolean isConnected(int p, int q){
		return find(p) == find(q);
	}
	/** 根据触点连接找到根节点*/
	public int find(int p){
		while(p != id[p])
			p = id[p];
		return p;
	}
	/** 把p的根节点连接到q的根节点上*/
	public void union(int p, int q){
		int pRoot = find(p);
		int qRoot = find(q);
		//如果两个根相同，说明已经在一个连通分量中，直接返回，无需操作
		if(pRoot == qRoot)	return;	
		//否则，把p连到q上
		id[pRoot] = qRoot;
		count--;
	}
	public static void main(String[] args) {
		String dir = QuickUnion.class.getPackage().getName().replace(".", "/");
		String path = QuickUnion.class.getClassLoader().getResource(dir+"/tinyUF.txt").getPath();
		In in = new In(new File(path));
		int n = in.readInt();
		QuickUnion uf = new QuickUnion(n);
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
