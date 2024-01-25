package ptrn.modifiedbinarysearch;

//@link - https://leetcode.com/problems/random-pick-with-weight/description/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/5111744692486144
public class RandomPickWithWeight {
    //array of running sums.
    int[] rs;
    public RandomPickWithWeight(int[] w) {
        rs = new int[w.length];
        int sum = 0;
        for (int i=0; i<w.length; i++) {
            rs[i] = sum + w[i];
            sum = rs[i];
        }
    }

    public int pickIndex() {
        //return pickSuboptimal();
        return pickOptimal();
    }

    //T: O(logn)
    //S: O(1)
    private int pickOptimal() {
        //select a random number between 0 to max running sum.
        int random = (int)(Math.random()*(rs[rs.length-1]));

        //find the first num in running sum array which is
        //greater than the selected random num and return its index.
        //optimal use binary search.
        int l = 0;
        int r = rs.length-1;
        int first = -1;
        while (l <= r) {
            int mid = l + (r-l)/2;
            if (rs[mid] > random) {
                first = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return first;
    }

    //Approach per edctv.
    //T: O(N) --> to find the higher num in rs array.
    //S: O(N) --> to store the running sums.
    private int pickSuboptimal() {
        //select a random number between 0 to max running sum.
        int random = (int)(Math.random()*(rs[rs.length-1]));
        //find the first num in running sum array which is
        //greater than the selected random num and return its index.
        //suboptimal coz, using linear search.
        for (int i=0; i<rs.length; i++) {
            if (rs[i] > random) return i;
        }
        return -1;
    }
}
