package homework.task1;

/**
 * Created by 1 on 08.02.17.
 */

public class Main {

    public static void main(String[] args) {
      ThreadMgr tmgr =  new ThreadMgr();
        try {
            tmgr.join();
        } catch (InterruptedException e) {}

    }
}
