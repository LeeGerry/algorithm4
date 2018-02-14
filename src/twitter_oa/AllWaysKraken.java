package twitter_oa;
/**
 * LC unique-paths 可以向右，向下和右下走
 */
public class AllWaysKraken {
	public static int allWays(int m, int n){
		int[][] dp = new int[m][n];
		for(int i=0; i<m; i++){
			dp[i][0] = 1;
		}
		for(int i=0; i<n; i++){
			dp[0][i] = 1;
		}
		for(int i=1; i<m; i++){
			for(int j=1; j<n; j++){
				dp[i][j] = dp[i-1][j] + dp[i][j-1] + dp[i-1][j-1];
			}
		}
		return dp[m-1][n-1];
	}
	public static void main(String[] args) {
		System.out.println(allWays(3, 3));
	}
}
