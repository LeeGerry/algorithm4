package me.auburn.edu.alg4.ch1_5;

import java.io.File;

import edu.princeton.cs.algs4.In;

/**
 * quick - Union find
 */
public class QuickFind {
	private int[] id;
	private int count;
	public QuickFind(int n){
		id = new int[n];
		count = n;
		for(int i = 0; i<n; i++)
			id[i] = i;
	}
	/** 得到分量数量*/
	public int getConnectedCount(){return count;}
	/** 判断p和q是否在同一个分量中*/
	public boolean isConnected(int p, int q){return find(p) == find(q);}
	/** 查询p在哪个分量中*/
	public int find(int p){return id[p];}
	/** 连接p和q ,将p和q归并到相同的分量中*/
	public void union(int p, int q){
		int pid = find(p);
		int qid = find(q);
		//如果已经在同一个分量中，不需操作
		if(pid == qid)	return;
		//将p的分量重命名为q的名称
		for(int i = 0; i<id.length; i++)
			if(id[i] == pid)	id[i] = qid;
		count--;
	}
	public static void main(String[] args) {
		String dir = QuickFind.class.getPackage().getName().replace(".", "/");
		String path = QuickFind.class.getClassLoader().getResource(dir+"/tinyUF.txt").getPath();
		In in = new In(new File(path));
		int n = in.readInt();
		QuickFind uf = new QuickFind(n);
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
