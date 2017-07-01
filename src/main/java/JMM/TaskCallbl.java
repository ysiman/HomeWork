package JMM;

import java.util.concurrent.Callable;

/**
 * Created by sbt-siman-yn on 29/05/2017.
 */
public class TaskCallbl<T> {
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
}
