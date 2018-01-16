package me.gerry.uf;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

class Data {
	int first;
	int second;
	public Data() {}
	@Override
	public String toString() {
		return first + "," + second;
	}
}

public class UFDemo {
	public static void main(String[] args) {
		List<Data> list = new ArrayList<>();
		Random r = new Random();
		int number = 1000000;
		Data d;
		for (int i = 0; i < number; i++) {
			d = new Data();
			d.first = r.nextInt(number);
			d.second = r.nextInt(number);
			list.add(d);
		}
		UF uf = new WeightedQuickUnion(number);
		Stopwatch w = new Stopwatch();
		for (Data data : list) {
			if (uf.connected(data.first, data.second))
				continue;
			uf.union(data.first, data.second);
		}
		double time = w.elapsedTime();
		StdOut.println(uf.count() + " components" + " time : " + time);
		UF uf1 = new QuickUnion(number);
		Stopwatch w1 = new Stopwatch();
		for (Data data : list) {
			if (uf1.connected(data.first, data.second))
				continue;
			uf.union(data.first, data.second);
		}
		double time1 = w1.elapsedTime();
		StdOut.println(uf.count() + " components" + " time : " + time1);
	}
}
