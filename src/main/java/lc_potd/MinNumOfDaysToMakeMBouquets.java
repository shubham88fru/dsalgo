package lc_potd;

//@link - https://leetcode.com/problems/minimum-number-of-days-to-make-m-bouquets/
//@check - https://www.youtube.com/watch?v=w24SIuukcZI&ab_channel=AryanMittal
public class MinNumOfDaysToMakeMBouquets {
    public int minDays(int[] bloomDay, int m, int k) {
        if (((long)m * (long) k) > bloomDay.length) return -1;

        return binarySearch(bloomDay, m, k);
    }

    /**
     At first when I read the problem, I had an impression that
     we can only select a group of K adjacent flowers if they have
     same value. But its not like that. You can select any group of
     k adjacent flowers, only that the answer for that group will be
     max of the group because that how many it will take at the least
     for all the flowers to be available to be put in the bouquet.
     */

    /**
     consider this problem as day by day. The days we have are from
     the min of the given array to max of the given array.
     On each day (starting from min) certain position of flowers will be
     in bloomed state (Note that on a given day x, all flowers with bloom day < x will
     also be considered bloomed only). We can observe that as we go on increasing the day,
     certainly more and more flowers will be in bloomed state i.e. with increasing days,
     bloomed flowers is an increasing number and so we can certainly get a hint of
     applying binary search to find the smallest day when it was possible to form m
     consecutive group of size k.
     */


    private int binarySearch(int[] bloomDay, int m, int k) {
        int l = 1; //lower bound - first day.

        int max = Integer.MIN_VALUE;
        for (int i=0; i<bloomDay.length; i++) {
            max = Math.max(max, bloomDay[i]);
        }
        int r = max; //upper bound - max day.

        int minDays = -1;

        //perform binary search over an increasing day.
        while (l <= r) {
            int mid = l + (r-l)/2; //day to be checked.
            if (canFormGroup(mid, bloomDay, m, k)) {
                minDays = mid; //potential answer.
                r = mid - 1; //check if even smaller possible.
            } else {
                l = mid + 1;
            }
        }

        return minDays;
    }

    private boolean canFormGroup(int mid, int[] bloomDay, int m, int k) {
        int consecDays = 0;
        int numConsecDays = 0;
        for (int i=0; i<bloomDay.length; i++) {
            //If so, ith flower is certainly bloomed on mid'th day, so can be included in the group.
            if (bloomDay[i] <= mid) {
                consecDays += 1;
            } else {
                consecDays = 0; //otherwise, break the count.
            }

            if (consecDays == k) { //if we have formed a group, increment numConsecDays found.
                numConsecDays += 1;
                consecDays = 0; //init for next group.
            }
        }

        return (numConsecDays >= m); //valid if we were able to form atleast m groups.
    }
}
