package misc;

import java.util.HashMap;
import java.util.Map;

public class DSForStoringBalances {

    private final Map<Integer, Integer> balances;

    public DSForStoringBalances() {
        balances = new HashMap<>();
    }

    public void set(int id, int balance) {
        balances.put(id, balance);
    }

    public int get(int id) {
        return balances.get(id) ;
    }

    @Override
    public String toString() {
        return balances.toString();
    }

    public static void main(String[] args) {
        DSForStoringBalances
                ds = new DSForStoringBalances();
        ds.set(1,100);
        ds.set(2,2000);
        System.out.println(ds);
        System.out.println(ds.get(1));
        ds.set(1,5000);
        System.out.println(ds);
    }
}
