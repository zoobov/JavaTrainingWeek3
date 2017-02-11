package homework.task2;


import java.util.Random;

public class Provider implements Runnable{
    Thread thread;
    int sleepTime;
    String[] appleNames = {"Macintosh", "Semerenko", "Aidared"};
    Random random;
    int totalAmount;

    Provider(String name, int sleepTime){
        thread = new Thread(this,name);
        this.sleepTime = sleepTime;
        random = new Random();
        totalAmount = 0;
        thread.start();
    }

    @Override
    public void run() {
        System.out.println(thread.getName() + " is opened...");
        try {
            for (int i = 0; i < 40; i++) {
                supplyApple();
                thread.sleep(sleepTime);
            }
            System.err.println(thread.getName() + " is closed.");
        } catch (InterruptedException e) {
            System.err.println(thread.getName() + " was closed coz all shops was closed. ");
            return;
        }
    }




    private void supplyApple(){
        String name = appleNames[random.nextInt(2)];
        int amount =random.nextInt(4)+1;
        totalAmount +=amount;
        Main.appleStore.putApples(name,amount);
    }

    public Thread getThread(){
        return thread;
    }
}
