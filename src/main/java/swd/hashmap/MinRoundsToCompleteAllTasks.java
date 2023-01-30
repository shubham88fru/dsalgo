package swd.hashmap;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

//@link - https://leetcode.com/problems/minimum-rounds-to-complete-all-tasks/description/
public class MinRoundsToCompleteAllTasks {

    public int minimumRounds(int[] tasks) {
        int res = 0;
        Map<Integer, Integer> frequencyMap = new HashMap<Integer, Integer>();

        // keep a map of all elements of the array against their frequency.
        for (int el: tasks) {
            if (frequencyMap.containsKey(el)) {
                frequencyMap.put(el, frequencyMap.get(el)+1);
            } else frequencyMap.put(el, 1);
        }

        //For all the entries of map, divide their frequency in 2/3 in min possible ways.
        for (Map.Entry<Integer, Integer> entry:  frequencyMap.entrySet()) {
            int frequency = entry.getValue();

            //If the element appears less than twice, its not possible to finish.
            if (frequency < 2) return -1;

                // else if the element can't be divided by three, try to break into two parts
                // and three parts as much as possible.
            else if (Objects.equals(frequency%3, 1)) {
                int i = 0;
                int twoParts = 0;
                int threeParts = 0;
                while (i < frequency) {
                    if ((frequency-i)%3 != 0) {
                        i += 2;
                        twoParts++;
                    } else {
                        threeParts = (frequency-i) / 3;
                        break;
                    }
                }
                if (i <= frequency) res += (twoParts + threeParts);
                else return -1;
            } else if (Objects.equals(frequency%3, 2)) {
                int threeParts = frequency/3;
                int twoParts = 1;
                res += (threeParts + twoParts);
            } else {
                res += frequency/3;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        MinRoundsToCompleteAllTasks minRoundsToCompleteAllTasks = new MinRoundsToCompleteAllTasks();
        int res = minRoundsToCompleteAllTasks.minimumRounds(new int[] {66,66,63,61,63,63,64,66,66,65,66,65,61,67,68,66,62,67,61,64,66,60,69,66,65,68,63,60,67,62,68,60,66,64,60,60,60,62,66,64,63,65,60,69,63,68,68,69,68,61});
        System.out.println(res);
    }
}
