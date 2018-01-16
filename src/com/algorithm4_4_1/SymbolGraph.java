package com.algorithm4_4_1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class SymbolGraph {
	private ST<String, Integer> st;	// 符号名->索引
	private String[] keys;			// 索引->符号名
	private Graph g;				// 图
	
	public SymbolGraph(String stream, String sp){
		st = new ST<>();
		In in = new In(stream);		//第一遍
		while(in.hasNextChar()){	//构造索引
			String[] a = in.readLine().split(sp);//读取字符串
			for(String str:a)		//为每个不同的字符串关联一个索引
				if(!st.contains(str))	st.put(str, st.size());
		}
		keys = new String[st.size()];//用来获得顶点名的反向索引，是一个数组
		for(String name: st.keys())
			keys[st.get(name)] = name;
		g = new Graph(st.size());
		in = new In(stream);		//第二遍
		while(in.hasNextLine()){	//构造图
			String[] a = in.readLine().split(sp);//将每一行的顶点和该行其他点相连
			int v = st.get(a[0]);
			for(int i=1; i<a.length; i++)
				g.addEdge(v, st.get(a[i]));
		}
	}
	public boolean contains(String s){return st.contains(s);}
	public int index(String s){return st.get(s);}
	public String name(int v){return keys[v];}
	public Graph getG(){return g;}
	public static void main(String[] args) {
		String filePath = "/Users/liguorui/Documents/javaee/algorith4/src/com/algorithm4/routes.txt";
//		String filePath = "/Users/liguorui/Documents/javaee/algorith4/src/com/algorithm4/movies.txt";
//		String delim = "/";
		String delim = " ";
		SymbolGraph sg = new SymbolGraph(filePath, delim);
		Graph g = sg.getG();
		while(StdIn.hasNextChar()){
			String source = StdIn.readLine();
			for(int w: g.adj(sg.index(source)))
				StdOut.println(" "+sg.name(w));
		}
	}
}
