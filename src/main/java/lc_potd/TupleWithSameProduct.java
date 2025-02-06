package lc_potd;

import java.util.*;

//@link - https://leetcode.com/problems/tuple-with-same-product/description/
//@check - https://www.youtube.com/watch?v=aC7q-YCFV2o&t=2746s&ab_channel=codestorywithMIK
public class TupleWithSameProduct {
    public int tupleSameProduct(int[] nums) {
        // return pass1(nums);
        // return brute(nums);
        return optimal(nums);
    }

    /*
        Following are all my solutions (optimal was after some hints)
        Mik showed a slightly different optimal approach.
     */
    private int optimal(int[] nums) {
        int n = nums.length;

        Map<Integer, Integer> mp = new HashMap<>();
        int count = 0;
        for (int i=0; i<n; i++) {
            for (int j=i+1; j<n; j++) {
                int pdt = nums[i] * nums[j];
                if (mp.containsKey(pdt)) {
                    int cnt = mp.get(pdt); //no. of times this product is seen before.
                    count += (8*cnt);
                }
                mp.put(pdt, mp.getOrDefault(pdt, 0) + 1);
            }
        }

        return count;
    }

    //1) First intuition was to solve it
    //using pick/notpick pattern with backtracking.
    //Gives TLE though.
    private int pass1(int[] nums) {

        List<List<Integer>> lst = new ArrayList<>();
        dfs(nums, nums.length, lst, new ArrayList<>(), 0);

        return lst.size()*8;
    }

    private void dfs(int[] nums, int n, List<List<Integer>> lst, List<Integer> picked, int curr) {
        if (picked.size() == 4) {
            if (equalProduct(picked)) {
                lst.add(new ArrayList<>(picked));
            }
            return ;
        }

        if (curr >= n) return;

        picked.add(nums[curr]);
        dfs(nums, n, lst, picked, curr+1);
        picked.remove(picked.size()-1);

        dfs(nums, n, lst, picked, curr+1);
    }

    //2) Simple 4 for loops. Again gives TLE.
    private int brute(int[] nums) {
        int n = nums.length;

        int count = 0;
        for (int i=0; i<n; i++) {
            for (int j=i+1; j<n; j++) {
                for (int k=j+1; k<n; k++) {
                    for (int l=k+1; l<n; l++) {
                        if (equalProduct(Arrays.asList(nums[i], nums[j], nums[k], nums[l]))) {
                            count += 8;
                        }
                    }
                }
            }
        }

        return count;
    }

    private boolean equalProduct(List<Integer> lst) {
        int a = lst.get(0);
        int b = lst.get(1);
        int c = lst.get(2);
        int d = lst.get(3);

        return (a*b==c*d) || (a*c == b*d) || (a*d == b*c);
    }
}
