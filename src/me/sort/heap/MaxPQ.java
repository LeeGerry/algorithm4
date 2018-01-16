package me.sort.heap;
/**
 * 优先级队列-大顶堆
 */
public class MaxPQ<Key extends Comparable<Key>> {
	private Key[] pq;	// 基于堆的完全二叉树，存储于pq[1...n]中，pq[0]没有使用
	private int n = 0;	
	public MaxPQ(int maxN){
		pq = (Key[]) new Comparable[maxN + 1];
	}
	public boolean isEmpty(){return n == 0;}
	public int size(){return n;}
	/** 向优先级队列中插入v*/
	public void insert(Key v){
		pq[++n] = v;		// n加一然后在n的位置放入k
		swim(n);			// 从n处开始进行上浮操作,恢复堆的有序性
	}
	/** 删除优先级队列队头的最大值，返回该值*/
	public Key delMax(){
		Key max = pq[1];	// 从根节点得到最大元素
		exchange(1, n--);	// 将其和最后一个节点交换，然后n减一
		pq[n+1] = null;		// 防止越界，把删掉的节点置空
		sink(1);			// 从1开始下沉，恢复堆的有序性
		return max;
	}
	/** 交换位置i和j处的元素*/
	private void exchange(int i, int j) {
		Key temp = pq[i];
		pq[i] = pq[j];
		pq[j] = temp;
	}
	/** 下沉操作，从k处下沉*/
	private void sink(int k) {
		while(2*k <= n){
			int j = 2*k;
			if(j<n && less(j,j+1))	j++;//找较大的那个子节点
			if(!less(k,j))	break;//如果K比较大的那个子节点大，则堆已经有序
			exchange(k,j);			// 交换k和较大的那个子节点
			k = j;	//k下沉
		}
	}
	/** 上浮操作，从k处上浮*/
	private void swim(int k) {
		while(k > 1 && less(k/2,k)){
			exchange(k/2,k);
			k = k/2;
		}
	}
	/** i处元素是否小于j处元素*/
	private boolean less(int i, int j){
		return pq[i].compareTo(pq[j]) < 0;
	}
}
