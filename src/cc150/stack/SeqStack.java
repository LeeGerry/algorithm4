package cc150.stack;

import java.util.EmptyStackException;
/**
 * 顺序栈
 */
public class SeqStack<T> implements Stack<T> {

	private int top = -1;// 栈顶指针
	private int capacity = 10;// 容量大小，默认10
	private T[] array;// 存放元素的数组
	private int size;

	public SeqStack(int capacity) {
		array = (T[]) new Object[capacity];
	}

	public SeqStack() {
		array = (T[]) new Object[this.capacity];
	}

	@Override
	public boolean isEmpty() {
		return this.top == -1;
	}

	@Override
	public void push(T data) {
		if (array.length == size)
			extendCapacity(size * 2 + 1);
		array[++top] = data;
		size++;
	}

	private void extendCapacity(int capacity) {
		if (capacity < size)
			return;
		T[] old = array;
		array = (T[]) new Object[capacity];
		for (int i = 0; i < size; i++)
			array[i] = old[i];
	}

	@Override
	public T peek() {
		if (isEmpty())
			new EmptyStackException();
		return array[top];
	}
	
	public int size(){
		return size;
	}
	
	@Override
	public T pop() {
		if (isEmpty())
			new EmptyStackException();
		size--;
		return array[top--];
	}
	public static void main(String[] args) {
		SeqStack<Character> st = new SeqStack<>();
		for(char i = 97;i<107;i++)
			st.push(i);
		while(!st.isEmpty()){
			System.out.println(st.pop()+",size"+st.size());
		}
	}
}
