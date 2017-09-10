package AccessTest;

/**
 * Created by HOME on 04.08.2017.
 */
public class Main {
    public static void main(String[] args) {
        Class1 class1 = new Class1();
        Class2 class2 = new Class2();
        class1.x = 1;
        class1.y = 2;
        class2.z = 3;
        System.out.println("class1.x=" + class1.x + "  class1.y="+class1.y+"  class2.z="+class2.z);
    }
}
