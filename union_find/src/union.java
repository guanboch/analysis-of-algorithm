public class union {

public int a ;
public union(){
    a = 10034;
}

public void functiontest (int b){
    System.out.println(b);
}

    public static void main(String []args){

        union unionA = new union();
        unionA.a = 1000;
        union unionB  = new union();
        unionA.functiontest(3);



        System.out.println("Hello, World!"+unionB.a);

    }
}
