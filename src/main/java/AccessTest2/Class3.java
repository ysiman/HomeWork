package AccessTest2;

import AccessTest.Class1;

/**
 * Created by HOME on 04.08.2017.
 */
public class Class3 extends Class1 {
    final int zz;
    private int z;
    public Class3(){
        System.out.println("Class3");
        z = 1;
        zz = 1;
    }
    public void sy(){
        setY();
        y = 2;

    }
    private void testFinal()
    {
        System.out.println("testFinal Class3");
    }
    public static void main(String[] args) {
        //Class1 cl1 = new Class1();
        Class3 cl3 = new Class3();
        System.out.println("cl3.x = " + cl3.x + " cl3.xx="+ cl3.xx + " cl3.y="+cl3.y+ " cl3.z =" + cl3.z);

    }
}
