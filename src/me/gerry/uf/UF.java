package me.gerry.uf;

public abstract class UF {
	protected int[] id;
	protected int count;

	public UF(int n) {
		count = n;
		id = new int[n];
		for (int i = 0; i < n; i++)
			id[i] = i;
	}

	boolean connected(int p, int q) {
		return find(p) == find(q);
	}

	int count() {
		return count;
	}

	abstract void union(int p, int q);

	abstract int find(int p);
}
