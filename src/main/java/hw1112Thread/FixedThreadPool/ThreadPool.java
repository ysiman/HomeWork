package hw1112Thread.FixedThreadPool;

import hw1112Thread.TaskQueue;
import hw1112Thread.SingleThread;

import java.util.LinkedList;
import java.util.List;


/**
 * Created by HOME on 21.05.2017.
 */
public class ThreadPool {

    private TaskQueue taskQueue = null;
    private List<SingleThread> threads = new LinkedList<>();
    private boolean isStopped = false;

    public boolean isTerminated(){
        return isStopped;
    }

    public ThreadPool(int noOfThreads){
        taskQueue = new TaskQueue();

        for(int i=0; i<noOfThreads; i++){
            threads.add(new SingleThread(taskQueue));
        }
        for(SingleThread thread : threads){
            thread.start();

        }
    }

    public synchronized void  execute(Runnable task) throws Exception
    {
        this.taskQueue.enqueue(task);
    }

    public synchronized void stop(){
        this.isStopped = true;
        for(SingleThread thread : threads){
            thread.doStop();
        }

    }

    public  synchronized  void shutdown(){
        int taskSize = this.taskQueue.getSize();
        while(taskSize != 0) {
            taskSize = this.taskQueue.getSize();
        }
        for(SingleThread thread : threads){
            thread.doStop();
        }
        boolean allJobDone = false;
        while(allJobDone == false) {
            allJobDone = true;
            for(SingleThread thread : threads){

                if (thread.isJobDone() == false){
                    allJobDone = false;
                    if( thread.getState().toString() == "WAITING") {
                        thread.interrupt();
                    }
                }
            }
        }
    }

}