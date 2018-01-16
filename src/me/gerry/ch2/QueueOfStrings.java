package me.gerry.ch2;

public class QueueOfStrings {
	private Node first = null;
	private Node last = null;
	private int number = 0;

	private class Node {
		String item;
		Node next;
	}

	public void enqueue(String item) {
		Node oldlast = last;
		last = new Node();
		last.item = item;
		last.next = null;
		if (isEmpty())
			first = last;
		else
			oldlast.next = last;
		number++;
	}

	public String dequeue() {
		String temp = first.item;
		first = first.next;
		if (isEmpty())
			last = null;
		number--;
		return temp;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public int size() {
		return number;
	}

	public static void main(String[] args) {
		QueueOfStrings q = new QueueOfStrings();
		for (int i = 0; i < 50; i++) {
			q.enqueue(i+"'s item");
		}
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		System.out.println(q.size());
	}
}
