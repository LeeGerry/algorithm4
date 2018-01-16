package cc150.stack;
/**
 * 
 */
public interface Stack<T> {
	boolean isEmpty();
	void push(T data);
	T peek();
	T pop();
}
