package unionFind;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
	
public class PercolationStats  {
	
	
	private double[] samples;
	private int trials;
	private final double CONFIDENCE_95 = 1.96;
	
	public PercolationStats(int n, int trials)   {
		
		if (n <= 0 || trials <= 0)
			throw new IllegalArgumentException();
		
		Percolation perc;
		
		this.trials = trials;
		
		samples = new double[trials];
		
		for (int i = 0 ; i < trials ; i++)
		{
			perc = new Percolation(n);
			int c,r;
			
			double result;
			while(! perc.percolates() )
				{
					c = StdRandom.uniform(1,n+1);
					r = StdRandom.uniform(1,n+1);			
					perc.open(r,c);					
				}
			result =  (double) perc.numberOfOpenSites() / (double)(n*n);
			samples[i] = result;
		}
	}
	

	
	public double mean() {
		
		return StdStats.mean(samples);
	}
	
	public double stddev() {
		return StdStats.stddev(samples);
	}
	
	
	public double confidenceLo() {
		return this.mean() - CONFIDENCE_95*Math.sqrt(this.stddev()/(double)trials );
	}
	
	public double confidenceHi() {
		return this.mean() + CONFIDENCE_95*Math.sqrt(this.stddev()/(double)trials );
	}
	
	
	public static void main(String[] args) {
		int a = Integer.parseInt( args[0] );
		int b = Integer.parseInt(args[1]);
		
		PercolationStats p = new PercolationStats(a, b);
		
		System.out.println("mean = " + p.mean());
		System.out.println("stddev = "+ p.stddev());
		System.out.println("95% confidence interval = [" + p.confidenceLo() + ", " + p.confidenceHi() + "]");
		
	}

}
