package SHILDT.Chapt15;

interface MyFunc {
    int func(int n);
}

public class  VarCapture {
    public static void main(String args[])
    {
        // A local variable that can be captured.
        int num = 10;
        int rez;
        MyFunc myLambda = (n) ->  {
            // This use of num is OK. It does not modify num.
            int v = num + n;

            // However, the following is illegal because it attempts
            // to modify the value of num.
//    num++;

            return v;
        };
        rez = myLambda.func(11);
        System.out.println("rez = " + rez);
        rez = 20;
        // The following line would also cause an error, because
        // it would remove the effectively final status from num.
    //  num = 9;
    }
}
