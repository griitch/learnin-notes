package unionFind;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation{
	
	private boolean[][] arr;
	private WeightedQuickUnionUF uf;
	private int openSites;
	private int topPoint, botPoint;
	private int dimension;
	
	public Percolation(int n)
	{
		topPoint = n*n;
		botPoint = n*n+1;
		dimension = n;
		openSites = 0;
		uf = new WeightedQuickUnionUF(n*n + 2); //    0..n^2 - 1 : grid elemnts,  n^2,n^2 + 1 top and bottom points
		//    the +2 is for the points to which all sites of the top and bot rows respectively are connected
		//    they are indexed by n*n and n*n +1 in the uf; 
		arr = new boolean[n][n];
		//    false = blocked, true = open, full = open && connects
		//    arr[i][j] = uf_array[ (i-1)*n + j - 1 ]
	}
	
	public void open(int row, int col) 
	{
		if (row <= 0 || col <= 0 || row > dimension || col > dimension )
			throw new IllegalArgumentException();
		
		if(isOpen(row,col))
			return;
		
		arr[row-1][col-1] = true;
		
		if (row == 1)
			union(row, col, 1, topPoint + 1);
		
		if (row == dimension)
			union(row, col, 1, botPoint + 1);						

		if (col < dimension )
		{ //   check right side
			if(isOpen(row, col+1))
				union(row, col, row, col+1);
		}
		
		if (col > 1 )
		{ //   check right side
			if(isOpen(row, col-1))
				union(row, col, row, col-1);
		}
		
		if (row < dimension )
		{ //   check right side
			if(isOpen(row+1, col))
				union(row, col, row+1, col);
		}
		
		if (row > 1 )
		{ //   check right side
			if(isOpen(row-1, col))
				union(row, col, row-1, col);
		}
		
		openSites++;
	}
	
	public boolean isOpen(int row, int col)  {  
		
		if(row <= 0 || col <= 0 || row > dimension || col > dimension )
			throw new IllegalArgumentException();
		
		return arr[row-1][col-1];
	}
	
	public boolean isFull(int row, int col) { 
		if(row <= 0 || col <= 0 || row > dimension || col > dimension )
			throw new IllegalArgumentException();
		
		if(!arr[row-1][col-1])
			return false;
		else
			return uf.find(topPoint) == uf.find( (row-1)*dimension + col -1);
	}
	
	
	public int numberOfOpenSites() { return openSites; }
	
	public boolean percolates() { return uf.find(topPoint) == uf.find(botPoint); }
	
	private void union(int a,int b, int c, int d) //    for unions between 2 items inside the grid (a,b) and (c,d)
	{
		uf.union((a-1)*dimension + b -1, (c-1)*dimension + d -1 );
	}
	
	
	
	

}