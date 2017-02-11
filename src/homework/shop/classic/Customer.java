package homework.shop.classic;

/**
 * Created by 1 on 08.02.17.
 */
public class Customer implements Runnable {
    int number;
    Thread thread;
    Notifier notifier;
    Customer(int number,Notifier notifier){
        this.number = number;
        this.notifier = notifier;
        thread = new Thread(this, "CustomerThread" + number);
        thread.start();
    }

    @Override
    public void run() {
        System.out.printf("Customer %d has come to shop...\n",number);
        notifier.waitTillOpened();
        System.out.printf("Customer %d has entered inside.\n",number);
    }

    public String getState (){
        return thread.getState().toString();
    }
}
