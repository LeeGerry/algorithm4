package me.auburn.edu.alg4.ch5_2;

public class TrieST<Value> {
	private static int R = 256;// 基数
	private Node root;	// 单词查找树的根节点
	private static class Node{
		private Object val;
		private Node[] next = new Node[R];
	}
	
	public Value get(String key){
		Node x = get(root, key, 0);
		if(x == null) return null;
		return (Value)x.val;
	}
	
	private Node get(Node x, String key, int d){
		if(x == null) return null;
		if(d == key.length()) return x;
		char c = key.charAt(d);
		return get(x.next[c], key, d+1);
	}
	public void put(String key, Value val){
		root = put(root, key, val, 0);
	}
	private Node put(Node x, String key, Value val, int d){
		if(x == null) x = new Node();
		if(d == key.length()){
			x.val = val;
			return x;
		}
		char c = key.charAt(d);
		x.next[c] = put(x.next[c], key, val, d+1);
		return x;
	}
	
	public static void main(String[] args) {
		TrieST<Integer> tree = new TrieST<>();
		tree.put("she", 0);
		tree.put("sells", 1);
		tree.put("sea", 2);
		tree.put("shells", 3);
		tree.put("by", 4);
		tree.put("the", 5);
		tree.put("sea", 6);
		tree.put("shore", 7);
		System.out.println(tree.get("sea"));
	}
}
