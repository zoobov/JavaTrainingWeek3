package homework.shop.concur;

import java.util.concurrent.CountDownLatch;

/**
 * Created by 1 on 08.02.17.
 */
public class Shop {
    static final int CUST_COUNT = 5;
    static final CountDownLatch LATCH = new CountDownLatch(CUST_COUNT + 1);

    public static void main(String[] args) throws InterruptedException  {
        for (int i = 1; i <= CUST_COUNT ; i++) {
            new Customer(i);
            Thread.sleep(500);
        }

        System.out.println("........Sales has started, pleas come in.........");
        LATCH.countDown();


    }
}
