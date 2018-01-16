package cc150.hashtable;

public class MyHashTable<K, V> {
	private Entry<?, ?>[] table;
	private int capacity;
	
	public MyHashTable(){
		this(11);
	}
	public MyHashTable(int capacity){
		if(capacity < 0)
			throw new IllegalArgumentException("capacity invalid");
		this.capacity = capacity;
		table = new Entry<?, ?>[capacity];
	}
	private static class Entry<K,V>{
		int hash;
		K key;
		V value;
		Entry<K, V> next;
		
		public Entry(int hash, K key, V value, Entry<K, V> next){
			this.hash = hash;
			this.key = key;
			this.value = value;
			this.next = next;
		}
		
		public K getKey(){
			return key;
		}
		
		public V getValue(){
			return value;
		}
	}
	public V get(Object key){
		Entry<?, ?>[] tab = table;
		int hash = key.hashCode() & 0x7ffffff;
		int index = hash % table.length;
		for(Entry<?,?> entry = tab[index]; entry!= null; entry = entry.next){
			if(entry.hash == hash && key == entry.key)
				return (V)entry.value;
		}
		return null;
	}
	public V put(K key, V value){
		Entry<?, ?>[] tab = table;
		int hash = key.hashCode();
		int index = (hash & 0x7ffffff) % tab.length;
		Entry<K, V> entry = (Entry<K, V>)tab[index];
		for(;entry != null; entry = entry.next){
			if(entry.hash == hash && key == entry.key){
				V oldValue = entry.value;
				entry.value = value;
				return oldValue;
			}
		}
		addEntry(hash, key, value, index);
		return null;
	}
	private void addEntry(int hash, K key, V value, int index){
		Entry<?, ?>[] tab = table;
		Entry<K, V> entry = (Entry<K, V>) tab[index];
		tab[index] = new Entry<>(hash, key, value, entry);
	}
	public static void main(String[] args) {
		MyHashTable<String, Integer> h = new MyHashTable<>(5);
		h.put("one", 1);
		h.put("two", 2);
		h.put("three", 3);
		Integer v = h.get("two");
		System.out.println(v);
	}
}
