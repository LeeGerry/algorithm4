package me.gerry.uf;

public class QuickFind extends UF {

	public QuickFind(int n) {
		super(n);
	}

	@Override
	void union(int p, int q) {
		int pid = find(p);
		int qid = find(q);
		if (pid == qid)		return;
		for (int i = 0; i < id.length; i++)
			if (id[i] == pid)	id[i] = qid;
		this.count--;
	}

	@Override
	int find(int p) {return id[p];}
}
