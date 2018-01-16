package ch2.sub4;

import java.io.File;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;

/**
 * 基于堆的优先队列
 */
public class MaxPQ<Key extends Comparable<Key>>{
	private Key[] pq;	//基于堆的完全二叉树
	private int N = 0;	//存储于pq[1..N]中，pq[0]没有使用
	public MaxPQ(int max){
		pq =  (Key[]) new Comparable[max+1];
	}
	public boolean isEmpty(){return N==0;}
	public int size(){return N;}
	public void insert(Key v){
		pq[++N] = v;	//N 先自增，然后在数组N的位置放入新值
		swim(N);		//N进行上浮
	}
	public Key delMax(){
		Key max = pq[1];	//从根节点得到最大元素
		exch(1, N--);		//根节点和最后一个节点交换，然后N减一
		pq[N+1] = null;		//防止越界
		sink(1);			//从根的位置进行下沉操作
		return max;			//返回最大元素
	}
	/**下沉*/
	private void sink(int k) {
		while(2*k <= N){	//保证边界
			int j = 2*k;
			if(j<N && less(j, j+1)) j++;//取两个子节点中较大的那个
			if(!less(k, j))	break;		//父节点大于较大的那个子节点，则break
			exch(k, j);					//否则的话交换，父节点下沉，把较大的子节点浮上去
			k = j;						//更新k，继续while循环下沉
		}
	}
	/**上浮*/
	private void swim(int k) {
		while(k>1 && less(k/2,k)){//父节点小的话一直上浮，同时要k>1保证边界
			exch(k/2,k);//交换
			k = k/2;	//更新k
		}
	}
	private boolean less(int i, int j){
		return pq[i].compareTo(pq[j]) < 0;
	}
	private void exch(int i, int j) {
		Key temp = pq[i];
		pq[i] = pq[j];
		pq[j] = temp;
	}
	
	public static void main(String[] args) {
		String filePath = MaxPQ.class.getClassLoader().getResource("ch2/sub4/num.txt").getPath();
		int M = 5;
		In in = new In(new File(filePath));
		MaxPQ<Integer> pq = new MaxPQ<>(M+1);
		int[] ints = in.readAllInts();
		for(int i = 0;i<ints.length;i++){
			pq.insert(ints[i]);
			if(pq.size() > M) pq.delMax();
		}
		Stack<Integer> stack = new Stack<>();
		while(!pq.isEmpty()) stack.push(pq.delMax());
		for(Integer t: stack) 
			System.out.println(t);
	}
}
