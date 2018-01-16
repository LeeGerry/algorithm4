package cc150.linkedlist;

import java.util.Stack;

public class IsPalindrome {
	boolean isP(LinkedListNode head){
		LinkedListNode fast = head, slow = head;
		Stack<Integer> stack = new Stack<Integer>();
		while(fast != null && fast.next != null){
			stack.push(slow.value);
			slow = slow.next;
			fast = fast.next.next;
		}
		//链表中有奇数个元素，跳过中间元素
		if(fast != null)	
			slow = slow.next;
		while(slow != null){
			int top = stack.pop().intValue();
			if(top != slow.value)
				return false;
			slow = slow.next;
		}
		return true;
	}
	public static void main(String[] args) {
		
		LinkedListNode n0 = new LinkedListNode();
		n0.value = 0;
		LinkedListNode n1 = new LinkedListNode();
		n1.value = 1;
		LinkedListNode n2 = new LinkedListNode();
		n2.value = 2; 
		LinkedListNode n3 = new LinkedListNode();
		n3.value = 1; 
		LinkedListNode n4 = new LinkedListNode();
		n4.value = 0; 
		n0.next = n1;
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = null;
		IsPalindrome is = new IsPalindrome();
		System.out.println(is.isP(n0));
	}
}
