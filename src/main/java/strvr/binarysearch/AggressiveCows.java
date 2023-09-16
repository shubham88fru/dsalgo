package strvr.binarysearch;

import java.util.Arrays;

//@link - https://www.spoj.com/problems/AGGRCOW/
//@strvr - https://www.codingninjas.com/studio/problems/aggressive-cows_1082559
public class AggressiveCows {

    public static int aggressiveCows(int []stalls, int k) {
        //    Write your code here.
        return maxMinDistance2(stalls, k);
    }

    //1) Optimal. Using binary search.
    public static int maxMinDistance1(int[] barnDist, int totalCows) {
        return binarySearchForMaxMin(barnDist, totalCows);
    }

    private static int binarySearchForMaxMin(int[] barnDist, int totalCows) {
        Arrays.sort(barnDist);
        int max = barnDist[barnDist.length-1];
        int min = barnDist[0];
        int low = 0;
        int high = max-min;
        int ans = 0;

        while (low <= high) {
            int mid = (low+high)/2;
            if (canPlace(barnDist, mid, totalCows)) {
                ans = mid;
                low = mid+1;
            } else {
                high = mid - 1;
            }
        }

        return ans; //or high;
    }

    //2) Brute. Uses sort and linear search.
    public static int maxMinDistance2(int[] barnDist, int totalCows) {
        Arrays.sort(barnDist);
        int max = barnDist[barnDist.length-1];
        int min = barnDist[0];

        for (int i=1; i<=(max-min); i++) {
            if (canPlace(barnDist, i, totalCows)) continue;
            else return (i-1);
        }
        return 0;
    }

    private static boolean canPlace(int[] barnDist, int dist, int totalCows) {
        int cntCows = 1;
        int lastCowPos = barnDist[0];
        for (int i=1; i<barnDist.length; i++) {
            if (barnDist[i]-lastCowPos >= dist) {
                cntCows += 1;
                lastCowPos = barnDist[i];
            }
        }

        return cntCows >= totalCows;
    }
}
