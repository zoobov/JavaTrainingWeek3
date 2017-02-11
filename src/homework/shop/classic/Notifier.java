package homework.shop.classic;

/**
 * Created by 1 on 08.02.17.
 */
public class Notifier {
    private boolean isOpened = false;

    public synchronized void waitTillOpened(){
        while(!isOpened){
            try {
                //System.out.println(Thread.currentThread());
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void notifyOpened(){
        isOpened=true;
        notifyAll();
    }
}
