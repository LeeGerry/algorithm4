package me.auburn.edu.dp;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 钢条切割
 */
public class CutRob {
//	public static int[] prices = 
//			{1,5,8,9,10,17,17,20,24,30,
//			30,30,30,30,30,30,30,30,30,30,
//			30,30,30,30,30,30,30,30,30,30,
//			30,30,30,30,30,30,30,30,30,30,
//			30,30,30,30,30,30,30,30,30,30,
//			30,30,30};
	public static int[] prices = {1,5,8,9,10,17,17,20,24,30};
	public static int solution(int length){
		if(length == 0)	return 0;
		int result = Integer.MIN_VALUE;
		for(int i = 1; i <= length; i++){
			result = Math.max(result, prices[i-1] + solution(length-i));
		}
		return result;
	}
	
	/** 自顶向下*/
	public static int mem_cut_rod(int n){
		int[] dp = new int[n+1];			// 辅助数组dp
		Arrays.fill(dp, Integer.MIN_VALUE); // 初始化为负无穷
		return mem_cut_rod_aux(n, dp);
	}
	/** 自顶向下法的辅助函数*/
	private static int mem_cut_rod_aux(int n, int[] dp) {
		if(dp[n] >= 0)	return dp[n];	// 如果子问题已经解过，直接返回
		int max = Integer.MIN_VALUE;
		if(n==0)	max = 0;			// 如果长度为0，则最大收益为0
		else{							// 长度若不为0
			for(int i = 1; i<=n; i++){	// 找到最大收益
				max = Math.max(max, prices[i-1] + mem_cut_rod_aux(n-i, dp));
			}
		}
		dp[n] = max;	// 把计算得到的最大收益存入结果
		return max;		// 返回结果
	}
	/** 自底向上法*/
	private static int bottom_up_cut_rod(int n){
		int[] dp = new int[n+1];
		dp[0] = 0;
		for(int j=1; j<=n; j++){
			int max = Integer.MIN_VALUE;
			for(int i=1; i<=j; i++){
				max = Math.max(max, prices[i-1] + dp[j-i]);
			}
			dp[j] = max;
		}
		return dp[n];
	}
	private static int[] path; 
	/** 带切割方案的自底向上扩展方案*/
	public static int extended_bottom_up_cut_rod(int n){
		int[] dp = new int[n+1];
		path = new int[n+1];
		dp[0] = 0;
		for(int j = 1; j<=n; j++){
			int max = Integer.MIN_VALUE;
			for(int i=1; i<=j; i++){
				if(max < (prices[i-1] + dp[j-i])){
					max = prices[i-1] + dp[j-i];
					path[j] = i;
				}
			}
			dp[j] = max;
		}
		return dp[n];
	}
	/** 得到切割方案(一个最优解)*/
	public static ArrayList<Integer> getACutSolution(int n){
		ArrayList<Integer> result = new ArrayList<>();
		while(n > 0){
			result.add(path[n]);
			n -= path[n];
		}
		return result;
	}
	public static void main(String[] args) {
//		long curr = System.currentTimeMillis();
		System.out.println("长度为7的最大收益为："+extended_bottom_up_cut_rod(7));
		System.out.println(getACutSolution(7));
//		System.out.println(System.currentTimeMillis() - curr);
//		for(int i=1; i<=prices.length; i++)
//			System.out.println("长度为"+i+"的最大收益为："+bottom_up_cut_rod(i));
	}
}
