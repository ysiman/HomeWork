package AccessTest;

/**
 * Created by HOME on 04.08.2017.
 */
public class Class1 {
    public Class1() {
        System.out.println("class_1");
    }

    public Class1(int x, int xx, int y) {
        this.x = x;
        this.xx = xx;
        this.y = y;
    }
    private final void testFinal()
    {
        System.out.println("testFinal");
    }

    public int x;
    public int xx;
    protected int y;
    protected void setY(){
        y = 2;
    }

}
