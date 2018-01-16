package me.gerry.uf;

public class QuickUnion extends UF{
	public QuickUnion(int n) {super(n);}

	@Override
	void union(int p, int q) {
		int proot = find(p);
		int qroot = find(q);
		if(proot == qroot)	return;
		id[proot] = qroot;
		count--;
	}

	@Override
	int find(int p) {
		while(p != id[p])	p = id[p];
		return p;
	}

}
