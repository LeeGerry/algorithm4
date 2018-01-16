package cc150.linkedlist;

class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
	}
	public void print(){
		ListNode h = this;
		while(h != null){
			System.out.print(h.val + " ");
			h = h.next;
		}
		System.out.println();
	}
}

// Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
// Output: 7 -> 0 -> 8
// Explanation: 342 + 465 = 807.
public class AddIntegerList3 {

	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		int len1 = len(l1);
		int len2 = len(l2);
		if(len1 > len2) l2 = pad(l2, len1-len2);
		else			l1 = pad(l1, len2-len1);
		ListNode dump = new ListNode(0);
		ListNode curr = dump;
		int carry = 0;
		while(l1!=null){
			int sum = carry + l1.val + l2.val;
			ListNode n = new ListNode(sum % 10);
			curr.next = n;
			carry = sum / 10;
			l1 = l1.next;
			l2 = l2.next;
			curr = curr.next;
		}
		if(carry > 0) curr.next = new ListNode(carry);
		return dump.next;
	}
	//length
	public int len(ListNode l){
		int len = 0;
		while(l != null){
			len++;
			l = l.next;
		}
		return len;
	}
	//pad
	public ListNode pad(ListNode l, int offset){
		ListNode h = l;
		while(h != null && h.next != null) 	h = h.next;
		for(int i = 0;i<offset;i++){
			ListNode n = new ListNode(0);
			h.next = n;
			h = n;
		}
		return l;
	}
	
	public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
		ListNode dump = new ListNode(0);
		ListNode m = l1, n = l2, curr = dump;
		int carry = 0;
		while(m!=null || n!=null){
			int x = (m==null ? 0:m.val);
			int y = (n==null ? 0:n.val);
			int sum = x+y+carry;
			carry = sum/10;
			curr.next = new ListNode(sum%10);
			curr = curr.next;
			if(m!=null) m = m.next;
			if(n!=null) n = n.next;
		}
		if(carry > 0) curr.next = new ListNode(carry);
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
		AddIntegerList3 add = new AddIntegerList3();
		l1.print();
		l5.print();
		ListNode result = add.addTwoNumbers2(l1, l5);
		result.print();
	}
}
