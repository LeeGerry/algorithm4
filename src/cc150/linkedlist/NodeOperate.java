package cc150.linkedlist;

import java.util.Hashtable;

public class NodeOperate {
	/**
	 * delete a node, whose value is d
	 */
	public static Node deleteNode(Node head, int d) {
		Node n = head;
		if (n.data == d) {
			return head.next;
		}
		while (n.next != null) {
			if (n.next.data == d) {
				n.next = n.next.next;
				return head;
			}
			n = n.next;
		}
		return head;
	}

	/**
	 * delete duplicate node, time O(n), space O(n)
	 */
	public static Node deleteDups(Node head) {
		Node dump = head;
		Hashtable<Integer, Boolean> table = new Hashtable<>();
		Node prev = null;
		while (head != null) {
			if (table.containsKey(head.data)) {
				prev.next = head.next;
			} else {
				table.put(head.data, true);
				prev = head;
			}
			head = head.next;
		}
		return dump;
	}

	/**
	 * delete duplicate node, time O(n^2), space O(1)
	 */
	public static Node deleteDups2(Node head) {
		if (head == null)
			return head;
		Node dump = head;
		Node current = head;
		while (current != null) {
			Node runner = current;
			while (runner.next != null) {
				if (runner.next.data == current.data) {
					runner.next = runner.next.next;
				} else {
					runner = runner.next;
				}
			}
			current = current.next;
		}
		return dump;
	}

	/**
	 * get the kth node from the last
	 */
	public static Node nthToLast(Node head, int k) {
		if (k <= 0)
			return null;
		Node n1 = head, n2 = head;
		// n2 move forward k-1 times
		for (int i = 0; i < k - 1; i++) {
			if (n2 == null)
				return null;// check the error
			n2 = n2.next;
		}
		if (n2 == null)
			return null;
		// now, n1 and n2 move forward, when n2 arrives to the end, n1 is the
		// kth from the end
		while (n2.next != null) {
			n1 = n1.next;
			n2 = n2.next;
		}
		return n1;
	}

	public static void print(Node head) {
		StringBuilder sb = new StringBuilder();
		Node n = head;
		while (n != null) {
			sb.append(n.data).append(",");
			n = n.next;
		}
		sb.deleteCharAt(sb.length() - 1);
		System.out.println(sb.toString());
	}

	public static Node partition(Node n, int x){
		Node beforeStart=null, beforeEnd=null, afterStart=null, afterEnd=null;
		// 分割
		while(n != null){
			Node next = n.next;//需要断开当前节点进行操作，所以使用next来保存当前节点的下一个节点，当while中最后n有地方指
			n.next = null;// 当前节点的next指针置空，也就是断开
			if(n.data < x){// 如果当前节点小于x，则需要插入到前段
				if(beforeStart == null){
					beforeStart = n;
					beforeEnd = beforeStart;
				}else{
					beforeEnd.next = n;
					beforeEnd = n;
				}
			}else{//如果当前节点大于或等于x，则需要插入到后段
				if(afterStart == null){
					afterStart = n;
					afterEnd = afterStart;
				}else{
					afterEnd.next = n;
					afterEnd = n;
				}
			}
			n = next;
		}
		if(beforeStart == null) 	return afterStart;
		//合并前后段
		beforeEnd.next = afterStart;
		return beforeStart;
	}
	
	public static Node partition2(Node head, int x){
		Node before = null, after = null;
		while(head != null){
			Node next = head.next;
			if(head.data < x){
				// 将节点插入before
				head.next = before;
				before = head;
			}else{
				head.next = after;
				after = head;
			}
			head = next;
		}
		if(before == null)	return after;
		Node h = before;
		while(before.next != null){
			before = before.next;
		}
		before.next = after;
		return h;
	}
	
	public static void main(String[] args) {
		int[] array = { 0, 1, 2, 1, 0, 3, 4, 0, 5 };
		Node head = new Node(array[0]);
		for (int i = 1; i < array.length; i++) {
			head.appendToTail(array[i]);
		}
		print(head);
//		Node n = deleteDups(head);
//		print(n);
//		Node n = nthToLast(head, 10);
		Node n = partition2(head, 3);
//		System.out.println(null == n ? "error":n.data);
		print(n);
	}
}
