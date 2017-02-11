package homework.task2;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class AppleStore {
    HashMap<String,Integer> apples;
    private boolean isBusy;

    AppleStore(){
        apples = new HashMap<String, Integer>();
        isBusy = false;
        System.out.println("Apple store is opened");
   }

   public synchronized void putApples(String name, int amount){
       int tmpValue=0;
       while (isBusy) {
           try {
               System.out.println(Thread.currentThread().getName() + " is waiting to supply new apples");
               wait();
           } catch (InterruptedException e) {}
       }
       isBusy=true;
       if (apples.containsKey(name))
           tmpValue = apples.get(name);
       apples.put(name,(amount + tmpValue));
       isBusy=false;
       notifyAll();
       System.out.printf("+++ " + Thread.currentThread().getName() + " added %d %s apples to store\n",amount,name);
   }

   public synchronized void getApples(int amount){
       int tmpValue, v;
       String k = null;
       while (isBusy && apples.isEmpty()) {
           try {
               System.out.println(Thread.currentThread().getName() + " is waiting to get apples");
               wait();
           } catch (InterruptedException e) {
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
                apples.remove(k);
                 break;
            }else{
                 v-=amount;
                apples.put(k,v);
            }
       }
       isBusy=false;
       notifyAll();
       System.out.printf("--- " + Thread.currentThread().getName() + " has taken %d apples from store.\n ",amount);

   }
}
