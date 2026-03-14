package lc_potd;

import java.util.*;

//@link - https://leetcode.com/problems/high-five/
public class HighFive {
    public int[][] highFive(int[][] items) {
        return pass1(items);
    }

    private int[][] pass1(int[][] items) {
        int n = items.length;
        Map<Integer, PriorityQueue<Integer>> mp = new HashMap<>();

        for (int[] item: items) {
            int id = item[0];
            int score = item[1];

            mp.putIfAbsent(id, new PriorityQueue<>());
            mp.get(id).add(score);
            if (mp.get(id).size() > 5) mp.get(id).remove();
        }

        List<int[]> ans = new ArrayList<>();
        for (Map.Entry<Integer, PriorityQueue<Integer>> entry: mp.entrySet()) {
            PriorityQueue<Integer> pq = entry.getValue();
            int sum = 0;
            while (!pq.isEmpty()) {
                sum += pq.remove();
            }
            ans.add(new int[]{entry.getKey(), sum/5});
        }

        Collections.sort(ans, (a1, a2) -> a1[0]-a2[0]);

        return ans.toArray(int[][]::new);
    }
}
