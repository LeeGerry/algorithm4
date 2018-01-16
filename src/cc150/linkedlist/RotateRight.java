package cc150.linkedlist;

public class RotateRight {
	public ListNode rotateRight(ListNode head, int k) {
		if(head == null || head.next == null)	return head;
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode fast = dummy, slow = dummy;
		int len = 0;
		while(fast.next != null){
			fast = fast.next;
			len++;
		}
		for(int i=len-k%len; i>0; i--){
			slow = slow.next;
		}
		fast.next = dummy.next;
		dummy.next = slow.next;
		slow.next = null;
		return dummy.next;
	}
	public static void main(String[] args) {
		ListNode l1 = new ListNode(1);
		ListNode l2 = new ListNode(2);
		ListNode l3 = new ListNode(3);
		ListNode l4 = new ListNode(4);
		ListNode l5 = new ListNode(5);
		ListNode l6 = new ListNode(6);
		ListNode l7 = new ListNode(7);
		ListNode l8 = new ListNode(8);
		l1.next = l2;
		l2.next = l3;
		l3.next = l4;
		l4.next = l5;
		l5.next = l6;
		l6.next = l7;
		l7.next = l8;
		RotateRight r = new RotateRight();
		l1.print();
		ListNode node = r.rotateRight(l1, 2);
		node.print();
	}
}
