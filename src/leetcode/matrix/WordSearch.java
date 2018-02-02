package leetcode.matrix;

/**
 * LeetCode 79
 * https://leetcode.com/problems/word-search/description/
 */
public class WordSearch {
	public boolean exist(char[][] board, String word) {
		for(int i = 0; i<board.length; i++){
			for(int j = 0; j<board[0].length; j++){
				if(exist(board, i, j, word, 0))	return true;
			}
		}
		return false;
	}

	private boolean exist(char[][] board, int i, int j, String word, int start) {
		if(start >= word.length())	return true; // 如果最后一个字符已经匹配，则找到，返回true
		if(i<0 || i>=board.length || j<0 || j>=board[0].length)	return false; // 边界非法返回FALSE
		if(board[i][j] == word.charAt(start++)){ // 找到匹配，start索引加一
			char c = board[i][j];	// 保存当前字符
			board[i][j] = '#';		// 更改当前字符，防止深搜倒回去
			// 上下左右四个方向深搜
			boolean result = exist(board, i+1, j, word, start)
							|| exist(board, i-1, j, word, start)
							|| exist(board, i, j+1, word, start)
							|| exist(board, i, j-1, word, start);
			board[i][j] = c; // 还原字符
			return result;
		}
		return false;
	}
}
