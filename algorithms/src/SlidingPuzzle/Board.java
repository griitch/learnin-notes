package SlidingPuzzle;

import java.util.Stack;

public class Board {

    private final int[][] board;
    private final int n;
    private final int N;


    public Board(int[][] board){

        if (board == null || board.length != board[0].length )
            throw new IllegalArgumentException();
        this.board = board;
        this.n = board.length;
        this.N = n*n;

    }

    public int dimension() { return n; }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(n);
        sb.append('\n');
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(board[i][j]);
                sb.append(' ');
            }
            sb.append('\n');
        }
        return  sb.toString();
    }

    public Board twin() {
        int[][] twinTiles = copy(this.board);
        // a board that is obtained by exchanging any pair of tiles
        if (twinTiles[0][0] != 0 && twinTiles[0][1] != 0)
            return new Board(swap(0, 0, 0, 1));
        else
            return new Board(swap(1, 0, 1, 1));
    }

    public Iterable<Board> neighbors() {
        Stack<Board> neighbours = new Stack<>();
        int position = blankPosition();
        int i = position / dimension();
        int j = position % dimension();
        if (i > 0)
            neighbours.push(new Board(swap(i, j, i - 1, j)));
        if (i < board.length - 1)
            neighbours.push(new Board(swap(i, j, i + 1, j)));
        if (j > 0)
            neighbours.push(new Board(swap(i, j, i, j - 1)));
        if (j < board.length - 1)
            neighbours.push(new Board(swap(i, j, i, j + 1)));

        return neighbours;
    }

    private int[][] swap(int i1, int j1, int i2, int j2) {
        int[][] copy = copy(board);
        int temp = copy[i1][j1];
        copy[i1][j1] = copy[i2][j2];
        copy[i2][j2] = temp;
        return copy;
    }

    private int[][] copy(int[][] blocks) {
        int[][] copy = new int[blocks.length][blocks.length];
        for (int i = 0; i < blocks.length; i++) {
            System.arraycopy(blocks[i], 0, copy[i], 0, blocks.length);
        }
        return copy;
    }


    private int blankPosition() {
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board.length; j++)
                if (board[i][j] == 0)
                    return j + i * dimension();
        return -1;
    }
    public boolean isGoal(){
        return hamming() == 0;
    }

    public int hamming() {
        int hamming = 0;
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board.length; j++)
                if (board[i][j] != (j + i*n + 1) % N ) hamming++;
        return hamming;
    }

    public int manhattan() {
        int manhattan = 0;
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board.length; j++)
                if (board[i][j] != 0 && board[i][j] != j + i * dimension() + 1)
                    manhattan += manhattanDistance(i, j, board[i][j]);
        return manhattan;
    }

    private int manhattanDistance(int i, int j, int square) {
        square--;
        int horizontal = Math.abs(square % n - j);
        int vertical = Math.abs(square / n - i);
        return horizontal + vertical;
    }

    public boolean equals(Object y) {
        if (y == this)
            return true;

        if( y == null )
            return false;

        if (y.getClass() != this.getClass() ){
            return false;
        }
        Board that = (Board) y;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(board[i][j] != that.board[i][j] )
                    return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] f = new int[4][4];
        Board b = new Board(f);
        for (int i = 0; i < b.n; i++) {
            for (int j = 0; j < b.n; j++) {
                f[i][j] = (i*b.n + j + 1)%( b.n * b.n );
            }
        }
        System.out.println(b);
    }
}
