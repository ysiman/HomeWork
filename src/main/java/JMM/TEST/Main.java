package JMM.TEST;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by sbt-siman-yn on 29/05/2017.
 */
public class Main {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        List<Future<String>> list = new ArrayList<>();
        for(int i=0;i<10;i++){
            Future<String> future = executor.submit(new TaskCallable(i));
            list.add(future);
        }
        System.out.println("Ожидание результатов");
        for(Future<String> ft : list){
            while (!ft.isDone()){

            }
            try {
                System.out.println(ft.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }





    }
}
