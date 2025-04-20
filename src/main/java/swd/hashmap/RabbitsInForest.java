package swd.hashmap;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

//@link - https://leetcode.com/problems/rabbits-in-forest/description/
public class RabbitsInForest {
    /**
     *  1) My Soln - Works fine
     * This seems to be the best, coz its a
     * single pass soln and much more intuitive.
     * **/
    public int numRabbits(int[] answers) {
        Map<Integer, Integer> groups = new HashMap<>();
        int minRabits = 0;
        for (int answer : answers) {
            //Create a new group if answer not seen before
            //or if seen before but space exhausted
            //of if no other rabbit of same color
            if (!groups.containsKey(answer) || Objects.equals(groups.get(answer), 0) || answer == 0) {
                //We need to find the min possible no. of rabbits
                //so we'll assume that the rabbits that have not reported
                //have not seen any extra rabbit of same color.
                groups.put(answer, answer);
                minRabits += answer + 1;
            } else {
                //else keep using up the space.
                groups.put(answer, groups.get(answer) - 1);
            }
        }
        return minRabits;
    }

    /** 2) SWD Soln **/
    public int numRabbits2(int[] answers) {
        int minRabbits = 0;
        Map<Integer, Integer> memo = new HashMap<>();
        for (int ans: answers) {
            memo.put(ans, memo.getOrDefault(ans, 0)+1);
        }

        for (int currentKey: memo.keySet()) {
            int groupSize = currentKey + 1;
            double numOfGroupsRequired = Math.ceil(memo.get(currentKey) / (double) groupSize);
            minRabbits += (groupSize*numOfGroupsRequired);
        }

        return minRabbits;
    }

    /** 3) My mathy soln **/
    private int revise(int[] answers) {
        int n = answers.length;
        Map<Integer, Integer> mp = new HashMap<>();

        for (int ans: answers) {
            mp.put(ans, mp.getOrDefault(ans, 0)+1);
        }

        int min = 0;
        for (Map.Entry<Integer, Integer> entry: mp.entrySet()) {
            int key = entry.getKey();
            int freq = entry.getValue();

            int div = key + 1;
            if (freq <= div) {
                min += (1 + key);
            } else {
                min += (freq/div)*(1 + key);

                if (freq%div > 0) min += (1+key);
            }

        }

        return min;
    }
}
