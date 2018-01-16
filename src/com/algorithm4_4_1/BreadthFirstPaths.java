package com.algorithm4_4_1;

import java.io.File;
import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class BreadthFirstPaths {
	private boolean[] marked;//到达该顶点的最短路径已知吗？
	private int[] edgeTo;	//到达该顶点的一直路径上的最后一个顶点
	private int start;		//起点
	
	public BreadthFirstPaths(Graph g, int s){
		marked = new boolean[g.getVCount()];
		edgeTo = new int[g.getVCount()];
		this.start = s;
		bfs(g, s);
	}

	private void bfs(Graph g, int s) {
		Queue<Integer> queue = new Queue<>();
		marked[s] = true;//标记起点
		queue.enqueue(s);//起点入队
		while(!queue.isEmpty()){//遍历整个队列
			int v = queue.dequeue();//从队列中删除队首V
			for(int w:g.adj(v)){//遍历V的邻接点
				if(!marked[w]){//标记过的就不用管了，对未标记的进行处理
					edgeTo[w] = v;//保存最短路径的最后一条边
					marked[w] = true;// 标记它
					queue.enqueue(w);//并将它入队
				}
			}
		}
	}
	public boolean hasPathTo(int v){
		return marked[v];
	}
	public Iterable<Integer> pathTo(int v){
		if(!hasPathTo(v)) return null;	//如果不存在到V的路径，返回null
		Stack<Integer> path = new Stack<>();//申请一个栈来保存结果
		for(int x = v; x != start; x = edgeTo[x])//从终点开始，知道找到起点终止，依次push入栈
			path.push(x);
		path.push(start);//循环完毕结束后把起点入栈，构造一条完整的路径
		return path;//返回结果
	}
	public static void main(String[] args) {
		String path = "/Users/liguorui/Documents/javaee/algorith4/src/com/algorithm4/bfs.txt";
		int start = 0;
//		In in = ;
		Graph g = new Graph(new In(new File(path)));
		BreadthFirstPaths search = new BreadthFirstPaths(g, start);
		for(int v = 0; v < g.getVCount(); v++){
			StdOut.print(start + " to " + v+ " : ");
			if(search.hasPathTo(v)){
				for (int x: search.pathTo(v)) {
					if(x == start) StdOut.print(x);
					else		   StdOut.print("-" + x);
				}
			}
			StdOut.println();
		}
	}
}
