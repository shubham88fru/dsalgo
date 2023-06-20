package interview;

//@link - https://practice.geeksforgeeks.org/problems/find-the-smallest-and-second-smallest-element-in-an-array3226/1?utm_source=gfg&utm_medium=article&utm_campaign=bottom_sticky_on_article
//company - fyndna techcorp
public class TwoSmallestElementsInArray {
    public long[] minAnd2ndMin(long[] a, long n)  {
        return get2Smallest(a);
    }

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
}
