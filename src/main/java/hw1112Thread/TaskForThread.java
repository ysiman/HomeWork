package hw1112Thread;

/**
 * Created by HOME on 21.05.2017.
 */
public class TaskForThread implements Runnable {

    private String task;
    SingleThread singleThread;
    public TaskForThread(String s){
        this.task=s;
    }

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