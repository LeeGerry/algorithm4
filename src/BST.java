
public class BST <Key extends Comparable<Key>, Value>{
	private Node root ;
	private class Node{
		private Key key;
		private Value value;
		private Node left, right;
		private int N;
		
		public Node(Key key, Value val, int N)
		{	this.key = key; this.value = val; this.N = N; }
		
	}
	public int size()
	{ return size(root); }
	
	private int size(Node x){
		if(x == null) return 0;
		return x.N;
	}
	public Value get(Key key){return get(root, key);}
	private Value get(Node x, Key key){
		if(x == null) return null;//找不到返回null
		int cmp = key.compareTo(x.key);//把key值与当前节点key比较
		if(cmp < 0)			return get(x.left, key);//如果小，找左子树
		else if(cmp > 0) 	return get(x.right, key);//如果大，找右子树
		else				return x.value;//如果相等，返回该节点对应的V值
	}
	public void put(Key key, Value value){
		root = put(root, key, value);//查找key，找到则更新，否则创建新节点
	}
	private Node put(Node x, Key key, Value val){
		//若key存在于以x为根节点的子树中，则更新其值
		//否则将以key value为键值对的新节点插入到该子树
		if(x == null)	return new Node(key,val,1);//如果树为空，返回键值对新节点
		int cmp = key.compareTo(x.key);//不为空，比较key和当前节点的key
		if(cmp < 0)	x.left = put(x.left, key, val);//若小，则在左子树中插入
		else if(cmp > 0) x.right = put(x.right, key, val);//若大，则在右子树插入
		else x.value = val;//若等，则更新value
		x.N  = size(x.left) + size(x.right) +1 ;//最后更新大小
		return x;
	}
	/**
	 * 最小节点
	 * @return
	 */
	public Key min(){
		return min(root).key;//通过私有方法递归
	}
	private Node min(Node x){
		if(x.left == null)	return x;//如果左子树为空，则返回根
		return min(x.left);//如果不为空，则递归左子树
	}
	/**
	 * 最大节点
	 * @return
	 */
	public Key max(){
		return max(root).key;
	}
	private Node max(Node x){
		if(x.right == null)	return x;//如果右子树为空，则返回空
		return max(x.right);//否则，递归右子树
	}
	/**
	 * 向上取整
	 * @param key
	 * @return
	 */
	public Key floor(Key key){
		Node x = floor(root, key);//通过私有方法得到节点
		if(x == null)	return null;//若节点为空，则返回空
		return x.key;//否则，返回节点的key
	}
	
	private Node floor(Node x, Key key){
		if(x == null)	return null;	//若节点为空，则返回空	
		int cmp = key.compareTo(x.key);//比较key值和节点的key值
		if(cmp == 0)	return x;//若相等，则返回即可
		if(cmp < 0)		return floor(x.left, key);//若小，则向左子树递归找,并返回
		Node t = floor(x.right, key);//若大，则向右子树递归找
		if(t!=null)		return t;//在右子树找到不为空，则返回
		else			return x;//在右子树没找到，则返回沿途下来的根
	}
	public Key ceiling(Key key){
		Node x = ceiling(root, key);
		if(x == null) 	return null;
		return x.key;
	}
	private Node ceiling(Node x, Key key){
		if(x == null)	return null;
		int cmp = key.compareTo(x.key);
		if(cmp == 0)	return x;
		if(cmp > 0)		return ceiling(x.right, key);
		Node t = ceiling(x.left, key);
		if(t!=null)		return t;
		else			return x;
	}
	public Key select(int k){
		return select(root, k).key;
	}
	/**
	 * 要找到排名为k的键，（树中正好有k个小于它的键）。
	 * 如果左子树中的节点数t>k, 那么就继续递归左子树，查找排名为k的键；
	 * 如果t == k,就返回根节点中的键；
	 * 如果t < k,就递归地在右子树中查找排名为k-t-1的键
	 * @param x
	 * @param k
	 * @return
	 */
	private Node select(Node x, int k){
		//返回排名为k的节点
		if(x == null) 	return null;
		int t = size(x.left);
		if(t > k)		return select(x.left, k);
		else if(t < k)	return select(x.right, k-t-1);
		else			return x;
	}
	/**
	 * rank()是select()的逆方法，会返回给定键的排名。实现和select类似：
	 * 如果给定的键和根节点的键相等，返回左子树中节点总数t;
	 * 如果给定的键小于根节点，返回该键在左子树中的排名（递归计算）；
	 * 如果给定的键大于根节点，返回t+1(根节点)加上它在右子树中的排名（递归计算）。
	 * @param key
	 * @return
	 */
	public int rank(Key key){
		return rank(key, root);
	}
	private int rank(Key key, Node x){
		//返回以x为根节点的子树中小于x.key的键的数量
		if(x == null)		return 0;
		int cmp = key.compareTo(x.key);
		if(cmp < 0)			return rank(key, x.left);
		if(cmp > 0)			return 1+size(x.left)+rank(key, x.right);
		else				return size(x.left);
	}
	
	public void deleteMin(){
		root = deleteMin(root);
	}
	/**
	 * 和put()一样，deleteMin接受一个指向节点的链接，并返回一个指向节点的链接。
	 * 不断深入根节点的左子树中直至遇到一个空链接，然后将指向该节点的链接指向该节点的右子树
	 * (只需在递归调用中返回它的右链接即可)。此时已经没有任何链接指向要被删除的节点，被GC.
	 * 该递归代码在删除节点后会正确地设置其父节点的链接并更新它到根节点的路径上的所有节点的计数器值
	 */
	private Node deleteMin(Node x){
		if(x.left == null)		return x.right;
		x.left = deleteMin(x.left);
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}
	
	public void delete(Key key){
		root = delete(root,key);
	}
	private Node delete(Node x, Key key){
		if(x == null)	return null;
		int cmp = key.compareTo(x.key);
		if(cmp < 0)			x.left = delete(x.left, key);
		else if(cmp > 0)	x.right = delete(x.right, key);
		else{
			if(x.right == null)		return x.left;
			if(x.left == null)		return x.right;
			Node t = x;
			x = min(t.right);
			x.right = deleteMin(t.right);
			x.left = t.left;
		}
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}
	
	
}
