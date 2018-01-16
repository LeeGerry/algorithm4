package ch1;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Test {
	public static void main(String[] args) {
		List<String> a = new LinkedList<>();
		a.add("a1"); a.add("a2"); a.add("a3");
		
		List<String> b = new LinkedList<>();
		b.add("b1"); b.add("b2"); b.add("b3"); b.add("b4");
		
		ListIterator<String> ait = a.listIterator();
		Iterator<String> bit = b.iterator();
		
		while(bit.hasNext()){
			if(ait.hasNext())	ait.next();
			ait.add(bit.next());
		}
		System.out.println(a);
		
		
	}
}
