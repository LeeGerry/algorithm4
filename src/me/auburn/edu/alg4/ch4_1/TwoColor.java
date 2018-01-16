package me.auburn.edu.alg4.ch4_1;

import java.io.File;

import edu.princeton.cs.algs4.In;

/**
 * 双色问题：能够用两种颜色将图的所有顶点着色，使得任意一条边上的两个端点的颜色都不同吗？
 * 等价于：判断是否是二分图的问题
 */
public class TwoColor {
	private boolean[] marked;
	private boolean[] color;
	private boolean isColorable;
	public TwoColor(Graph g){
		isColorable = true;
		marked = new boolean[g.getVertexCount()];
		color = new boolean[g.getVertexCount()];
		for(int i = 0; i<g.getVertexCount(); i++)//遍历所有顶点
			if(!marked[i])	dfs(g, i);//没有mark就进行深搜
	}
	private void dfs(Graph g, int v) {
		marked[v] = true;		// 标记
		for(int w: g.adj(v))	// 对邻接表进行遍历
			if(!marked[w]){		// 如果没有被标记
				color[w] = !color[v];	// 当前w节点颜色置为和父节点不同的颜色
				dfs(g, w);				// 对当前节点继续深搜
			}else if(color[w] == color[v]){	// 如果已经被标记，看是否颜色和父节点相同
				isColorable = false; 		// 若相同则不是二分图
			}
	}
	/** 是否是二分图*/
	public boolean isBipartite(){return isColorable;}
	public static void main(String[] args) {
		String dir = TwoColor.class.getPackage().getName().replace(".", "/");
		String path = TwoColor.class.getClassLoader().getResource(dir+"/color_test.txt").getPath();
		String path2 = TwoColor.class.getClassLoader().getResource(dir+"/color_test2.txt").getPath();
		In in = new In(new File(path));
		Graph g = new Graph(in);
		TwoColor t = new TwoColor(g);
		System.out.println(t.isBipartite());
		
		In in2 = new In(new File(path2));
		Graph g2 = new Graph(in2);
		TwoColor t2 = new TwoColor(g2);
		System.out.println(t2.isBipartite());
	}
}
