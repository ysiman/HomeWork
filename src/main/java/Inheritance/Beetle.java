package Inheritance;

// reuse/Beetle.java
// (c)2017 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// The full process of initialization

class Insect {
    private int i = 9;
    protected int j;
    Insect() {
        System.out.println("i = " + i + ", j = " + j);
        j = 39;
    }
    private static int x1 =
            printInit("static Insect.x1 initialized");
    private int x2 =
            printInit("nonStatic Insect.x2 initialized");
    static int printInit(String s) {
        System.out.println(s);
        return 47;
    }
}

public class Beetle extends Insect {
    private int k = printInit("Beetle.k initialized");
    public Beetle() {
        System.out.println("k = " + k);
        System.out.println("j = " + j);
    }
    private static int x2 =
            printInit("static Beetle.x2 initialized");
    public int varInit(int var){
        System.out.println("varInit var = " + var );
        return var;
    }

    public static void main(String[] args) {
        int i = 1;
        System.out.println("Run!");
        i++;
        System.out.println("i="+ i);
        //Insect s = new Insect();
        //Cupboard cb = new Cupboard();
        /*Beetle b = new Beetle();
        System.out.println("Run2");
        int x22 = printInit("MAIN  x22 initialized");
        int x3 = b.varInit(3);*/
    }
}
/* Output:
static Insect.x1 initialized
static Beetle.x2 initialized
Beetle constructor
i = 9, j = 0
Beetle.k initialized
k = 47
j = 39
*/
