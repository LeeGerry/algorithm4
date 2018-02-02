package leetcode;
/**
 * https://leetcode.com/problems/dungeon-game/description/
 * 思路: 骑士在左上角，公主被困在在右下角，骑士每次向右走一步或者向下走一步，如果血量<=0就死掉了。
 * 从右下角公主的位置逆推，每次向上或者向左一步。这样子每个格子可以是它下方或者右方的格子逆推回来的。
 * 要让骑士的血量最少，则要保证逆推的每一步 都让骑士处于活着的状态 并且 选择血量较小的那一种。
 * 假设dp[i][j]表示格子(i,j)处的血量，dungeon[i][j]表示走到(i,j)处要扣除的血量。
 * 		从下方逆推: 血量 dp[i][j] = dp[i+1][j] - dungeon[i][j], 
 * 					但是如果dungeon[i][j]处要扣除的血量较多，则相减结果会变成负数，这样骑士就死掉了，
 * 					我们要保证扣完血后骑士还活着，此时改点的血量就应该是1.
 * 				所以应该是 dp[i][j] = Math.max(dp[i+1][j] - dungeon[i][j], 1)
 * 		从右边逆推: 同理	dp[i][j] = Math.max(dp[i][j+1] - dungeon[i][j], 1)
 * 最后，在这两个值中选择较小的那个就可以了，也即 dp[i][j] = Math.min(从下逆推得到的值, 从右逆推得到的值);
 * 
 * 另外：1.最下面的一行的每个格子只能从右边逆推；最右面的一列中的每个格子只能从下面逆推。这两个要先独立处理一下。
 * 		2.右下角那个格子没有任何格子可以逆推，所以假设其逆推节点的血量为1，也就是骑士活着的状态的血量最小值。
 */
public class DungeonGame {
	public int calculateMinimumHP(int[][] dungeon){
		if(dungeon == null ||dungeon.length == 0 || dungeon[0].length == 0)	return 0;
		
		int m = dungeon.length;
		int n = dungeon[0].length;
		//DP二维数组表示血量
		int[][] dp = new int[m][n]; // dp[i][j]表示从(i,j)到目的地(m-1, j-1)需要的最小生命值
		
		//初始化右下角的血量格子中的值
		dp[m-1][n-1] = Math.max(1-dungeon[m-1][n-1], 1);
		//逆推最后一列的血量
		for(int i = m-2; i>=0; i--){
			dp[i][n-1] = Math.max(dp[i+1][n-1] - dungeon[i][n-1], 1);
		}
		//逆推最后一行的血量
		for(int i = n-2; i>=0; i--){
			dp[m-1][i] = Math.max(dp[m-1][i+1] - dungeon[m-1][i], 1);
		}
		//对于每个节点，从下方和右方逆推出来
		for(int i = m-2; i>=0; i--){
			for(int j = n-2; j>=0; j--){
				int down = Math.max(dp[i+1][j] - dungeon[i][j], 1);//从下方逆推出来的值
				int right = Math.max(dp[i][j+1] - dungeon[i][j], 1);//从右方逆推出来的值
				dp[i][j] = Math.min(down, right);//选择最小血量放入当前血量格子
			}
		}
		return dp[0][0];//返回骑士起始所在格子对应的血量
	}
	public static void main(String[] args) {
		int[][] test = {
				{-2, -3, 3},
				{-5, -10, 1},
				{10, 30, -5}
		};
		System.out.println(new DungeonGame().calculateMinimumHP(test));
	}
}
