package homework.shop.concur;


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
            Shop.LATCH.countDown();
            Shop.LATCH.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            thread.sleep(1000);
            System.out.printf("Customer %d has entered inside.\n",number);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
