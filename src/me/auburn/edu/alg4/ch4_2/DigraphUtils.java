package me.auburn.edu.alg4.ch4_2;

import java.util.LinkedList;

import me.auburn.edu.alg4.ch4_1.Config;

/**
 * 有向图的基本常用操作工具类
 */
public class DigraphUtils {
	
	/** 把图转化成标准字符串形式*/
	public static String graphToString(Digraph graph){
		StringBuilder sb = new StringBuilder();
        sb.append("vertex count: ").append(graph.getVertexCount())
                .append(" edge count: ").append(graph.getEdgeCount())
                .append(Config.NEWLINE);
        for (int v = 0; v<graph.getVertexCount();v++){
            LinkedList<Integer> list = graph.adj(v);
            if(list.size() == 0) continue;
            sb.append(v).append(":\t").append("[");
            for (int i=0; i < list.size();i++){
                sb.append(list.get(i)).append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append("]").append(Config.NEWLINE);
        }
        return sb.toString();
	}
}
