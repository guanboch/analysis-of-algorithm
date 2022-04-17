import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;




public class PercolationStats {
    private final double[] fractions;
    private final double CONFIDENCE_95 = 1.96;
    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0) {
            throw new IllegalArgumentException("n <= 0.");
        }
        if (trials <= 0) {
            throw new IllegalArgumentException("trials <= 0.");
        }
        //Percolation percol = new Percolation(n);
        fractions = new double[trials];
        for (int trial = 0; trial < trials; trial++) {
            boolean percolateFound = false;
            Percolation percol = new Percolation(n);

            while (!percolateFound) {
                int row_id = StdRandom.uniform(1, n+1);
                int col_id = StdRandom.uniform(1, n+1);

                while (percol.isOpen(row_id, col_id)) {
                    row_id = StdRandom.uniform(1, n+1);
                    col_id = StdRandom.uniform(1, n+1);
                }
                percol.open(row_id, col_id);

                percolateFound = percol.percolates();
            }
            fractions[trial]= percol.numberOfOpenSites()*1.0/(n*n);
        }
    }

    // sample mean of percolation threshold
    public double mean(){
        return StdStats.mean(fractions);
    }

    // sample standard deviation of percolation threshold
    public double stddev(){
        return StdStats.stddev(fractions);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo(){
        return mean() - CONFIDENCE_95 * stddev() / Math.sqrt(fractions.length);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi(){
        return mean() + CONFIDENCE_95 * stddev() / Math.sqrt(fractions.length);
    }

    // test client (see below)
    public static void main(String[] args){
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(n, trials);

        StdOut.println("mean  = " + stats.mean());
        StdOut.println("stddev  = " + stats.stddev());
        StdOut.println("95% confidence interval = ["
                + stats.confidenceLo() + ", "
                + stats.confidenceHi() +"]");
    }




}
