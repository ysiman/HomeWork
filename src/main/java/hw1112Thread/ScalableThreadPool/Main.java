package hw1112Thread.ScalableThreadPool;

import hw1112Thread.FixedThreadPool.ThreadPool;
import hw1112Thread.TaskForThread;

public class Main {
    public static void main(String[] args) throws Exception {
        ScaleThreadPool threadPool = new ScaleThreadPool(5,7);
        for (int i = 0; i < 30; i++) {
            Runnable taskForThread = new TaskForThread("Task" + i);
            threadPool.execute(taskForThread);
        }
        threadPool.manageThreads();
        threadPool.shutdown();
        System.out.println("Finished all threads. Main thread end");
    }
}