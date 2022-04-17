public class QuickUnionUF {
    private int[] id;

    public QuickUnionUF(int N){
        id = new int[N];
        for (int i = 0; i < id.length; i++){
            id[i] = i;
        }
    }

    public int root(int x){
       while(x != id[x]){
            x = id[x];
        }
        return x;
    }

    public boolean connected(int p, int q){
        return root(p) == root(q);
    }

    public void union (int p, int q){
        id[root(p)]=root(q);
    }




}
