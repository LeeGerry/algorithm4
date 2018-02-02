import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
class Student{
	int age;
	String name;
	
}
public class ComTest {
	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<>();
		list.add(3);list.add(1);list.add(0);list.add(9);
		System.out.println(list.toString());
		Collections.sort(list, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});
		
		ArrayList<Student> list1 = new ArrayList<>();
		Collections.sort(list1, new Comparator<Student>() {
			@Override
			public int compare(Student o1, Student o2) {
				// TODO Auto-generated method stub
				return 0;
			}
		});
		System.out.println(list.toString());
	}
}
