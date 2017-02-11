package homework.task2;

public class Main {
    public static AppleStore appleStore;
    public static void main(String[] args) {
        appleStore = new AppleStore();
        Provider provider = new Provider("Apple provider",2000);
        Shop shop1 = new Shop("Shop 1", 200);
        Shop shop2 = new Shop("Shop 2", 400);

    }
}
