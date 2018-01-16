package me.gerry.ch2;

public class StackOfStrings {
	private Node first = null;
	private class Node{
		String item;
		Node next;
	}
	public void push(String item){
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
	}
	public String pop(){
		String item = first.item;
		first = first.next;
		return item;
	}
	public boolean isEmpty(){
		return first == null;
	}
	public int size(){
		return -1;
	}
	public static void main(String[] args) {
		StackOfStrings s = new StackOfStrings();
		s.push("abc");
		s.push("bcd");
		s.push("cde");
		s.push("def");
		s.push("efg");
	}
}
