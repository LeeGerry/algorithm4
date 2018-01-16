package me.practice;


class DListNode{
	int value;
	DListNode prev, next;
	DListNode(int value){
		this.value = value;
		this.prev = this.next = null;
	}
	public DListNode reverse(DListNode head){
		DListNode current = null;
		while(head != null){
			current = head;
			head = current.next;
			current.next = current.prev;
			current.prev = head;
		}
		return current;
	}
}



public class ListNode {
	public int val;
	public ListNode next;
	public ListNode(int val){
		this.val = val;
		this.next = null;
	}
	
	public ListNode reverse(ListNode head){
		ListNode prev = null;
		while(head != null){
			ListNode next = head.next;
			head.next = prev;
			prev = head;
			head = next;
		}
		return prev;
	}
}
