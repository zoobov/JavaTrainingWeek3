package homework.shop.classic;

import java.util.concurrent.CountDownLatch;

/**
 * Created by 1 on 08.02.17.
 */
public class Shop {
    static final int CUST_COUNT = 5;
    static public boolean isOpened = false;

    public static void main(String[] args) throws InterruptedException  {
        Customer[] customers = new Customer[CUST_COUNT];
        Notifier notifier = new Notifier();
        for (int i = 0; i <CUST_COUNT ; i++) {
            customers[i] = new Customer(i+1,notifier);
            Thread.sleep(500);
        }

        System.out.println("..........................................................");
        for (int i = 0; i <CUST_COUNT ; i++) {
            System.out.printf("Customer %d is " + customers[i].getState() + "\n", i + 1);
        }
        System.out.println("........Sales has started, Welcome in the shop!!!.........");
        notifier.notifyOpened();

    }
}
