package homework.task1;

public class ThreadMgr extends Thread{
    public static final Object MONITOR = new Object();
    public static boolean paused = false;
    public static String lastThreadName;
    MyThread[] myTreads;

    ThreadMgr(){
        super("Thread maneger");
        myTreads = new MyThread[4];
        for (int i = 0; i < 4; i++) {
            myTreads[i] = new MyThread("Thread " + (i+1) );
        }
        start();
    }


    @Override
    public void run () {
        myTreads[0].start();
        try {
            myTreads[0].join(); //waiting for thread 1 terminates
        } catch (InterruptedException e) {}

        paused=true;
        myTreads[1].start();
        myTreads[2].start();

        while(true) {
            if (myTreads[1].getState() == State.WAITING && myTreads[1].getState() == State.WAITING ) {
                paused=false;
                wakeThreads();
                break;
            }
        }
        try {
            myTreads[1].join(); //waiting for thread 2,3 terminates
            myTreads[2].join();
        } catch (InterruptedException e) {}

        myTreads[3].setName(myTreads[3].getName() + " run after " + lastThreadName );
        myTreads[3].start();
        try {
            myTreads[3].join(); //waiting for thread 4 terminates
        } catch (InterruptedException e) {}


        System.out.println("Thread mgr is terminated");

    }


    public static void getReady(){
        synchronized (MONITOR){
            while (paused){
                try {
                    System.out.println(Thread.currentThread().getName() + " waiting to start..." );
                    MONITOR.wait();
                } catch (InterruptedException e) {}

            }
        }

    }


    public static void wakeThreads(){
        synchronized (MONITOR){
            System.out.println("Trying to wake up threads...");
            MONITOR.notifyAll();
        }
    }

    public synchronized static void threadTermReport(){
        lastThreadName = Thread.currentThread().getName();

    }


}
