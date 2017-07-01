package hw1112Thread;


/**
 * Created by HOME on 21.05.2017.
 */
public class SingleThread extends Thread {

    private TaskQueue taskQueue = null;

    private  boolean  isStopped = false;
    private  boolean  jobDone = false;

    public SingleThread(TaskQueue queue){
        taskQueue = queue;
    }

    public synchronized boolean isJobDone() {
        return jobDone;
    }

    public void run(){

        while(!isStopped()){
            try{
                Runnable runnable = (Runnable) taskQueue.dequeue();
                runnable.run();
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