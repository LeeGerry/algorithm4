package fb;
/**
 * 计算一个字符串和他的reverse有多少个字符不同
 */
public class StringDifCount {
	public static int count(String str){
		int left = 0, right = str.length() - 1;
		int result = 0;
		while(left < right){
			if(str.charAt(left) != str.charAt(right))	result++;
			left++;
			right--;
		}
		return 2 * result;
	}
	public static void main(String[] args) {
		String str = "abcbbcba";
		System.out.println(count(str));
	}
}
