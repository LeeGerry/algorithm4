package cc150.queue;

public class SeqQueue<T> implements Queue<T> {
	
	private static final int DEFAULT_SIZE = 10;
	private T elements[];
	private int front, rear;
	private int size;
	public SeqQueue(){
		elements = (T[]) new Object[DEFAULT_SIZE];
		front = rear = 0;
	}
	public SeqQueue(int capacity){
		elements = (T[]) new Object[capacity];
		front = rear = 0;
	}
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return front == rear;
	}

	@Override
	public boolean add(T data) {
		//if(this.front == this.rear)
		return false;
	}

	@Override
	public boolean offer(T data) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public T peek() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T element() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T poll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T remove() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

}
