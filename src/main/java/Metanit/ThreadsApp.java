package Metanit;

/**
 * Created by HOME on 21.05.2017.
 */
public class ThreadsApp {

    public static void main(String[] args) {

        Store store=new Store();
        Producer producer = new Producer(store);
        Consumer consumer = new Consumer(store);
        new Thread(producer).start();
        new Thread(consumer).start();
    }
}