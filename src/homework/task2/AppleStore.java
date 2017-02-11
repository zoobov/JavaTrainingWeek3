package homework.task2;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class AppleStore {
    HashMap<String,Integer> apples;
    private boolean isBusy;
    int totalAmount;
    static boolean stop = false;

    AppleStore(){
        apples = new HashMap<String, Integer>();
        isBusy = false;
        totalAmount=0;
        System.out.println("Apple store is opened");
   }


    private int getTotAmount(){
        int total=0;
        Collection<Integer> values = apples.values();
        Iterator<Integer> iterator = values.iterator();
        while(iterator.hasNext()){
            total+=iterator.next();
        }
        //System.out.println("getTotAmount()=" + total);
        return total;
    }

    public synchronized void putApples(String name, int amount){
       int tmpValue=0;
       while (isBusy) {
           try {
               System.out.println(Thread.currentThread().getName() + " is waiting to supply new apples");
               wait();
           } catch (InterruptedException e) {
               return;
           }
           return;
       }

       isBusy=true;
       if (apples.containsKey(name))
           tmpValue = apples.get(name);
       apples.put(name,(amount + tmpValue));
       totalAmount = getTotAmount();
       System.out.printf("+++ " + Thread.currentThread().getName() + " added %d %s apples to store, Total:%d\n",amount,name,totalAmount);
       isBusy=false;
       notifyAll();
   }

    public synchronized boolean getApples(int amount){
       int v,getTotal;
       String k;
       getTotal = amount;
       while (isBusy || (amount > totalAmount)) {
           try {
               System.out.println(Thread.currentThread().getName() + " is waiting to get apples");
               wait();
               //System.out.println(Thread.currentThread().getName() + " awaken from sleep");
           } catch (InterruptedException e) {
               return false;
           }
           if (stop) {
               Thread.currentThread().interrupt();
               return false;
           }
       }
       isBusy=true;
       Set<String> appleSet = apples.keySet();
       Iterator<String> iterator = appleSet.iterator();
       while(iterator.hasNext()){
           k=iterator.next();
           v=apples.get(k);
           if (amount >= v) {
                amount -= v;
                apples.put(k,0);
            }else{
                 v-=amount;
                 apples.put(k,v);
            }
       }
       totalAmount = getTotAmount();
       System.out.printf("--- " + Thread.currentThread().getName() + " has got %d apples from store, Total apples on store:%d\n",getTotal,totalAmount);
       isBusy=false;
       notifyAll();
       return true;
   }



}
