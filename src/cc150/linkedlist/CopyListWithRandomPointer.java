package cc150.linkedlist;

class RandomListNode{
	int label;
	RandomListNode next, random;
	RandomListNode(int x){
		this.label = x;
	}
}
public class CopyListWithRandomPointer {
    public RandomListNode copyRandomList(RandomListNode head) {
    	if(head == null) 	return null;
    	RandomListNode curr = head;
    	
    	// 第一遍扫描，复制每个结点，放在原结点之后
    	while(curr != null){
    		RandomListNode newNode = new RandomListNode(curr.label);
    		newNode.next = curr.next;
    		curr.next = newNode;
    		curr = newNode.next;
    	}
    	
    	// 第二遍扫描，根据原结点的random，给新结点的random赋值
    	curr = head;
    	while(curr != null){
    		if(curr.random != null)
    			curr.next.random = curr.random.next;
    		curr = curr.next.next;
    	}
    	
    	RandomListNode newHead = head.next;
    	// 第三遍扫描，把新结点从原链表中拆出来
    	curr = head;
    	while(curr != null){
    		RandomListNode newNode = curr.next;
    		curr.next = newNode.next;
    		if(newNode.next != null)	
    			newNode.next = newNode.next.next;
    		curr = curr.next;
    	}
    	return newHead;
    }
}
