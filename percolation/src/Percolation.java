import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;



public class Percolation {

    private int openSiteCnt = 0;
    private int[][] sites;
    private int N;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF uf2;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n =  " + n + " is smaller than 0 ");
        }
        uf = new WeightedQuickUnionUF(n*n+1);
        uf2= new WeightedQuickUnionUF(n*n+2);
        sites = new int[n+1][n+1];
        for (int i = 1 ; i <= n; i++){
            for (int j = 1; j <= n; j++){
                sites[i][j] = 0;  // initialize to be blocked
            }
        }
        openSiteCnt = 0;
        N = n;


    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 1 || row > N || col < 1 || col >N ){
            throw new IllegalArgumentException("row or col index out of range");
        }
        if(sites[row][col]==0){
            sites[row][col] = 1;
            openSiteCnt = openSiteCnt + 1;
            if(row==1){
                uf.union(N*N, (row - 1) * N + col - 1);
                uf2.union(N*N, (row - 1) * N + col - 1);
            }
            if (row == N){
                uf2.union(N*N+1, (row - 1) * N + col - 1);
            }
            if (row > 1 ) {
                if (isOpen(row - 1, col)) {
                    uf.union((row - 2) * N + col - 1, (row - 1) * N + col - 1);
                    uf2.union((row - 2) * N + col - 1, (row - 1) * N + col - 1);
                }
            }
            if (col > 1){
                if(isOpen(row,col-1)){
                    uf.union((row-1)*N+col-2,(row-1)*N+col-1);
                    uf2.union((row-1)*N+col-2,(row-1)*N+col-1);
                }
            }
            if (row <N){
                if (isOpen(row +1, col)) {
                    uf.union(row  * N + col - 1, (row - 1) * N + col - 1);
                    uf2.union(row  * N + col - 1, (row - 1) * N + col - 1);
                }
            }
            if (col <N){
                if (isOpen(row , col+1)) {
                    uf.union((row-1)  * N + col , (row - 1) * N + col - 1);
                    uf2.union((row-1)  * N + col , (row - 1) * N + col - 1);
                }
            }

        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        checkException(row, col);
        return sites[row][col] == 1 ;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        checkException(row, col);
        boolean result = false;
        return uf.find((row - 1) * N + col - 1) == uf.find(N * N);

    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSiteCnt;
    }


    private void checkException(int row, int col){
        if(row<1 || row>N || col<1 || col>N){
            throw new IllegalArgumentException("row or col index out of range");
        }
    }
    // does the system percolate?
    public boolean percolates() {
//            boolean percolateFound = false;
//            // check if there is a pro
//            for (int col_tmp = 1; col_tmp <=N ; col_tmp++){
//                if(isFull(N, col_tmp)){
//                    percolateFound = true;
//                    break;
//                }
//            }
//        return percolateFound;
        return uf2.find(N*N)==uf2.find(N*N+1);

    }

    // test client (optional)
    public static void main(String[] args) {
        int size = 20;
        Percolation percolate = new Percolation(size);


    }



}
