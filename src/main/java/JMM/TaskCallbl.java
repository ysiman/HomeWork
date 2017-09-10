package JMM;

import hw1112Thread.SingleThread;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * Created by sbt-siman-yn on 29/05/2017.
 */
public class TaskCallbl<T>  implements Runnable {
    Callable<? extends T> callable;
    private T result;


    public TaskCallbl(Callable<? extends T> callable) {
        this.callable = callable;
    }


    public T get(){
        if (result==null){
            try {
                result = callable.call();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }


    private String task;
    SingleThread singleThread;

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" start process task: "+task);
        processCommand();
        System.out.println(Thread.currentThread().getName()+" end task:"+task);
    }

    private void processCommand() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("Sleep interrapt!! " + Thread.currentThread().getName());
            e.printStackTrace();
        }
    }

    @Override
    public String toString(){
        return this.task;
    }


}
