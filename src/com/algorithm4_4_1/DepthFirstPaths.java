package com.algorithm4_4_1;

import java.io.File;
import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class DepthFirstPaths {
	private boolean[] marked;//从这个顶点上调用过dfs()了吗？
	private int[] edgeTo;	 //从起点到一个顶点的已知路径上的最后一个顶点
	private int s;			 //起点
	public DepthFirstPaths(Graph g, int s){
		marked = new boolean[g.getVCount()];
		edgeTo = new int[g.getVCount()];
		this.s = s;
		dfs(g, s);
		System.out.println(Arrays.toString(edgeTo));
	}
	private void dfs(Graph g, int v){
		marked[v] = true;
		for(int w: g.adj(v)){//遍历v的邻接表
			if(!marked[w]){  //遇到没有被标记过的，进行操作，被标记过的不用管
				edgeTo[w] = v;//把V作为值放到当前遍历的顶点W作为索引的路径数组中，其实就是保存了到达当前W节点前一个节点
				dfs(g, w);//递归深搜当前顶点W
			}
		}
	}
	public boolean hasPathTo(int v){ return marked[v]; }
	public Iterable<Integer> pathTo(int v){
		if(!hasPathTo(v)) return null;
		Stack<Integer> path = new Stack<>();
		for(int x = v; x != s; x=edgeTo[x])//从终点开始，倒着一直找到起点，依次push入栈
			path.push(x);
		path.push(s);//最后循环完毕以后把起点入栈，至此一条完整的路径放入到了栈中
		return path;//把该栈返回
	}
	public static void main(String[] args) {
		String path = "/Users/liguorui/Documents/javaee/algorith4/src/com/algorithm4/test_depth_path.txt";
//		String path = "/Users/liguorui/Documents/javaee/algorith4/src/com/algorithm4/bfs.txt";
		int start = 0;
		In in = new In(new File(path));
		Graph g = new Graph(in);
		DepthFirstPaths search = new DepthFirstPaths(g, start);
		for (int v = 0; v < g.getVCount(); v++) {
			StdOut.print(start+" to"+v+": ");
			if(search.hasPathTo(v)){			// 如果存在一条到V得路径
				for (int x: search.pathTo(v)) { //遍历该路径
					if(x == start)  StdOut.print(x);
					else 		StdOut.print("-"+x);
				}
			}	
			StdOut.println();
		}
	}
}
