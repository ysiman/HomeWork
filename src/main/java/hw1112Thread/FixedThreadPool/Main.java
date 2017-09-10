package hw1112Thread.FixedThreadPool;

import hw1112Thread.TaskForThread;

/**
 * Created by HOME on 21.05.2017.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        ThreadPool threadPool = new ThreadPool(5);
        for (int i = 0; i < 10; i++) {
            Runnable taskForThread = new TaskForThread("Task" + i);
            threadPool.execute(taskForThread);
        }
        threadPool.shutdown();
        System.out.println("Finished all threads. Main thread end");
    }
}
