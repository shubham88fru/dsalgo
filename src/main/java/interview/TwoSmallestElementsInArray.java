package interview;

//@link - https://practice.geeksforgeeks.org/problems/find-the-smallest-and-second-smallest-element-in-an-array3226/1?utm_source=gfg&utm_medium=article&utm_campaign=bottom_sticky_on_article
//@company - fyndna techcorp
public class TwoSmallestElementsInArray {
    public long[] minAnd2ndMin(long[] a, long n)  {
        return get2Smallest(a);
    }

    //T: O(N), one pass solution
    private long[] get2Smallest(long[] a) {
        long least = Integer.MAX_VALUE;
        long sleast = Integer.MAX_VALUE;

        for (long el : a) {
            //if lower than lowest,
            if (el < least) {
                sleast = least; //mark second least as prev least
                least = el; //update the least
            } else if (el < sleast && el > least) { //otherwise, if greater than least and less than second least
                sleast = el; //update second least.
            }
        }

        if (least == sleast || sleast == Integer.MAX_VALUE) return new long[]{-1};

        return new long[]{least, sleast};
    }

    //T: O(2N) ~ O(N), two pass soln
    private static void printTwoSmallest(int[] arr) {
        int min1 = Integer.MAX_VALUE;
        int min1Idx = -1;
        for (int i=0; i<arr.length; i++) {
            if (arr[i] < min1) {
                min1 = arr[i];
                min1Idx = i;
            }
        }

        int min2 = Integer.MAX_VALUE;

        for (int i=0; i<arr.length; i++) {
            if ((arr[i] < min2) && arr[i] > min1) {
                min2 = arr[i];
            }
        }

        System.out.println(min1 + ", " + min2);
    }
}
