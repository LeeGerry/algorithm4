package fb;

public class TicTacToe {
	int[][] board;
	int num;

	/** Initialize your data structure here. */
	public TicTacToe(int n) {
		num = n;
		board = new int[n][n];
	}

	/**
	 * Player {player} makes a move at ({row}, {col}).
	 * 
	 * @param row
	 *            The row of the board.
	 * @param col
	 *            The column of the board.
	 * @param player
	 *            The player, can be either 1 or 2.
	 * @return The current winning condition, can be either: 0: No one wins. 1:
	 *         Player 1 wins. 2: Player 2 wins.
	 */
	public int move(int row, int col, int player) {
		if (player == 1)
			board[row][col] = 'x';
		else
			board[row][col] = 'o';
		if (isWin(player, row, col))
			return player;
		else
			return 0;
	}

	public boolean isWin(int player, int row, int col) {
		boolean boo = false;
		int count = 0;
		char c;

		if (player == 1)
			c = 'x';
		else
			c = 'o';
		for (int i = 0; i < num; i++) {
			if (board[row][i] == c)
				count++;
		}
		if (count == num)
			return true;
		count = 0;
		for (int i = 0; i < num; i++) {
			if (board[i][col] == c)
				count++;
		}
		if (count == num)
			return true;
		count = 0;
		if (row == col) {
			for (int i = 0; i < num; i++) {
				if (board[i][i] == c)
					count++;
			}
			if (count == num)
				return true;
		}
		count = 0;
		if(row+col == num-1){
			for(int i=0;i<num;i++){
				if(board[i][num-i-1] == c)
					count++;
			}
			if(count == num)
				return true;
		}
		return boo;
	}
	public static void main(String[] args) {
		TicTacToe t = new TicTacToe(2);
		System.out.println(t.move(0, 1, 1));
		System.out.println(t.move(1, 1, 2));
		System.out.println(t.move(1, 0, 1));
	}
}
