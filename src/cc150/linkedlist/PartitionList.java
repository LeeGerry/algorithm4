package cc150.linkedlist;

/**
 * Given  1->4->3->2->5->2 and x = 3, 
 * return 1->2->2->4->3->5.
 */
public class PartitionList {
	public ListNode partition(ListNode head, int x) {
        ListNode dummy1 = new ListNode(0), dummy2 = new ListNode(0);
        ListNode curr1 = dummy1, curr2 = dummy2;
        while(head != null){
        	if(head.val < x){
        		curr1.next = head;
        		curr1 = head;
        	}else{
        		curr2.next = head;
        		curr2 = head;
        	}
        	head = head.next;
        }
        curr2.next = null;
        curr1.next = dummy2.next;
        return dummy1.next;
    }
	public static void main(String[] args) {
		
	}
}
