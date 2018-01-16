package cc150.linkedlist;

public class SplitListToParts {
	public ListNode[] splitListToParts(ListNode root, int k) {
		ListNode curr = root;
		// 计算长度
		int len = 0;
		while (curr != null) {
			len++;
			curr = curr.next;
		}
		// 计算每个应存的个数，和余数
		int width = len / k, rem = len % k;
		
		ListNode[] result = new ListNode[k];
		curr = root;
		for (int i = 0; i < k; i++) { // 填充每个数组
			ListNode head = new ListNode(0);
			ListNode temp = head;
			for (int j = 0; j < width + (i < rem ? 1 : 0); j++) { // 每个数组尽可能平均，余数平均给前面的数组中
				temp.next = new ListNode(curr.val);
				temp = temp.next;
				if(curr != null)	curr = curr.next;
			}
			result[i] = head.next;
		}
		return result;
	}
}
