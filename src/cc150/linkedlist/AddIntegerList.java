package cc150.linkedlist;

import java.util.Random;

public class AddIntegerList {
	public static Node addIntList(Node n1, Node n2) {
		return help(n1, n2, 0);
	}

	public static Node help(Node n1, Node n2, int carry) {
		// 两个链表全部为空，并且进位为0
		if (n1 == null && n2 == null && carry == 0)
			return null;
		Node result = new Node(0);
		int value = carry;
		if (n1 != null)
			value += n1.data;
		if (n2 != null)
			value += n2.data;
		result.data = value % 10;
		Node more = help(n1 == null ? null : n1.next, n2 == null ? null : n2.next, value >= 10 ? 1 : 0);
		result.next = more;
		return result;
	}

	public static void main(String[] args) {
		Node n1 = new Node(3);
		Node n2 = new Node(2);
		Random r = new Random();

		for (int i = 0; i < 10; i++) {
			n1.appendToTail(r.nextInt(10));
			n2.appendToTail(r.nextInt(10));
		}
		NodeOperate.print(n1);
		NodeOperate.print(n2);
		Node result = addIntList(n1, n2);
		NodeOperate.print(result);
	}
}
