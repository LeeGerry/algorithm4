package com.algorithm4_4_1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class TestSearch {
	public static void main(String[] args) {
		Graph g = new Graph(new In(args[0]));
		int s = Integer.parseInt(args[1]);//对顶点s进行搜索
		GraphSearch search = new GraphSearch(g, s);
		for(int i=0; i < g.getVCount(); i++){
			//遍历顶点，如果相连，则输出
			if(search.marked(i))	StdOut.print(i+" ");
		}
		StdOut.println();
		String result = "connected";
		//如果和s相连通的定点数等于图的顶点数，说明是连通图
		if(search.count() != g.getVCount()) 
			result = "not "+ result;
		StdOut.println(result);
	}
}
