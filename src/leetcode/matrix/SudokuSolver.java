package leetcode.matrix;

public class SudokuSolver {
	public void solveSudoku(char[][] board){
		if(board == null || board.length == 0)	return;
		solve(board);
	}

	private boolean solve(char[][] board) {
		for(int i = 0; i<board.length; i++){
			for(int j = 0; j<board[0].length;j++){
				if(board[i][j] == '.'){		// 若当前没有填充，需要进行填充
					for(char c = '1'; c<='9';c++){  // 从1-9开始试着往该位置填充
						if(isValid(board, i, j, c)){// 判断填充是否有效
							board[i][j] = c;  // 若有效，则填充
							if(solve(board)) return true;// 继续DFS，如果成功，返回true
							else board[i][j] = '.';		 // 如果不成功，恢复原状，返回上一层，试别的数
						}
					}
					return false;	// 1-9都不成功，返回FALSE
				}
			}
		}
		return true;
	}

	/**
	 * 每行，每列，每个小九宫格
	 * @param board
	 * @param row
	 * @param col
	 * @param c
	 * @return
	 */
	private boolean isValid(char[][] board, int row, int col, char c) {
		for(int i = 0; i<9; i++){
			if(board[i][col] != '.' && board[i][col] == c)	return false;
			if(board[row][i] != '.' && board[row][i] == c)	return false;
			int rowIndex = 3 * (row/3) + i/3;
			int colIndex = 3 * (col/3) + i%3;
			if(board[rowIndex][colIndex] != '.' && board[rowIndex][colIndex] == c)	return false;
		}
		return true;
	}
}
