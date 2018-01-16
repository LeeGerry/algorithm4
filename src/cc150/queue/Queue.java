package cc150.queue;

public interface Queue<T> {
	int size();
	boolean isEmpty();
	/** 入队，添加成功返回true，否则返回false，可扩容*/
	boolean add(T data);
	/** 插入一个元素，和add方法不同，只能通过抛出未经检查的异常使添加元素失败。
	 * 而不是出现异常的情况，例如在容量固定（有界）的队列中
	 * data==Null时抛出*/
	boolean offer(T data);
	/** 返回队头元素，不执行删除操作，若队列为空，返回null*/
	T peek();
	/** 返回队头元素，不执行删除操作，若队列为空，抛出异常*/
	T element();
	/** 出队，执行删除操作，返回队头元素，若队列为空，返回null*/
	T poll();
	/** 出队，执行删除操作，返回队头元素，若队列为空，抛出异常*/
	T remove();
	/** 清空队列*/
	void clear();
}
