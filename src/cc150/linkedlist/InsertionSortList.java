package cc150.linkedlist;

public class InsertionSortList {
	public ListNode insertionSortList(ListNode head) {
		if(head == null)	return head;
		ListNode dummy = new ListNode(0);
		ListNode prev = dummy; // 记录在 prev 和 prev.next之间插入结点
		ListNode curr = head; // 记录当前要被插入的结点
		ListNode next = null; // 记录下一个要被插入的结点
		
		while(curr != null){
			next = curr.next;
			// 找到要插入的位置
			while(prev.next != null && prev.next.val < curr.val)
				prev = prev.next;
			curr.next = prev.next; // 1
			prev.next = curr; // 2. 1和2两步，把当前节点正确插入排好序的链表中
			prev = dummy; // dummy.next记录着排好序的链表的头部，每次向排好序的链表中插入成功后，prev重新回到dummy处
			curr = next; // 当前节点已经插入完毕，后移
		}
		return dummy.next;
	}
}
