
public class SequentialSerachST <Key, Value>{
	private Node first;
	private class Node{
		Key key;
		Value value;
		Node next;
		public Node(Key k, Value v, Node next){
			this.key = k;
			this.value = v;
			this.next = next;
		}
	}
	public Value get(Key k){
		Node n = first;
		while(n!=null){
			if(k.equals(n.value)) return n.value;
			n = n.next;
		}
		return null;
	}
	public void put(Key k, Value v){
		Node n = first;
		while(n!=null){
			if(k.equals(n.value)){
				n.value = v; return;
			}
			n = n.next;
		}
		first = new Node(k,v,first);
	}
	public static void main(String[] args) {
		
	}
}
