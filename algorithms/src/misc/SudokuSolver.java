package misc;

public class SudokuSolver {
	
	private static void solve(char[][] board) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board[i][j] == '.') {
					for (char k = '1'; k <= '9'; k++) {
						if (isValidMove(k, i, j, board)) {
							board[i][j] = k;
							solve(board);
							board[i][j] = '.';
						}
					}

					return;
					// if you're here, it means that every single number can't work for this cell
					// so there must be an error in a previous step
					// if k reached 10, this return statement won't be reached
				}
			}
		}
		// if you're here, it means the board is solved
		printbo(board);
		return;
	}

	

	public static void main(String[] args) {
		char[][] board = {
				{ '5', '3', '.', '.', '7', '.', '.', '.', '.' },
				{ '6', '.', '.', '1', '9', '5', '.', '.', '.' },
				{ '.', '9', '8', '.', '.', '.', '.', '6', '.' },
				{ '8', '.', '.', '.', '6', '.', '.', '.', '3' },
				{ '4', '.', '.', '8', '.', '3', '.', '.', '1' },
				{ '7', '.', '.', '.', '2', '.', '.', '.', '6' },
				{ '.', '6', '.', '.', '.', '.', '2', '8', '.' },
				{ '.', '.', '.', '4', '1', '9', '.', '.', '5' },
				{ '3', '.', '.', '.', '8', '.', '.', '7', '9' } };
		solve(board);

	}
	
	private static boolean isValidMove(char n, int l, int c, char[][] board) {

		for (int i = 0; i < 9; i++) {
			if (board[l][i] == n)
				return false;
		}

		for (int i = 0; i < 9; i++) {
			if (board[i][c] == n)
				return false;
		}

		int l0 = (l / 3) * 3;
		int c0 = (c / 3) * 3;

		for (int i = 0; i < 3; i++) {

			for (int j = 0; j < 3; j++) {

				if (board[l0 + i][c0 + j] == n) {
					return false;
				}
			}

		}

		return true;

	}
	
	private static void printbo(char[][] b) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(b[i][j]);
				System.out.print(' ');
			}
			System.out.println();
		}

	}

}
