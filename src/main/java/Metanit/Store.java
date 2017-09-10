package Metanit;

/**
 * Created by HOME on 21.05.2017.
 */
class Store{
    private int product=0;
    public synchronized void get() {
        while (product<1) {
            try {
                System.out.println("WAIT GET");
                wait();
            }
            catch (InterruptedException e) {
            }
        }
        product--;
        System.out.println("Покупатель купил 1 товар");
        System.out.println("Товаров на складе: " + product);
        notify();
    }
    public synchronized void put() {
        while (product>=3) {
            try {
                System.out.println("WAIT PUT");
                wait();
            }
            catch (InterruptedException e) {
            }
        }
        product++;
        System.out.println("Производитель добавил 1 товар");
        System.out.println("Товаров на складе: " + product);
        notify();
    }
}