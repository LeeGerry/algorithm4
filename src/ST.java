/**
 * 可用链表实现；
 * @author liguorui
 *
 * @param <Key>
 * @param <Value>
 */
public abstract class ST<Key extends Comparable<Key>, Value extends Comparable<Value>> {
	/**
	 * create an ordered symbol table
	 * @param key
	 * @param value
	 */
	abstract void put(Key key, Value value);
	/**
	 * put key-value pair into the table 
	 * (remove key from table if value is null)
	 * @param key
	 * @return
	 */
	abstract Value get(Key key);
	/**
	 * remove key (and its value ) from table
	 * @param key
	 */
	abstract void delete(Key key);
	/**
	 * is there a value paired with key?
	 * @param key
	 * @return
	 */
	abstract boolean contains(Key key);
	/**
	 * is the table empty?
	 * @return
	 */
	abstract boolean isEmpty();

	/**
	 * number of K-V pairs
	 * @return
	 */
	abstract int size();
	/**
	 * smallest key
	 * @return
	 */
	abstract Key min();
	/**
	 * largest key
	 * @return
	 */
	abstract Key max();
	/**
	 * largest key less than or equal to key
	 * @param key
	 * @return
	 */
	abstract Key floor(Key key);

	/**
	 * smallest key greater than or equal to key
	 * @param key
	 * @return
	 */
	abstract Key ceiling(Key key);
	/**
	 * number of keys less than key
	 * @param key
	 * @return
	 */
	abstract int rank(Key key);

	/**
	 * key of rank k
	 * @param k
	 * @return
	 */
	abstract Key select(int k);
	/**
	 * delete smallest key
	 */
	abstract void deleteMin();
	/**
	 * delete largest key
	 */
	abstract void deleteMax();
	/**
	 * number of keys in [lo, hi]
	 * @param lo
	 * @param hi
	 * @return
	 */
	abstract int size(Key lo, Key hi);
	/**
	 * keys in [lo, hi], in sorted order
	 * @param lo
	 * @param hi
	 * @return
	 */
	abstract Iterable<Key> keys(Key lo, Key hi);
	/**
	 * all keys in the table, in sorted order
	 * @return
	 */
	abstract Iterable<Key> keys();
}
