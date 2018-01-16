package cc150.linkedlist;
class TreeNode{
	int val;
	TreeNode left, right;
	TreeNode(int x){
		this.val = x;
	}
}
public class SortedListToBst {
    public TreeNode sortedListToBST(ListNode head) {
    	if(head == null) return null;
    	return toBST(head, null);
    }

	private TreeNode toBST(ListNode head, ListNode tail) {
		if(head == tail)	return null;
		ListNode fast = head, slow = head;
		while(fast != tail && fast.next != tail){
			fast = fast.next.next;
			slow = slow.next;
		}
		TreeNode node = new TreeNode(slow.val);
		node.left = toBST(head, slow);
		node.right = toBST(slow.next, tail);
		return node;
	}
}
