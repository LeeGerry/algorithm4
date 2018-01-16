package me.auburn.edu.alg4.ch4_2;

import java.util.Stack;

import me.auburn.edu.alg4.ch4_1.Config;

/**
 * 有向图中的环
 */
public class DirectedCycle {
	private boolean[] marked;
	private int[] edgeTo;
	private Stack<Integer> cycle;	// 有向环中的所有顶点
	private boolean[] onStack;		// 递归调用的栈上的所有顶点
	public DirectedCycle(Digraph digraph){
		onStack = new boolean[digraph.getVertexCount()];
		edgeTo = new int[digraph.getVertexCount()];
		marked = new boolean[digraph.getVertexCount()];
		for(int i = 0; i< digraph.getVertexCount(); i++){
			if(!marked[i]) dfs(digraph, i);
		}
	}
	private void dfs(Digraph digraph, int v) {
		onStack[v] = true;				// 在当前栈标记v
		marked[v] = true;				// mark v
		for(int w:digraph.adj(v)){
			if(this.hasCycle())	return;	// 若有环，直接返回
			else if(!marked[w]){			// 无环，且当前节点没有被mark
				edgeTo[w] = v;			// 设置进当前路径
				dfs(digraph, w);			// 继续深搜
			}else if(onStack[w]){		// 无环，且当前节点被mark了，然后当前节点又出现在当前栈，说明产生了环
				cycle = new Stack<>();	
				for(int x = v; x!=w; x=edgeTo[x])	// 把当前产生的环的所有顶点入栈
					cycle.push(x);
				cycle.push(w);						// 终点入栈
				cycle.push(v);						// 起点入栈，起点和终点相同
			}
		}
		onStack[v] = false;							// 恢复原状
	}
	public boolean hasCycle() {return cycle != null;}
	public Stack<Integer> getCycle(){return cycle;}
	public static void main(String[] args) {
		Digraph d = new Digraph(13, Config.directEdges);
		DirectedCycle dc = new DirectedCycle(d);
		System.out.println(dc.cycle.toString());
	}
}
