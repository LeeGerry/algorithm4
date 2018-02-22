public class ComTest {
	
	public static int search(Object[] a, Object t){
		return -1;
	}
	public static int search2(Comparable[] a, Comparable t){
		a[0].compareTo(t);
		return -1;
	}
	public static void main(String[] args) {
		Object[] a = {"abc",'c',33.3};
		Comparable[] b = {"abc",'c',33.3,new Integer(3)};
		
		//这个编译通过了，因为参数都很对。但是方法体中指明了要用compareTo方法，
		//这时候可能有不一样的对象，大家都有compareto方法，这时候比较的时候不同对象在运行的时候不知道如何比较，就产生了如下错误
		//比如这个33.33是浮点型，就不知道如何跟"abc"字符串型比较
		search2(b, 33.33);
	}
}
