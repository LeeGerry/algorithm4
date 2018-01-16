package me.auburn.edu.alg4.ch5_1;

import java.util.Arrays;

public class MSD {
	private static int R = 256; // 基数
	private static String[] aux; // 数据分类的辅助数组

	private static int charAt(String s, int d) {
		return d < s.length() ? s.charAt(d) : -1;
	}

	public static void sort(String[] a) {
		int N = a.length;
		aux = new String[N];
		sort(a, 0, N - 1, 0);
	}

	private static void sort(String[] a, int lo, int hi, int d) {
		if (hi <= lo)
			return;
		int[] count = new int[R + 2];
		// 计算频率
		for (int i = lo; i <= hi; i++)
			count[charAt(a[i], d) + 2]++;

		// 频率转索引
		for (int r = 0; r < R + 1; r++)
			count[r + 1] += count[r];
		// 数据分类
		for (int i = lo; i <= hi; i++) // Distribute.
			aux[count[charAt(a[i], d) + 1]++] = a[i];
		// 回写
		for (int i = lo; i <= hi; i++)
			a[i] = aux[i - lo];
		// 递归的以每个字符为键进行排序
		for (int r = 0; r < R; r++)
			sort(a, lo + count[r], lo + count[r + 1] - 1, d + 1);

	}

	public static void main(String[] args) {
		String[] inputs = { "she", "sells", "seashells", "by", "the", "sea", "shore", "the", "shells", "she", "shells",
				"are", "surely", "seashells" };
		System.out.println("最初:" + Arrays.toString(inputs));
		sort(inputs);
		System.out.println("最终:" + Arrays.toString(inputs));
	}
}
