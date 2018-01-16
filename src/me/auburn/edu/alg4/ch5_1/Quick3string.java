package me.auburn.edu.alg4.ch5_1;

import java.util.Arrays;

public class Quick3string {
	private static int charAt(String s, int d){
		return d < s.length() ? s.charAt(d) : -1;
	}
	public static void sort(String[] a){
		sort(a, 0, a.length - 1, 0);
	}
	private static void sort(String [] a, int low, int high, int d){
		if(high <= low)	return ;
		int lt = low, gt = high;
		int v = charAt(a[low], d);
		int i = low + 1;
		while(i <= gt){
			int t = charAt(a[i], d);
			if(t < v) 
				exch(a, lt++, i++);
			else if(t > v) 
				exch(a, i, gt--);
			else	
				i++;
		}
		sort(a, low, lt-1, d);
		if(v >= 0) 
			sort(a, lt, gt, d+1);
		sort(a, gt+1, high, d);
	}
	private static void exch(String[] a, int i, int j) {
		String temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	public static void main(String[] args) {
		String[] inputs = { "she", "sells", "seashells", "by", "the", "sea", "shore", "the", "shells", "she", "shells",
				"are", "surely", "seashells" };
		System.out.println("最初:" + Arrays.toString(inputs));
		sort(inputs);
		System.out.println("最终:" + Arrays.toString(inputs));
	}
}
