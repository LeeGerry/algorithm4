package cc150.linkedlist;

import java.util.Stack;

public class ReverseBetween {
	public ListNode reverseBetween(ListNode head, int m, int n) {
		if (head == null)
			return null;
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode pre = dummy;
		for (int i = 0; i < m - 1; i++) {
			pre = pre.next;
		}
		ListNode start = pre.next;
		ListNode then = start.next;
		for (int i = 0; i < n - m; i++) {
			start.next = then.next;
			then.next = pre.next;
			pre.next = then;
			then = start.next;
		}
		return dummy.next;
	}

	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode curr1 = l1, curr2 = l2;
		Stack<Integer> s1 = new Stack<>();
		Stack<Integer> s2 = new Stack<>();
		Stack<Integer> result = new Stack<>();
		// store the elements of l1 into s1
		while(curr1 != null){
			s1.push(curr1.val);
			curr1 = curr1.next;
		}
		// store the elements of l2 into s2
		while(curr2 != null){
			s2.push(curr2.val);
			curr2 = curr2.next;
		}
		// store the carry
		int carry = 0;
		// plus 
		while(!s1.isEmpty() || !s2.isEmpty()){
			int from1 = s1.isEmpty()?0:s1.pop(); 
			int from2 = s2.isEmpty()?0:s2.pop();
			int sum = from1 + from2 + carry;
			result.push(sum % 10); // store the result of each step
			carry = sum/10; // fix the carry
		}
		// important: if the carry > 0, need to add the carry to the result
		if(carry > 0)
			result.push(1);
		
		ListNode dump = new ListNode(0);
		ListNode head = dump;
		// generate the ListNode according to the result stack
		while(!result.isEmpty()){
			head.next = new ListNode(result.pop());
			head = head.next;
		}
		return dump.next;
	}
	public static void main(String[] args) {
		ListNode l1 = new ListNode(4);
		ListNode l2 = new ListNode(7);
		ListNode l3 = new ListNode(3);
		ListNode l4 = new ListNode(4);
		l1.next = l2;
		l2.next = l3;
		l3.next = l4;
		
		ListNode l5 = new ListNode(5);
		ListNode l6 = new ListNode(9);
		ListNode l7 = new ListNode(9);
		ListNode l8 = new ListNode(9);
		l5.next = l6;
		l6.next = l7;
		l7.next = l8;
		ListNode result = addTwoNumbers(l1, l5);
		result.print();
	}
}
