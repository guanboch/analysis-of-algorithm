import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class Helloworld {

    public static void main(String[] args) {
        // Prints "Hello, World" to the terminal window.
        System.out.println("Hello, World");

        String a = "123qwe";
        System.out.println(a.getBytes().length);
        ByteBuffer bytetest = ByteBuffer.allocate(10);

        byte type = (byte) 89;
        short type1 = 12;

        System.out.println(type +"\n");
        System.out.println(type1);


        bytetest.put(type);
        bytetest.putShort(type1);

        bytetest.flip();
        byte result;
        short result2;
        result = bytetest.get(0);
        result2 = bytetest.getShort(1);
        byte test3;
        test3 = bytetest.get();
        short test4;
        test4 = bytetest.getShort();

        System.out.println(result);
        System.out.println(result2);
        System.out.println(test3);
        System.out.println(test4);
        int x = 0x7FFF_FFFF;
        System.out.println(x);


//        short rrr= 0xFF;
//        System.out.println(rrr);
//
//        bytetest.position(0);
//        byte x= bytetest.get();
//        System.out.println(x);







    }

}
