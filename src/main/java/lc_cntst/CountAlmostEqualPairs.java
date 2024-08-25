package lc_cntst;

//@link - https://leetcode.com/problems/count-almost-equal-pairs-i/
public class CountAlmostEqualPairs {
    public int countPairs(int[] nums) {
        int count = 0;
        for (int i=0; i<nums.length; i++) {
            for (int j=i+1; j<nums.length; j++) {
                if (check(nums[i], nums[j])) {
                    count += 1;
                }
            }
        }

        return count;
    }

    private boolean check(int num1, int num2) {
        StringBuffer ns1 = new StringBuffer(String.valueOf(num1));
        StringBuffer ns2 = new StringBuffer(String.valueOf(num2));

        if (ns1.toString().equals(ns2.toString())) return true;

        for (int i=0; i<ns1.length(); i++) {
            for (int j=i+1; j<ns1.length(); j++) {
                char previ = ns1.charAt(i);
                char prevj = ns1.charAt(j);
                ns1.setCharAt(i, prevj);
                ns1.setCharAt(j, previ);
                if (Integer.parseInt(ns1.toString()) == Integer.parseInt(ns2.toString())) return true;
                ns1.setCharAt(i, previ);
                ns1.setCharAt(j, prevj);
            }
        }

        for (int i=0; i<ns2.length(); i++) {
            for (int j=i+1; j<ns2.length(); j++) {
                char previ = ns2.charAt(i);
                char prevj = ns2.charAt(j);
                ns2.setCharAt(i, prevj);
                ns2.setCharAt(j, previ);
                if (Integer.parseInt(ns2.toString()) == Integer.parseInt(ns1.toString())) return true;
                ns2.setCharAt(i, previ);
                ns2.setCharAt(j, prevj);
            }
        }

        return false;
    }
}
