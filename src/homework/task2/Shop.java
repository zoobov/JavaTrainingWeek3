package homework.task2;


public class Shop implements Runnable {
    Thread thread;
    int sleepTime;
    int totalAmount,getAmount;

    Shop(String name, int sleepTime){
        thread = new Thread(this,name);
        this.sleepTime = sleepTime;
        totalAmount=0;
        getAmount=3;
        thread.start();
    }

    @Override
    public void run() {
        System.out.printf(thread.getName() + " is opened... (takes %d apples and pause %d)\n",getAmount,sleepTime);
        try {
            for (int i = 0; i < 10; i++) {
                getApple();
                thread.sleep(sleepTime);
            }
            System.err.println(thread.getName() + " is closed. Total sold apples " + totalAmount);
            AppleStore.stop=true;
        } catch (InterruptedException e) {
            System.err.println(thread.getName() + " was closed for selling small amount. Total sold apples " + totalAmount);
            return;
        }
    }



    private void getApple(){
        if (Main.appleStore.getApples(getAmount))
            totalAmount += getAmount;

    }

    public Thread getThread(){
        return thread;
    }
}


