package me.auburn.edu.alg4.ch4_2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import edu.princeton.cs.algs4.Stack;
import me.auburn.edu.alg4.ch4_1.BreadthFirstPaths;

/**
 * 符号图
 */
public class SymbolGraph {
	private HashMap<String, Integer> map; 	// key:顶点名  value:索引
	private String[] keys;					// 反向索引，保存每个顶点索引对应的顶点名
	private Digraph g;						// 使用索引来引用图中的顶点
	public SymbolGraph(String path, String sp){
		map = new HashMap<>();
		
		BufferedReader reader;
		String line;
		try {
			reader = new BufferedReader(new FileReader(new File(path)));
			while((line = reader.readLine()) != null){//第一遍，构造索引
				String [] vertexs = line.split(sp);
				for(String s : vertexs)
					if(!map.containsKey(s))	map.put(s, map.size());
			}
			reader.close();
			keys = new String[map.size()]; 
			for(String name: map.keySet()){	// 遍历map的key,构造顶点名的反向索引
				keys[map.get(name)] = name; 
			}
			g = new Digraph(map.size());
			line = "";
			reader = new BufferedReader(new FileReader(new File(path)));
			while((line = reader.readLine()) != null){ // 第二遍，构造图，将每一行的顶点和该行其他点相连
				String[] strs = line.split(sp);
				int start = map.get(strs[0]);//获取起点
				for(int i = 1; i< strs.length; i++)
					g.addEdge(start, map.get(strs[i]));
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/** key是一个顶点吗*/
	public boolean  contains(String key){return map.containsKey(key);}
	/** key的索引*/
	public int index(String key){return map.get(key);}
	/** 索引v的顶点名*/
	public String name(int v){return keys[v];}
	/** 隐藏的Graph对象*/
	public Digraph graph(){return g;}
}
