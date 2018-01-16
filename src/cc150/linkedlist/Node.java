package cc150.linkedlist;

public class Node {
	Node next = null;
	int data;
	public Node(int data){
		this.data = data;
	}
	/**
	 * add a node to the end
	 * @param d
	 */
	void appendToTail(int d){
		Node end = new Node(d);
		Node n = this;
		while(n.next != null){
			n = n.next;
		}
		n.next = end;
	}
}
