package me.auburn.edu.alg4.ch4_2;

import me.auburn.edu.alg4.ch4_1.Config;

/**
 * 顶点对可达性
 */
public class TransitiveClosure {
	private DirectedDFS[] all;
	public TransitiveClosure(Digraph g){
		all = new DirectedDFS[g.getVertexCount()];
		for(int v = 0; v < g.getVertexCount(); v++)
			all[v] = new DirectedDFS(g, v);
	}
	public boolean reachable(int v, int w){
		return all[v].marked(w);
	}
	public static void main(String[] args) {
		Digraph g = new Digraph(13, Config.directEdges);
		TransitiveClosure tc = new TransitiveClosure(g);
		for(int i=0; i< g.getVertexCount();i++){
			for(int j=0; j< g.getVertexCount();j++)
				System.out.println(i+"到"+j+tc.reachable(i, j)+"\t");
			System.out.println();
		}
	}
}
