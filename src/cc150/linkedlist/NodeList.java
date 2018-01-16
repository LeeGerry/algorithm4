package cc150.linkedlist;

/**
 * 单链表
 */
public class NodeList<E> {
	/** 内部Node节点类 */
	static class Node<E> {
		E data; // 结点值
		Node<E> next;// next结点指针

		public Node(E data, Node<E> next) {
			this.data = data;
			this.next = next;
		}
	}

	/** 首结点 */
	private Node<E> head;
	/** 尾结点 */
	private Node<E> last;
	/** 辅助结点 */
	private Node<E> other;
	/** 元素个数 */
	private int size = 0;

	/** 默认构造 */
	public NodeList() {
		head = null;
		last = head;
	}

	/**
	 * 构造一个有参数的单链表
	 * @param data 初始化参数
	 */
	public NodeList(E data) {
		Node<E> d = new Node<>(data, null);
		head = d;
		last = head;
		size++;
	}

	/**
	 * 向单链表中增加一个结点
	 * @param data 节点值
	 */
	public void add(E data) {
		Node<E> d = new Node<>(data, null);
		if (isEmpty()) {
			head = d;
			last = head;
		} else {
			last.next = d;
			last = d;
		}
		size++;
	}

	/**
	 * 在单链表的索引index处添加data元素，其他元素后移
	 * @param index 索引
	 * @param data 新结点的值
	 */
	public void add(int index, E data) {
		checkIndex(index); // 检查索引是否合法
		other = head; // 用辅助结点指向头部，来对单链表进行操作
		if (index == 0) { // 如果是在头部插入新结点
			Node<E> d = new Node<>(data, other);
			head = d;
		} else { // 如果不是在头部插入，则需要先移动到index处
			for (int i = 0; i < index; i++) {
				other = other.next;
			}
			Node<E> d = new Node<>(data, other.next);
			other.next = d;
		}
		size++;
	}

	/**
	 * 第一个值为oldData的结点值替换为newData
	 * @param oldData
	 * @param newData
	 * @return
	 */
	public boolean set(E oldData, E newData) {
		other = head;
		while (other != null) {
			if (other.data.equals(oldData)) {
				other.data = newData;
				return true;
			}
			other = other.next;
		}
		return false;
	}

	/**
	 * 单链表中所有值为oldData的结点值设置为newData
	 * @param oldData
	 * @param newData
	 */
	public void setAll(E oldData, E newData) {
		other = head;
		while (other != null) {
			if (other.data.equals(oldData)) {
				other.data = newData;
			}
			other = other.next;
		}
	}

	/**
	 * 在第一个值为specialData的结点后新增一个值为newData的结点
	 * 
	 * @param specialData
	 * @param newData
	 * @return
	 */
	public boolean addAfter(E specialData, E newData) {
		other = head;
		while (other != null) {
			if (other.data.equals(specialData)) {
				Node<E> d = new Node<>(newData, other.next);
				other.next = d;
				size++;
				return true;
			}
			other = other.next;
		}
		return false;
	}

	/**
	 * 得到索引处的节点的值
	 * @param index
	 * @return
	 */
	public E get(int index) {
		checkIndex(index);
		other = head;
		for (int i = 0; i < index; i++) {
			other = other.next;
		}
		return other.data;
	}

	/**
	 * 删除第一个值为data的结点
	 * 
	 * @param data
	 * @return
	 */
	public boolean remove(E data) {
		other = head;
		Node<E> pre = other;
		for (int i = 0; i < size; i++) {
			if (other.data.equals(data)) {
				if (i == 0) {
					head = other.next;
					return true;
				}
				pre.next = other.next;
				return true;
			}
			pre = other;
			other = other.next;
		}
		return false;
	}

	/**
	 * 单链表中是否存在值为data的节点
	 * 
	 * @param data
	 * @return
	 */
	public boolean contains(E data) {
		other = head;
		for (int i = 0; i < size; i++) {
			if (other.data.equals(data)) {
				return true;
			}
			other = other.next;
		}
		return false;
	}

	/**
	 * 把单链表中的节点清空
	 */
	public void clear() {
		head = null;
		size = 0;
	}

	/**
	 * 检查索引是否合法
	 * 
	 * @param index
	 */
	private void checkIndex(int index) {
		if (index < 0 || index > size - 1)
			throw new IndexOutOfBoundsException("index invalid...");
	}

	/**
	 * 单链表是否为空
	 * 
	 * @return
	 */
	private boolean isEmpty() {
		return size == 0 || head == null;
	}

	/** 打印单链表 */
	public void print() {
		if (head == null || size == 0) {
			System.out.println("singly linked list is null.");
		} else {
			other = head;
			while (other != null) {
				System.out.print(other.data + " ");
				other = other.next;
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		NodeList<Integer> singlyLinkedList = new NodeList<>();
		for (int i = 0; i < 10; i++) {
			singlyLinkedList.add(i);
			singlyLinkedList.print();
		}
		singlyLinkedList.add(0, 11);
		singlyLinkedList.print();
		singlyLinkedList.set(1, 33);
		singlyLinkedList.print();
		singlyLinkedList.add(5);
		singlyLinkedList.print();
		singlyLinkedList.setAll(5, 76);
		singlyLinkedList.print();
		singlyLinkedList.addAfter(33, 44);
		singlyLinkedList.print();
		System.out.println(singlyLinkedList.get(5));
		singlyLinkedList.remove(76);
		singlyLinkedList.print();
		System.out.println("是否有8?"+singlyLinkedList.contains(8));
		singlyLinkedList.clear();
		System.out.println("是否为空链表?"+singlyLinkedList.isEmpty());
		
	}
}
