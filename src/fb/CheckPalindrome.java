package fb;

public class CheckPalindrome {
	public boolean isPalindrome(String s) {
		if (s.isEmpty())
			return true;
		int left = 0, right = s.length() - 1;
		char c1, c2;
		while (left <= right) {
			c1 = s.charAt(left);
			c2 = s.charAt(right);
			if (!Character.isLetterOrDigit(c1))
				left++;
			else if (!Character.isLetterOrDigit(c2))
				right--;
			else {
				if (Character.toLowerCase(c1) != Character.toLowerCase(c2))
					return false;
				left++;
				right--;
			}
		}
		return true;
	}

	/**
	 * param search 查找的字符串abc param 
	 * buf 被查找的字符串 deabcdef
	 */
	public static int search(String search, String buf) {

		int[] next = new int[search.length()];
		kmpNext(search, next);
		int i = 0;
		int j = 0;
		while (i < search.length() && j < buf.length()) {

			if (i == -1 || search.charAt(i) == buf.charAt(j)) {
				i++;
				j++;

			} else {
				i = next[i];
			}
		}
		if (i == search.length()) {
			return j - i;
		} else {
			return -1;
		}

	}

	private static void kmpNext(String buf, int next[]) {
		int m = 0;
		int n = -1;
		next[0] = -1;
		while (m < buf.length() - 1) {

			if (n == -1 || buf.charAt(m) == buf.charAt(n)) {
				m++;
				n++;
				next[m] = n;

			} else {

				n = next[n];// 如果不匹配，从不断缩小前缀范围进行匹配
			}
		}
	}
	public static void main(String[] args) {
		System.out.println(search("abc", "bdeabcdef"));
	}
}
