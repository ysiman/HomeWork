package JMM;

import hw1112Thread.TaskQueue;

/**
 * Created by sbt-siman-yn on 29/05/2017.
 */
public class SingleClThread<T> extends Thread{
    private TaskQueue taskQueue = null;

    private  boolean  isStopped = false;
    private  boolean  jobDone = false;
    private T result;

    public SingleClThread(TaskQueue queue){
        taskQueue = queue;
    }

    public synchronized boolean isJobDone() {
        return jobDone;
    }

    public void run(){

        while(!isStopped()){
            try{
                //Runnable runnable = (Runnable) taskQueue.dequeue();
                //runnable.run();
                TaskCallbl tsk = (TaskCallbl) taskQueue.dequeue();
                result = (T) tsk.get();
            } catch(Exception e){
                //Something wrong but it's all wright.  Keep working!
            }
        }
        jobDone = true;
    }

    public  void doStop() {
        isStopped = true;
        String state;
        while (jobDone == false) {
            state = this.getState().toString();
            if (state.equals("WAITING")) {
                this.interrupt();
                jobDone = true;
            }
        }
    }



    public synchronized boolean isStopped(){
        return isStopped;
    }
}
