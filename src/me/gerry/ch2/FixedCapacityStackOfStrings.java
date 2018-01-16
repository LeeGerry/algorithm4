package me.gerry.ch2;

public class FixedCapacityStackOfStrings {
	private String[] s;
	private int n = 0;

	public FixedCapacityStackOfStrings() {
		s = new String[1];
	}

	public boolean isEmpty() {
		return n == 0;
	}

	public void push(String item) {
		if (n == s.length)
			resize(2 * s.length);
		s[n++] = item;
	}

	private void resize(int i) {
		System.out.println(i);
		String[] copy = new String[i];
		for (int m = 0; m < n; m++) {
			copy[m] = s[m];
		}
		s = copy;
	}

	public String pop() {
		String item = s[--n];
		s[n] = null;
		if(n>0 && n==s.length/4) 	resize(s.length / 2);
		return item;
	}

	public static void main(String[] args) {
		FixedCapacityStackOfStrings fs = new FixedCapacityStackOfStrings();
		for (int a = 0; a < 50; a++) {
			fs.push(a + "'s item");
		}
		for (int a = 0; a < 50; a++) {
			System.out.println(fs.pop());
		}
		
	}
}
