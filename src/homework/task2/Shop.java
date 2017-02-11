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
        System.out.println(thread.getName() + " is opened...");
        for (int i = 0; i < 10; i++) {
            try {
                getApple();
                thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                System.out.println(thread.getName() + " is closed. Total sold apples " + totalAmount);
                return;
            }
        }
        System.out.println(thread.getName() + " is closed. Total sold apples " + totalAmount);
    }

    private void getApple(){
        totalAmount += getAmount;
        Main.appleStore.getApples(getAmount);
    }
}


