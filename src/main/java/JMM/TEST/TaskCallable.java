package JMM.TEST;

import java.util.StringJoiner;
import java.util.concurrent.Callable;

/**
 * Created by sbt-siman-yn on 29/05/2017.
 */
public class TaskCallable implements Callable<String> {
    int num;

    public TaskCallable(int num) {
        this.num = num;
    }

    @Override
    public String call() throws Exception {

        //System.out.println(Thread.currentThread().getName()+" start process task");
        processCommand();
        //System.out.println(Thread.currentThread().getName()+" end task");
        return "Task "+num+" complete.";

    }
    private void processCommand() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println("Sleep interrapt!! " + Thread.currentThread().getName());
            e.printStackTrace();
        }
    }
}
