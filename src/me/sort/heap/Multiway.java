package me.sort.heap;

import java.io.File;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
/**
 * 使用优先队列的多项归并
 */
public class Multiway {
	public static void merge(In[] streams){
		int n = streams.length;
		IndexMinPQ<String> pq = new IndexMinPQ<String>(n);
		for(int i = 0;i<n;i++){
			if(!streams[i].isEmpty()){
				String s = streams[i].readString();
				pq.insert(i, s);
			}
		}
		while(!pq.isEmpty()){
			StdOut.print(pq.minKey()+" ");
			int i = pq.delMin();
			if(!streams[i].isEmpty()){
				String s = streams[i].readString();
				pq.insert(i, s);
			}
		}
	}
	
	public static void main(String[] args) {
		ClassLoader loader = Multiway.class.getClassLoader();
		String dir = Multiway.class.getPackage().getName().replace(".", "/");
		String path0 = loader.getResource(dir+"/m1.txt").getPath();
		String path1 = loader.getResource(dir+"/m2.txt").getPath();
		String path2 = loader.getResource(dir+"/m3.txt").getPath();

		String[] paths = {path0, path1, path2};
		int n = 3;
		In[] streams = new In[n];
		for(int i = 0;i<n;i++){
			streams[i] = new In(new File(paths[i]));
		}
		merge(streams);
	}
}
