package cc150.stack;

import java.util.EmptyStackException;

class MyNode<T>{
	T data;
	MyNode<T> next;
	public MyNode(){}

	public MyNode(T data){
		this.data = data;
	}
	public MyNode(T data, MyNode<T> n){
		this.data = data;
		this.next = n;
	}
}
/**
 * 链式栈
 */
public class LinkedStack<T> implements Stack<T> {
	private MyNode<T> top;
	private int size;
	public LinkedStack(){
		this.top = new MyNode<T>();
	}
	public int size(){
		return size;
	}
	@Override
	public boolean isEmpty() {
		return top == null || top.data == null;
	}

	@Override
	public void push(T data) {
		if(data == null){
			System.out.println("data can't be null");
			return ;
		}
		if(this.top == null)	
			this.top = new MyNode<T>(data);
		else if(this.top.data == null)	
			this.top.data = data;
		else	
			top = new MyNode<T>(data, this.top);
		size++;
	}

	@Override
	public T peek() {
		if(isEmpty()) throw new EmptyStackException();
		return top.data;
	}

	@Override
	public T pop() {
		if(isEmpty())	throw new EmptyStackException();
		T data = top.data;
		top = top.next;
		size--;
		return data;
	}
	public static void main(String[] args) {
		LinkedStack<String> ls = new LinkedStack<>();
		ls.push("ab");System.out.println(ls.peek());
		ls.push("cd");System.out.println(ls.peek());
		ls.push("ef");System.out.println(ls.peek());
		System.out.println(ls.size());
		while(!ls.isEmpty()){
			System.out.println(ls.pop());
		}
		System.out.println(ls.size());
	}
}
