public class WeightedUF {
    private int[] id;
    private int[] tSize;

    public WeightedUF(int N) {
        id = new int[N];
        tSize = new int[N];
        for (int i = 0; i < id.length; i++) {
            id[i] = i;
            tSize[i] = 0;
        }
    }

    public int root(int x) {
        while(id[x]!=x) {
            id[x]= id[id[x]];
            x = id[x];
        }
        return x;
    }

    public boolean connected(int p, int q){
        return root(p) == root(q);
    }

    public void union(int p, int q) {
        int rootP = root(p);
        int rootQ = root(q);


        if (rootP != rootQ) {
            if (tSize[rootP] > tSize[rootQ]) {
                id[rootP] = rootQ;
                tSize[rootQ] = tSize[rootP] + tSize[rootQ];
            } else {
                id[rootQ] = rootP;
                tSize[rootP] = tSize[rootP] + tSize[rootQ];
            }
        }
    }


}
