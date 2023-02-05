package SlidingPuzzle;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;


public class Solver {
    private Stack<Board> solutionBoards = null;
    private boolean isSolvable;

    public Solver(Board initial){

        if (initial == null) throw new NullPointerException();
        isSolvable = false;
        solutionBoards = new Stack<>();
        MinPQ<SearchNode> searchNodes = new MinPQ<>();

        searchNodes.insert(new SearchNode(initial, 0,null ));
        searchNodes.insert(new SearchNode(initial.twin(), 0,null ));

        while ( !searchNodes.isEmpty() ) {

            SearchNode current = searchNodes.delMin();

            if(current.board.isGoal()) {
               while (current.previous != null) {
                   solutionBoards.push(current.board);
                   current = current.previous;
               }
            solutionBoards.push(current.board);

            if (current.board.equals(initial)) isSolvable = true;

            return;
            }

            for( Board e : current.board.neighbors()) {
                if(current.previous != null )  {
                    if(current.previous.board.equals(e))
                        continue;
                }

                searchNodes.insert(new SearchNode(e,current.moves+1,current));
            }

        }

    }
    public boolean isSolvable() {
        return isSolvable;
    }

    public int moves() {
        if(!isSolvable){
            return -1;
        }
        return solutionBoards.size() - 1;
    }


    public static void main(String[] args) {
        // create initial board from file
        In in = new In("8puzzle/puzzle3x3-unsolvable.txt");
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }

    public Iterable<Board> solution() {
        return solutionBoards;
    }

    private class SearchNode implements Comparable<SearchNode> {
        private Board board;
        private int moves; // number of moves to reach this board
        private SearchNode previous;
        private int manhattan;

        public SearchNode(Board board, int moves, SearchNode previous) {
            this.board = board;
            this.moves = moves;
            this.previous = previous;
            this.manhattan = board.manhattan();
        }

        // negative : this < that
        public int compareTo(SearchNode that) {
            int priorityDiff = ( this.manhattan + moves ) - ( that.manhattan + that.moves );
            return priorityDiff == 0 ? this.manhattan - that.manhattan : priorityDiff ;
        }
    }
}

