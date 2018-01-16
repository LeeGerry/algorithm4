package me.gerry.uf;

public class WeightedQuickUnion extends UF{
	private int[] sz;
	public WeightedQuickUnion(int n) {
		super(n);
		sz = new int[n];
		for(int i =0;i<n;i++)	sz[i] = 1;
	}

	@Override
	void union(int p, int q) {
		int i = find(p);//找到p所在树的根
		int j = find(q);//找到q所在树的根
		if(i == j)	return;//如果相等，则表示两者在同一个树中
		if(sz[i] < sz[j]){//如果p所在的树小于q所在的树
			id[i] = j;//p所在数的根节点连到q所在树的根节点
			sz[j] += sz[i];//q所在的树的大小更新为两棵树大小的和
		}else{//反之
			id[j] = i;
			sz[i] += sz[j];
		}
		count--;//最后将连通分量的个数减一
	}

	@Override
	int find(int p) {
		while(p != id[p])	p = id[p];
		return p;
	}
	
}
