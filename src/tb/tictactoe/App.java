package tb.tictactoe;

import java.util.Scanner;

public class App {
	public static void main(String[] args) {
		Board b = new Board(5);
		b.printBoard();

		Scanner scan = new Scanner(System.in);
		while (!b.isFull()) {
			String line = scan.nextLine();
			if (line.contains("a")) {
				b.aiMove();
			} else {
				String[] strs = line.split(",");
				int row = Integer.parseInt(strs[0]);
				int col = Integer.parseInt(strs[1]);
				boolean boo = b.move(row, col, Board.HUMAN);
				if(!boo){
					System.out.println("illegal");
					continue;
				}
			}
			int win = b.checkWin();
			if (win < 0) {
				if (b.isFull()) {
					System.out.println("draw!");
					break;
				} else {
					continue;
				}
			} else if (win == 0) {
				System.out.println("human win!");
				break;
			} else {
				System.out.println("AI win!");
				break;
			}
		}
	}
}
