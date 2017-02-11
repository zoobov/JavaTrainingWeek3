package homework.task2;

public class Main {
    public static AppleStore appleStore;
    public static void main(String[] args) {
        appleStore = new AppleStore();
        Thread providerThread, shop1Thread, shop2Thread;
        Provider provider = new Provider("Apple provider",400);
        providerThread = provider.getThread();
        Shop shop1 = new Shop("Shop 1", 100);
        shop1Thread = shop1.getThread();
        Shop shop2 = new Shop("Shop 2", 200);
        shop2Thread = shop2.getThread();
        //shop2Thread.setPriority(1);

        try {
            shop1Thread.join();
            shop2Thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        providerThread.interrupt();


    }
}
