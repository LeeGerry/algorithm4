import javax.swing.SpringLayout.Constraints;

import edu.princeton.cs.algs4.Queue;

public class BinarySearchST <Key extends Comparable<Key>, Value>{
	private Key[] keys;
	private Value[] values;
	private int N;
	public BinarySearchST(int capacity){
		keys = (Key[]) new Comparable[capacity];
		values = (Value[]) new Object[capacity];
	}
	public int size()	{ return N; }
	public Value get(Key key){
		if(isEmpty()) return null;
		int i = rank(key);
		if(i < N && keys[i].compareTo(key) == 0)	return values[i];
		else										return null;
	}
	private int rank(Key key) {
		int lo = 0, hi = N-1;
		while(lo<=hi){
			int mid = lo + (hi - lo) /2;
			int cmp = key.compareTo(keys[mid]);
			if(cmp < 0)			hi = mid -1;
			else if (cmp > 0) 	lo = mid + 1;
			else				return mid;
		}
		return lo;
	}
	public boolean isEmpty()	{ return N == 0; }
	public void put(Key key, Value value){
		int i = rank(key);
		if(i<N && keys[i].compareTo(key) == 0){
			values[i] = value; return;
		}
		for(int j = N; j>i; j--){
			keys[j] = keys[j-1]; values[j] = values[j-1];
		}
		keys[i] = key;
		values[i] = value;
		N++;
	}
	public Key min(){
		return keys[0];
	}
	public Key max(){
		return keys[N-1];
	}
	public Key ceiling(Key key){
		int i = rank(key);
		return keys[i];
	}
	public Iterable<Key> keys(Key lo, Key hi){
		Queue<Key> q = new Queue<>();
		for(int i = rank(lo); i< rank(hi); i++){
			q.enqueue(keys[i]);
		}
		if(constraints(hi))
			q.enqueue(keys[rank(hi)]);
		return q;
	}
	private boolean constraints(Key hi) {
		return false;
	}
	public Key select(int k){
		return keys[k];
	}
	private int rank(Key key, int lo, int hi){
		if(hi < lo) return lo;
		int mid = lo + (hi - lo) / 2;
		int cmp = key.compareTo(keys[mid]);
		if(cmp < 0)			return rank(key, lo, mid-1);
		else if(cmp > 0) 	return rank(key, mid+1, hi);
		else 				return mid;
	}
}
