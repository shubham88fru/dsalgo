package lc_potd;

import java.util.*;

//@link - https://leetcode.com/problems/find-x-sum-of-all-k-long-subarrays-ii/description/?
//@check - https://www.youtube.com/watch?v=ZyE1zCe_gSQ
public class FindXSumOfAllLongSubarraysII {

    long sum = 0; //maintains sum of top x elements from main set
    TreeSet<int[]> main; //contains top-x freq, elements
    TreeSet<int[]> sec;  //contains remaining freq, elements
    Map<Integer, Integer> freq; //element -> frequency

    public long[] findXSum(int[] nums, int k, int x) {
        // return pass1(nums, k, x);
        return pass2(nums, k, x);
    }

    /**
     direct copy pasta from mik.
     was getting too overwhelming to
     code on own. Easy to understand
     but difficult to code,
     maybe will try next time.

     */
    public long[] pass2(int[] nums, int k, int x) {
        int n = nums.length;
        sum = 0;
        freq = new HashMap<>();

        // Comparator to sort by freq first, then by val (both ascending)
        Comparator<int[]> comp = (a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        };

        main = new TreeSet<>(comp);
        sec = new TreeSet<>(comp);

        List<Long> resultList = new ArrayList<>();

        int i = 0;
        int j = 0;
        while (j < n) {
            int num = nums[j];

            // If already present, remove old (freq, val)
            if (freq.getOrDefault(num, 0) > 0) {
                removeFromSet(new int[]{freq.get(num), num}, x);
            }

            // Increase frequency
            freq.put(num, freq.getOrDefault(num, 0) + 1);

            // Insert updated pair
            insertInSet(new int[]{freq.get(num), num}, x);

            // When window size becomes k
            if (j - i + 1 == k) {
                resultList.add(sum);

                // Remove outgoing element
                int outNum = nums[i];
                removeFromSet(new int[]{freq.get(outNum), outNum}, x);
                freq.put(outNum, freq.get(outNum) - 1);

                if (freq.get(outNum) == 0) {
                    freq.remove(outNum);
                } else {
                    insertInSet(new int[]{freq.get(outNum), outNum}, x);
                }

                i++;
            }

            j++;
        }

        // Convert List<Long> to long[]
        long[] result = new long[resultList.size()];
        for (int idx = 0; idx < resultList.size(); idx++) {
            result[idx] = resultList.get(idx);
        }
        return result;
    }

    void insertInSet(int[] p, int x) {
        if (main.size() < x || comparePairs(p, main.first()) > 0) {
            sum += 1L * p[0] * p[1];
            main.add(p);

            if (main.size() > x) {
                int[] smallest = main.first();
                sum -= 1L * smallest[0] * smallest[1];
                main.remove(smallest);
                sec.add(smallest);
            }
        } else {
            sec.add(p);
        }
    }

    void removeFromSet(int[] p, int x) {
        if (main.contains(p)) {
            sum -= 1L * p[0] * p[1];
            main.remove(p);

            if (!sec.isEmpty()) {
                int[] largest = sec.last();
                sec.remove(largest);
                main.add(largest);
                sum += 1L * largest[0] * largest[1];
            }
        } else {
            sec.remove(p);
        }
    }

    // Helper comparison to mimic pair comparison from C++
    int comparePairs(int[] a, int[] b) {
        if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
        return Integer.compare(a[1], b[1]);
    }

    /*
        Soln for type I of this problem.
        Gives TLE.
     */
    private long[] pass1(int[] nums, int k, int x) {
        int n = nums.length;

        long[] ans = new long[n-k+1];
        Map<Integer, Integer> window = new HashMap<>();

        //sliding window
        int l = 0;
        int r = 0;
        int idx = 0;
        while (r < n) {
            while (r - l < k) {
                window.put(nums[r], window.getOrDefault(nums[r], 0)+1);
                r += 1;
            }

            ans[idx++] = calc(window, x);

            int toRem = nums[l];
            window.put(toRem, window.get(toRem)-1);
            if (window.get(toRem) == 0) window.remove(toRem);
            l += 1;
        }


        return ans;
    }

    private long calc(Map<Integer, Integer> freqs, int x) {

        /**
         Note: min heap (reverse of what's needed) to
         implement top-k freq.
         */
        Comparator<int[]> cmp1 = (a1, a2) -> a1[1] - a2[1];
        Comparator<int[]> cmp2 = (a1, a2) -> a1[0] - a2[0];
        Comparator<int[]> combined = cmp1.thenComparing(cmp2);

        PriorityQueue<int[]> pq = new PriorityQueue<>(combined);

        for (Map.Entry<Integer, Integer> entry: freqs.entrySet()) {
            int[] item = new int[]{entry.getKey(), entry.getValue()};

            //note how to compare using comparator.
            /**
             if (pq.size() < x) {
             pq.add(item);
             } else if (combined.compare(pq.peek(), item) < 0) {
             pq.remove();
             pq.add(item);
             }
             */

            //this is smart
            pq.add(item);
            if (pq.size() > x) pq.remove();
        }

        long sum = 0;
        while (x > 0 && !pq.isEmpty()) {
            int[] rem = pq.remove();
            sum += ((long)rem[0]*rem[1]);
            x -= 1;
        }

        return sum;
    }
}
