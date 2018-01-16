package com.algorithm4_4_1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class DegreeOfSeparation {
	public static void main(String[] args) {
		String filePath = "/Users/liguorui/Documents/javaee/algorith4/src/com/algorithm4/routes.txt";
		String delim = " ";
		SymbolGraph sg = new SymbolGraph(filePath, delim);
		Graph g = sg.getG();
		String source = "JFK";// 起点
		if (!sg.contains(source)) //起点不在图中
			{ StdOut.println("not in db."); return; }
		int s = sg.index(source);
		BreadthFirstPaths bfs = new BreadthFirstPaths(g, s);
		String end = "LAS"; //终点
		if (!sg.contains(end)) StdOut.println("not in db");
		else {
			int t = sg.index(end);//找到终点的索引
			if (bfs.hasPathTo(t)) //如果存在起点到终点的通路，打印
				for (int v : bfs.pathTo(t))  StdOut.println(sg.name(v));
			else StdOut.println("not connected");
		}
	}
}
