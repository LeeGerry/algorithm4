package com.algorithm4_4_2;


public class Topological {
	private Iterable<Integer> order; //顶点的拓扑顺序
	public Topological(Digraph g){
		DirectedCycle cycleFinder = new DirectedCycle(g);
		if(!cycleFinder.hasCycle()){
			DepthFirstOrder dfs = new DepthFirstOrder(g);
			order = dfs.reversePost();
		}
	}
	public Iterable<Integer> order(){ return order; }
	public boolean isDAG(){return order != null; }
	public static void main(String[] args) {
		String path = "/Users/liguorui/Documents/javaee/algorith4/src/com/algorithm4/digraph/jobs.txt";
		String separator = "/";
		SymbolGraph sg = new SymbolGraph(path, separator);
		Topological top = new Topological(sg.getG());
		if(!top.isDAG()){
			System.out.println("是有环图");
			return;
		}
		for(int v: top.order())
			System.out.println(v + "---"+ sg.name(v));
	}
}
