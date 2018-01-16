package cc150.linkedlist;

public class SortList {
	public ListNode sortList(ListNode head){
		if(head == null || head.next == null)	return head;
		// 用快慢指针把链表分为两部分
		ListNode prev = null, slow = head, fast = head;
		while(fast != null && fast.next != null){
			prev = slow;
			slow = slow.next;
			fast = fast.next.next;
		}
		prev.next = null; // 此处是断开操作!!!
		
		// 分别递归前后两个链表
		ListNode l1 = sortList(head);
		ListNode l2 = sortList(slow);
		
		// 合并起来
		return merge(l1, l2);
	}

	private ListNode merge(ListNode l1, ListNode l2) {
		ListNode dummy = new ListNode(0);
		ListNode curr = dummy;
		while(l1 != null && l2 != null){
			if(l1.val < l2.val){//若l1首部元素小于l2
				curr.next = l1; // next指针指向l1
				l1 = l1.next;	// l1右移
			}else{	
				curr.next = l2;
				l2 = l2.next;
			}
			curr = curr.next;
		}
		// 至此，l1完毕或者l2完毕，或者同时完毕。把curr的next指向有剩余的部分
		if(l1 != null)	curr.next = l1;//
		if(l2 != null)	curr.next = l2;
		return dummy.next;
	}
}
