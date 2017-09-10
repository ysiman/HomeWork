package hw1112Thread;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by HOME on 21.05.2017.
 */
public class TaskQueue {

    private volatile List queue = new LinkedList();


    public synchronized void enqueue(Object item)
            throws InterruptedException  {
        if(this.queue.size() == 0) {
            notifyAll();
        }
        this.queue.add(item);
    }


    public synchronized Object dequeue()
            throws InterruptedException{
        while(this.queue.size() == 0){
            wait();
        }

        return this.queue.remove(0);
    }

    public int getSize(){
        return this.queue.size();
    }

}