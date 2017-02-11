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
        for (int i = 0; i < 20; i++) {
            try {
                supplyApple();
                thread.sleep(sleepTime);
            } catch (InterruptedException e) {

                return;
            }
        }

        System.out.println(thread.getName() + " is closed.");
    }

    private void supplyApple(){
        String name = appleNames[random.nextInt(2)];
        int amount =random.nextInt(4)+1;
        totalAmount +=amount;
        Main.appleStore.putApples(name,amount);
    }
}
