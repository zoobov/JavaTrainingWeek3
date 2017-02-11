package homework.task3;


import homework.shop.concur.Shop;

/**
 * Created by 1 on 08.02.17.
 */
public class Customer implements Runnable {
    int number;
    Thread thread;
    Customer(int number){
        this.number = number;
        thread = new Thread(this, "Customer thread " + number);
        thread.start();
    }

    @Override
    public void run() {
        System.out.printf("Customer %d has come to shop...\n",number);
        try {
            AutoShop.LATCH.countDown();
            AutoShop.LATCH.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Customer %d has entered inside.\n", number);
    }
}
