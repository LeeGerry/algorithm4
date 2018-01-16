package cc150.linkedlist;

import java.util.LinkedList;
import java.util.Random;

public class AddIntegerList2 {
	public static LinkedList<Integer> addIntList(LinkedList<Integer> list1, LinkedList<Integer> list2) {
		int len1 = list1.size();
		int len2 = list2.size();
		int offSet = Math.abs(len1 - len2);
		if (len1 < len2)
			pad(list1, offSet);
		else
			pad(list2, offSet);
		System.out.println(list1);
		System.out.println(list2);
		LinkedList<Integer> res = new LinkedList<>();
		int carry = 0;
		for (int i = list1.size() - 1; i >= 0; i--) {
			int plus = carry + list1.get(i) + list2.get(i);
			if (plus > 9) {
				res.addFirst(plus % 10);
				carry = 1;
			} else {
				res.addFirst(plus);
				carry = 0;
			}
		}
		if (carry > 0)
			res.addFirst(carry);
		return res;
	}

	private static void pad(LinkedList<Integer> list, int offSet) {
		for (int i = 0; i < offSet; i++) {
			list.offerFirst(0);
		}
	}

	public static void main(String[] args) {
		Random r = new Random();
		LinkedList<Integer> l1 = new LinkedList<>();
		LinkedList<Integer> l2 = new LinkedList<>();
		for (int i = 0; i < 10; i++) {
			l1.add(r.nextInt(10));
			l2.add(r.nextInt(10));
		}
		l2.removeFirst();
		l2.removeLast();
		System.out.println(l1);
		System.out.println(l2);
		System.out.println(addIntList(l1, l2));
	}
}
