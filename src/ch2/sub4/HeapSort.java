package ch2.sub4;

import java.io.File;

import ch1.interf.Sort;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;

public class HeapSort extends Sort{
	@Override
	public void sort(Comparable[] a, int lo, int hi) {
		for(int k=hi/2;k>=1;k--)
			sink(a,k,hi);
		while(hi>1){
			exch(a, 1, hi--);
			sink(a,1,hi);
		}
	}

	private void sink(Comparable[] a, int k, int hi) {
		while(2*k <=hi){
			int j= 2*k;
			if(j<hi&&less(j,j+1)) j++;
			if(!less(k,j))break;
			exch(a,k,j);
			k= j;
		}
	}
	public static void main(String[] args) {
		String filePath = HeapSort.class.getClassLoader().getResource("ch2/sub4/num.txt").getPath();
		In in = new In(new File(filePath));
		int[] ints = in.readAllInts();
		Integer[] a = new Integer[ints.length];
		for(int i=0;i<ints.length;i++){
			a[i] = ints[i];
		}
		new HeapSort().sort(a, 1, a.length);
	}
}
