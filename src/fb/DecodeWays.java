package fb;

import java.util.Arrays;

/**
 * lc 91
 * @author liguorui
 *
 */
public class DecodeWays {
	public int numDecodings(String s) {
		int n = s.length();
		if (n == 0)
			return 0;
		int[] memo = new int[n + 1];
		memo[n] = 1;
		if (s.charAt(n - 1) == 0)
			memo[n - 1] = 0;
		else
			memo[n - 1] = 1;

		for (int i = n - 2; i >= 0; i--) {
			if (s.charAt(i) == '0')
				continue;
			else {
				String str = s.substring(i, i + 2);
				int cur = Integer.parseInt(str);
				if (cur <= 26)
					memo[i] = memo[i + 1] + memo[i + 2];
				else
					memo[i] = memo[i + 1];
			}
			System.out.println(Arrays.toString(memo));
		}
		return memo[0];
	}

	public static void main(String[] args) {
		System.out.println(new DecodeWays().numDecodings("1220"));
	}
}
