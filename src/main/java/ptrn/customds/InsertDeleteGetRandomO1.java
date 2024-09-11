package ptrn.customds;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

//@link - https://leetcode.com/problems/insert-delete-getrandom-o1/description/
//check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/5235238692454400
public class InsertDeleteGetRandomO1 {
    //edctv soln.
    class RandomizedSet {
        Map<Integer, Integer> mp = new HashMap<>();
        List<Integer> lst = new ArrayList<>();
        Random rand = new Random();

        public RandomizedSet() {
        }

        public boolean insert(int val) {
            if (mp.containsKey(val)) return false;
            lst.add(val);
            int idx = lst.size()-1;
            mp.put(val, idx);
            return true;
        }

        public boolean remove(int val) {
            if (!mp.containsKey(val)) return false;

            /**
             swap the last element in the array and
             the element to be removed.
             Update the map accordingly.
             Delete the element to be removed from
             the array and the map.
             */
            int idx = mp.get(val);
            int lastEl = lst.get(lst.size()-1);
            lst.set(idx, lastEl);
            lst.set(lst.size()-1, val);
            mp.put(lastEl, idx);
            lst.remove(lst.size()-1);
            mp.remove(val);
            return true;
        }

        public int getRandom() {
            return lst.get(rand.nextInt(lst.size()));
        }
    }



    //My soln. Kinda nasty and getRandom is not truly O(1)
    class RandomizedSet2 {

        private Map<Integer, Integer> valToIdx;
        private Map<Integer, Integer> idxToVal;
        private int idx = 0;

        public RandomizedSet2() {
            valToIdx = new HashMap<>();
            idxToVal = new HashMap<>();
        }

        public boolean insert(int val) {
            if (valToIdx.containsKey(val)) return false;
            valToIdx.put(val, idx+1);
            idxToVal.put(idx+1, val);
            idx += 1;
            return true;
        }

        public boolean remove(int val) {
            if (!valToIdx.containsKey(val)) return false;
            int id = valToIdx.get(val);
            valToIdx.remove(val);
            idxToVal.remove(id);
            return true;
        }

        public int getRandom() {
            int randomNum = ThreadLocalRandom.current().nextInt(0, idx + 1);
            while (!idxToVal.containsKey(randomNum)) {
                randomNum = ThreadLocalRandom.current().nextInt(0, idx + 1);
            }
            return idxToVal.get(randomNum);
        }
    }

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
}
