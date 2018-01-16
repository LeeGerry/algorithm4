package cc150.linkedlist;

import java.util.NoSuchElementException;
/** 双向链表的实现*/
public class DoubleNodeList<E> {
	/** 结点类*/
	private static class Node<E>{
		E item;
		Node<E> next;
		Node<E> prev;
		Node(Node<E> prev, E item, Node<E> next){
			this.prev = prev;
			this.item = item;
			this.next = next;
		}
	}
	/** 头结点*/
	private Node<E> first;
	/** 尾结点*/
	private Node<E> last;
	/** 双向链表大小*/
	private int size = 0;
	
	/** 在双向链表头部增加一个值为e的元素*/
	private void linkFirst(E e){
		Node<E> f = first;
		Node<E> newNode = new Node<E>(null, e, f);
		if(f == null)	last = newNode;
		else			f.prev = newNode;
		first = newNode;
		size++;
	}
	
	/** 在双向链表尾部增加一个值为e的新元素*/
	private void linkLast(E e){
		Node<E> l = last;
		Node<E> newNode = new Node<>(l, e, null);
		if(l == null)	first = newNode;
		else			l.next = newNode;
		last = newNode;
		size++;
	}
	/** 在指定节点n之前插入元素e*/
	private  void linkBefore(E e, Node<E> n){
		Node<E> pre = n.prev;
		Node<E> newNode = new Node<>(pre, e, n);
		n.prev = newNode;
		if(pre == null)	first = newNode;
		else			pre.next = newNode;
		size++;
	}
	/** 取消首结点的引用*/
	private E unlinkFirst(Node<E> f){
		final E element = f.item;
		final Node<E> next = f.next;
		f.item = null;
		f.next = null;
		first = next;
		if(next == null)	last = null;
		else				next.prev = null;
		size--;
		return element;
	}
	/** 取消尾结点的引用*/
	private E unlinkLast(Node<E> l){
		final E element = l.item;
		final Node<E> pre = l.prev;
		l.item = null;
		l.prev = null;
		last = pre;
		if(pre == null)		first = null;
		else				pre.next = null;
		size--;
		return element;
	}
	/** 取消结点n的引用*/
	private E unlink(Node<E> n){
		final E element = n.item;
		final Node<E> next = n.next;
		final Node<E> pre = n.prev;
		// 如果是第一个
		if(pre == null)	
			first = next;
		else{
			pre.next = next;
			n.prev = null;
		}
		// 如果是最后一个
		if(next == null)	
			last = pre;
		else{
			next.prev = pre;
			n.next = null;
		}
		
		n.item = null;
		size--;
		return element;
	}
	
	/** 获得第一个元素*/
	public E getFirst(){
		final Node<E> f = first;
		if(f == null)	throw new NoSuchElementException();
		return f.item;
	}
	
	/** 获得最后一个元素*/
	public E getLast(){
		final Node<E> l = last;
		if(l == null)	throw new NoSuchElementException();
		return l.item;
	}
	
	/** 删除第一个元素*/
	public E removeFirst(){
		final Node<E> f = first;
		if(f == null)	throw new NoSuchElementException();
		return unlinkFirst(f);
	}
	
	/** 删除最后一个元素*/
	public E removeLast(){
		final Node<E> l = last;
		if(l == null)	throw new NoSuchElementException();
		return unlinkLast(l);
	}
	
	/** 增加元素到首部*/
	public void addFirst(E e){
		linkFirst(e);
	}
	
	/** 增加元素到尾部*/
	public void addLast(E e){
		linkLast(e);
	}
	
	/** 在索引index处插入元素*/
	public void add(int index, E element){
		checkIndex(index);
		if(index == size)	linkLast(element);
		else				linkBefore(element, node(index));
	}
	
	/** 增加元素,默认增加在尾部*/
	public boolean add(E e){
		linkLast(e);
		return true;
	}
	/** 删除索引为index的元素*/
	public E remove(int index){
		checkIndex(index);
		return unlink(node(index));
	}
	/** 若对象o存在于双链表中，则删除。若双链表发生改变返回true，否则返回false*/
	public boolean remove(Object o){
		if(o == null){
			for(Node<E> x = first; x!=null; x = x.next){
				if(x.item == null){
					unlink(x);
					return true;
				}
			}
		}else{
			for(Node<E> x = first; x!=null; x = x.next){
				if(o.equals(x.item)){
					unlink(x);
					return true;
				}
			}
		}
		return false;
	}
	/** 清空双链表，所有引用对象置为null*/
	public void clear(){
		for(Node<E> x = first; x!=null;){
			Node<E> next = x.next;
			x.item = null;
			x.prev = null;
			x.next = null;
			x = next;
		}
		first = null;
		last = null;
		size = 0;
	}
	/** 获取索引index处的元素值*/
	public E get(int index){
		checkIndex(index);
		return node(index).item;
	}
	/**替换索引index处node的值为element，返回旧值*/
	public E set(int index, E element){
		checkIndex(index);
		Node<E> oldNode = node(index);
		E oldValue = oldNode.item;
		oldNode.item = element;
		return oldValue;
	}
	/** 是否包含元素*/
	public boolean contains(Object o){
		return indexOf(o) != -1;
	}
	/** 获取对象在双链表中的索引*/
	private int indexOf(Object o) {
		int index = 0;
		if(o == null){
			for(Node<E> x = first; x!=null; x = x.next){
				if(x.item == null)
					return index;
				index++;
			}
		}else{
			for(Node<E> x = first; x!=null; x = x.next){
				if(o.equals(x.item))
					return index;
				index++;
			}
		}
		return -1;
	}
	/** 双链表大小*/
	public int getSize(){
		return size;
	}
	/** 根据索引得到节点*/
	public Node<E> node(int index){
		// 如果index小于size的一半，说明在前半部，则正序查找
		if(index < (size >> 1)){
			Node<E> f = first;
			for(int i=0; i<index; i++)
				f = f.next;
			return f;
		}else{//否则倒序查找
			Node<E> l = last;
			for(int i=size-1; i > index; i--)
				l = l.prev;
			return l;
		}
	}
	/** 越界错误信息*/
	private String outOfBoundsMsg(int index){
		return "Index: " + index + ", size:" + size;
	}
	/** 检查索引是否正确*/
	private void checkIndex(int index){
		if(!isElementIndex(index)){
			throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
		}
	}
	/** 检查索引是否合法*/
	private boolean isElementIndex(int index){
		return index >= 0 && index < size;
	}
	/** print*/
	public void print(){
		Node<E> f = first;
		StringBuilder sb = new StringBuilder();
		while(f != null){
			sb.append(f.item).append(" ");
			f = f.next;
		}
		System.out.println(sb.toString());
	}
	public static void main(String[] args) {
		DoubleNodeList<Integer> dl = new DoubleNodeList<>();
		for(int i = 0; i< 10;i++){
			dl.add(i);
			dl.print();
		}
		System.out.println(dl.getFirst());
		System.out.println(dl.getLast());
		System.out.println("del first :"+dl.removeFirst());
		dl.print();
		System.out.println("del last :"+dl.removeLast());
		dl.print();
		dl.addFirst(21);
		dl.print();
		dl.addLast(88);
		dl.print();
		dl.add(2,33);
		dl.print();
		dl.remove(1);
		dl.print();
		//dl.remove(99);
		dl.remove(new Integer(33));
		dl.print();
		System.out.println(dl.node(4).item);
		System.out.println(dl.set(4, 54));
		dl.print();
		System.out.println(dl.get(2));
		System.out.println(dl.contains(new Integer(4)));
		System.out.println(dl.getSize());
		dl.clear();
		System.out.println(dl.getSize());
	}
	
}
