package me.auburn.edu.alg4.ch4_1;

import java.io.File;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;

/**
 * 深搜寻找路径问题
 */
public class DepthFirstPaths {
	private boolean[] marked;		
	private int[] edgeTo;		// 路径
	private int start;			// 起点
	public DepthFirstPaths(Graph g, int s){
		marked = new boolean[g.getVertexCount()];
		edgeTo = new int[g.getVertexCount()];
		this.start = s;
		dfs(g, s);
	}
	private void dfs(Graph g, int s) {
		marked[s] = true;
		for(int w: g.adj(s)){
			if(!marked[w]){
				// 如果w没有被标记过，把路径数组中的w处置为s，意思：从s到达了w。此处记录了每一次深搜的路径节点
				edgeTo[w] = s; 
				dfs(g, w);
			}
		}
	}
	/** 从起点s到顶点v是否存在通路*/
	public boolean hasPathTo(int v){return marked[v];}
	public Stack<Integer> pathTo(int v){
		if(!hasPathTo(v))	return null;
		Stack<Integer> stack = new Stack<>();
		for(int x = v; x!=start; x=edgeTo[x]) // 从终点开始，倒着找起点，依次push入栈
			stack.push(x);
		stack.push(start);// for循环到起点处终止，所以在循环结束后要把起点入栈，至此 一条完整的路径依次入栈
		return stack;
	}
	public static void main(String[] args) {
		String dir = DepthFirstPaths.class.getPackage().getName().replace(".", "/");
		String path = DepthFirstPaths.class.getClassLoader().getResource(dir+"/tinyG.txt").getPath();
		In in = new In(new File(path));
		Graph g = new Graph(in);
		int start = 0;
		DepthFirstPaths pathSearch = new DepthFirstPaths(g, start);
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i<g.getVertexCount(); i++){
			if(i == start) continue;
			if(!pathSearch.hasPathTo(i)){
				System.out.println(start+" to "+ i +" : not connected.");
				continue;
			}
			sb.setLength(0);
			sb.append(start).append(" to ").append(i).append(": ");
			Stack<Integer> p = pathSearch.pathTo(i);
			while(!p.isEmpty()) sb.append(p.pop()).append("->");
			sb.deleteCharAt(sb.length()-1);
			sb.deleteCharAt(sb.length()-1);
			System.out.println(sb.toString());
		}
	}
}
