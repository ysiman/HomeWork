package a01_EckelExample;

abstract class A1{
     void pintText(String s){
        System.out.println("s = [" + s + "]");
    }

}
class EextendA extends A1{

}
public class AbstractTest {
    public static void main(String[] args) {
        EextendA ea = new EextendA();
        ea.pintText("ea class");
       // System.out.println("args = [" + args + "]");
    }
}
