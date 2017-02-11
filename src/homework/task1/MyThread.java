package homework.task1;

import java.util.Random;

public class MyThread extends Thread  {
    int sleepTIme;

    MyThread(String name) {
        super(name);
        sleepTIme = new Random().nextInt(1000)+100;
    }

    @Override
    public void run() {
        ThreadMgr.getReady();
        System.out.println(getName() + " is started... (Start time:" + System.currentTimeMillis() + ")");
        try {
            for (int i = 5; i > 0 ; i--) {
                sleep(sleepTIme);
            }
        } catch (InterruptedException e) {
            System.out.println(getName() + " is interrupted");
            return;
        }
        ThreadMgr.threadTermReport();
        System.out.println(getName() + " is terminated. (End time:" + System.currentTimeMillis() + ")" );

    }
}
