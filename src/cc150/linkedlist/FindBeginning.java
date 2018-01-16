package cc150.linkedlist;

class LinkedListNode {
	int value;
	LinkedListNode next;
}

public class FindBeginning {
	LinkedListNode findBegin(LinkedListNode head) {
		LinkedListNode slow = head, fast = head;
		while(fast != null && fast.next != null){
			slow = slow.next;
			fast = fast.next.next;
			if(slow == fast)	break;
		}
		if(fast == null || fast.next == null)
			return null;
		slow = head;
		while(slow != fast){
			slow = slow.next;
			fast = fast.next;
		}
		return fast;
	}
	public static void main(String[] args) {
		LinkedListNode n0 = new LinkedListNode();
		n0.value = 0;
		LinkedListNode n1 = new LinkedListNode();
		n1.value = 1;
		LinkedListNode n2 = new LinkedListNode();
		n2.value = 2; 
		LinkedListNode n3 = new LinkedListNode();
		n3.value = 3; 
		LinkedListNode n4 = new LinkedListNode();
		n4.value = 4; 
		n0.next = n1;
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n1;
		FindBeginning f = new FindBeginning();
		System.out.println(f.findBegin(n0));
	}
}
