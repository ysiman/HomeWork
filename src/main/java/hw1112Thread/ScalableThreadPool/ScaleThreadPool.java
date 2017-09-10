package hw1112Thread.ScalableThreadPool;

import hw1112Thread.TaskQueue;
import hw1112Thread.SingleThread;

import java.util.LinkedList;

public class ScaleThreadPool {

    private TaskQueue taskQueue = null;
    private LinkedList<SingleThread> threads = new LinkedList<>();
    private boolean isStopped = false;
    private int minNumbThreads;
    private int maxNumbThreads;

    public boolean isTerminated(){
        return isStopped;
    }

    public ScaleThreadPool(int minNumbThreads,int maxNumbThreads){
        this.minNumbThreads = minNumbThreads;
        this.maxNumbThreads = maxNumbThreads;

        taskQueue = new TaskQueue();

        bornNewThreads(minNumbThreads,null);
    }

    private void bornNewThreads(int numThreads, String message ){
        for(int i=0; i< numThreads; i++){
            threads.add(new SingleThread(taskQueue));
            SingleThread thread = threads.getLast();
            thread.start();
            if (message !=  null){
                System.out.println(message+thread.getName());
            }
        }

    }

    public synchronized void  execute(Runnable task) throws Exception
    {
        // if(this.isStopped) throw
        //   new IllegalStateException("ScaleThreadPool is stopped");

        this.taskQueue.enqueue(task);
    }

    public synchronized void stop(){
        this.isStopped = true;
        for(SingleThread thread : threads){
            thread.doStop();
        }

    }

    public void manageThreads() {
        int taskSize = this.taskQueue.getSize();
        int colAddThreads;
        int delta;
        int threadPoolSize = threads.size();
        while (taskSize > 0) {

            if (taskSize > this.minNumbThreads) {
                if (threadPoolSize < this.maxNumbThreads){
                        colAddThreads = 0;
                        delta = taskSize - this.minNumbThreads;
                        if (delta + this.minNumbThreads <= this.maxNumbThreads) {
                            colAddThreads = delta;
                        } else {
                            colAddThreads = this.maxNumbThreads - threadPoolSize;
                        }
                         bornNewThreads(colAddThreads, "Create additional thread: ");
                    }

            } else {

                threadPoolSize = threads.size();
                if (threadPoolSize > this.minNumbThreads) {
                    delta = threadPoolSize - this.minNumbThreads;

                    for (int i = 0; i < delta; i++) {
                        SingleThread thread = threads.getLast();
                        String tmp = thread.getName();
                            thread.doStop();
                            threads.remove(thread);
                            System.out.println("Delete additional thread:" + thread.getName());
                    }


                }
            }
            threadPoolSize = threads.size();
            taskSize = this.taskQueue.getSize();
           // System.out.println("threadPoolSize=" + threadPoolSize + " taskSize=" + taskSize);
        }
    }

    public    void shutdown(){
        while(this.taskQueue.getSize() != 0) {

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
                }
            }
        }
    }

}