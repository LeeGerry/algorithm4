package tb.tictactoe;

public class Board {
	public final static int HUMAN = 0, AI = 1;
	private int[][] board;
	private int[] rowH, colH, rowAI, colAI;
	private int diagH, antiDiagH, diagAI, antiDiagAI;

	public Board() {
		init(3);
	}

	public Board(int n) {
		init(n);
	}

	/**
	 * Initialize the board
	 * @param n
	 */
	private void init(int n) {
		board = new int[n][n];
		rowH = new int[n];
		colH = new int[n];
		rowAI = new int[n];
		colAI = new int[n];
		diagH = antiDiagH = diagAI = antiDiagAI = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				board[i][j] = '-';
			}
		}
	}

	/**
	 * print the board
	 */
	public void printBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				System.out.print((char) board[i][j]);
				if (j != board[0].length - 1)
					System.out.print('|');
			}
			System.out.println();
		}
		System.out.println();
	}

	/**
	 * move to a position
	 * @param row
	 * @param col
	 * @param player: who moves
	 * @return
	 */
	public boolean move(int row, int col, int player) {
		if (board[row][col] != '-')
			return false;
		if (player == HUMAN) {
			board[row][col] = 'X';
			rowH[row]++;
			colH[col]++;
			if (row == col)
				diagH++;
			if (row + col == board.length - 1)
				antiDiagH++;
		} else {
			board[row][col] = 'O';
			rowAI[row]++;
			colAI[col]++;
			if (row == col)
				diagAI++;
			if (row + col == board.length - 1)
				antiDiagAI++;
		}
		System.out.print(player == 0 ? "HUMAN" : "AI");
		System.out.println(" move [" + row + "," + col + "]");
		printBoard();
		return true;
	}

	/**
	 * board is full
	 * @return
	 */
	public boolean isFull() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == '-')
					return false;
			}
		}
		return true;
	}

	/**
	 * let ai make a decision
	 */
	public void aiMove() {
		if (diagAI == board.length - 1) {
			for (int i = 0; i < board.length; i++) {
				if (board[i][i] == '-') {
					move(i, i, 1);
					return;
				}
			}
		}
		if (antiDiagAI == board.length - 1) {
			for (int i = board.length - 1; i >= 0; i--) {
				if (board[i][i] == '-') {
					move(i, i, 1);
					return;
				}
			}
		}

		for (int i = 0; i < rowAI.length; i++) {
			if (rowAI[i] == board.length - 1) {
				for (int j = 0; j < board.length; j++) {
					if (board[i][j] == '-') {
						move(i, j, 1);
						return;
					}
				}
			}
		}
		for (int i = 0; i < colAI.length; i++) {
			if (colAI[i] == board.length - 1) {
				for (int j = 0; j < board.length; j++) {
					if (board[j][i] == '-') {
						move(j, i, 1);
						return;
					}
				}
			}
		}

		if (diagH == board.length - 1) {
			for (int i = 0; i < board.length; i++) {
				if (board[i][i] == '-') {
					move(i, i, 1);
					return;
				}
			}
		}
		if (antiDiagH == board.length - 1) {
			for (int i = board.length - 1; i >= 0; i--) {
				if (board[board.length-1-i][i] == '-') {
					move(board.length-1-i, i, 1);
					return;
				}
			}
		}

		for (int i = 0; i < rowH.length; i++) {
			if (rowH[i] == board.length - 1) {
				for (int j = 0; j < board.length; j++) {
					if (board[i][j] == '-') {
						move(i, j, 1);
						return;
					}
				}
			}
		}
		for (int i = 0; i < colH.length; i++) {
			if (colH[i] == board.length - 1) {
				for (int j = 0; j < board.length; j++) {
					if (board[j][i] == '-') {
						move(j, i, 1);
						return;
					}
				}
			}
		}
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == '-') {
					move(i, j, 1);
					return;
				}
			}
		}
	}

	/**
	 * check the status of the board
	 * @return 0 if human win; 1 if AI win; -1 if no one win
	 */
	public int checkWin() {
		if (diagH == board.length || antiDiagH == board.length)
			return 0;
		if (diagAI == board.length || antiDiagAI == board.length)
			return 1;
		for (int i = 0; i < rowH.length; i++) {
			if (rowH[i] == board.length)
				return 0;
		}
		for (int i = 0; i < colH.length; i++) {
			if (colH[i] == board.length)
				return 0;
		}
		for (int i = 0; i < rowAI.length; i++) {
			if (rowAI[i] == board.length)
				return 1;
		}
		for (int i = 0; i < colAI.length; i++) {
			if (colAI[i] == board.length)
				return 1;
		}
		return -1;
	}
}
