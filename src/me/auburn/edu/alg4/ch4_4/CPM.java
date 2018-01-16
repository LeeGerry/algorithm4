package me.auburn.edu.alg4.ch4_4;

import java.io.File;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
/**
 * 优先级限制下的并行任务调度问题的关键路径方法
 */
public class CPM {
	public static void main(String[] args) {
		String dir = CPM.class.getPackage().getName().replace(".", "/");
		String path = CPM.class.getClassLoader().getResource(dir + "/jobsPC.txt").getPath();
		
		In in = new In(new File(path));
		int n = in.readInt();
		in.readLine();
		// source and sink
		int source = 2 * n;
		int sink = 2 * n + 1;

		// build network
		EdgeWeightedDigraph G = new EdgeWeightedDigraph(2 * n + 2);
		for (int i = 0; i < n; i++) {
			String[] strs = in.readLine().split("\\s+");
			double duration = Double.parseDouble(strs[0]);
			G.addEdge(new DirectedEdge(source, i, 0.0));
			G.addEdge(new DirectedEdge(i + n, sink, 0.0));
			G.addEdge(new DirectedEdge(i, i + n, duration));

			// precedence constraints
			for (int j = 1; j < strs.length; j++) {
				int precedent = Integer.parseInt(strs[j]);
				G.addEdge(new DirectedEdge(n + i, precedent, 0.0));
			}
		}

		// compute longest path
		AcyclicLP lp = new AcyclicLP(G, source);

		// print results
		StdOut.println(" job   start  time:");
		for (int i = 0; i < n; i++) {
			StdOut.printf("%4d %5.1f\n", i, lp.distTo(i));
		}
		StdOut.printf("Finish time: %7.1f\n", lp.distTo(sink));
	}
}
