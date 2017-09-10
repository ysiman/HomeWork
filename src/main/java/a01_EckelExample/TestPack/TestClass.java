package a01_EckelExample.TestPack;

class Tst{
    public StaticCl statClass;
    public static class StaticCl{
        int id;
        public StaticCl() {
            System.out.println("statClass init");
        }
        public StaticCl(int n) {
            id = n;
            System.out.println("statClass intit");
        }
        public void getId(){
            System.out.println("statClass id="+id);
        }
    }

    public void initStaticCl(int n){
        statClass = new StaticCl(n);
    }
    public void getStatClassId(){
        statClass.getId();
    }
}
public class TestClass {


    public static void main(String[] args) {
        Tst t = new Tst();
        t.initStaticCl(1);
        t.getStatClassId();
        Tst t2 = new Tst();
       // t2.getStatClassId();
        Tst.StaticCl stCl = new Tst.StaticCl(2);
        stCl.getId();

    }

}
