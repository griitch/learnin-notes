package misc;
public class NQueens {
	
	private int count;
	
	public int totalNQueens(int n) {
		
		boolean[][] b = new boolean[n][n];
		helper(b, 0);
		
		return count;
	}
	
	private void printbo(boolean[][] b) {
		
		
		for(int i = 0 ; i < b.length ; i++ ) {
			for(int j = 0 ; j < b[0].length ; j++ ) {
				System.out.print( b[i][j] ? "1 " :"0 " );
			}
			System.out.println();
		}
		System.out.println();
	}
	
	private boolean isNotUnderAttack(boolean[][] b, int i,int j) {
		
		for(int row = 0; row < b.length ; row++) {
			if(b[row][j]) 
				return false;
			}
			 
			for(int col = j,row = i ; row >= 0 && col >= 0 ; row--,col--) {
				if(b[row][col] )
					return false;
			}

			for(int col = j,row = i ; row >= 0 && col < b.length ; row--,col++) {
				if(b[row][col] )
					return false;
			}
		return true;
	}
	
	public void helper(boolean[][] board, int n) {
		// n current line to put queen in
			if( n == board.length ) {
				printbo(board);
				count++;
				return;
			}
			
			for(int i = 0 ;  i < board.length ; i++ ) {
				if( isNotUnderAttack(board, n, i)  ) {
					board[n][i] = true;
					helper(board, n+1);
					board[n][i] = false;
				}
			}
	}
	
	
	public static void main(String[] args) {
		NQueens fo = new NQueens();
		System.out.println(fo.totalNQueens(6));
		
	}

}
