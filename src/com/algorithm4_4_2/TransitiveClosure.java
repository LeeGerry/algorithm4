package com.algorithm4_4_2;

public class TransitiveClosure {
	private DirectedDFS[] all;
	public TransitiveClosure(Digraph g) {
		all = new DirectedDFS[g.getVCount()];
		for(int v=0; v<g.getVCount(); v++)
			all[v] = new DirectedDFS(g, v);
	}
	boolean reachable(int v, int w){
		return all[v].markde(w);
	}
}
