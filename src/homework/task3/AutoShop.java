package homework.task3;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * Created by 1 on 08.02.17.
 */
public class AutoShop {
    static final CountDownLatch LATCH = new CountDownLatch(11);

    public static void main(String[] args) throws InterruptedException  {
        ArrayList<Car> cars = new ArrayList<Car>();
        Random rnd = new Random();
        for (int i = 0; i < 5 ; i++) {
            cars.add(new Car(rnd.nextInt()));
        }

        for (int i = 1; i <= 10 ; i++) {
            new Customer(i);
            Thread.sleep(500);
        }
        if (cars.size()==5 ) {
            System.out.println("........Auction has started.........");
            LATCH.countDown();
        }
    }
}
