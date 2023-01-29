package swd.hashmap;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

//@link - https://leetcode.com/problems/check-if-array-pairs-are-divisible-by-k/
public class CheckIfArrayPairsDivisibleByK {
    /**SWD Soln**/
    public boolean canArrange(int[] arr, int k) {
        Map<Integer, Integer> elsRemainderMap = new HashMap<>();
        for (int el : arr) {
            int remainder = el % k;
            if (el < 0) remainder = ((el % k) + k) % k;
            //Remainders should be in pairs.
            elsRemainderMap.put(remainder, elsRemainderMap.getOrDefault(remainder, 0) + 1);
        }
        /*
            For +ve nos. `a` and `b`, remainder of a/b = a%b.
            If one of the two nos. is -ve, remainder of -a/b = ((a%b)+b)%b.

            In a pair (a, b) which should be divisible by k, if a%k = X,
            then b should be should be such that b%k = mk - X, where m is a integer.
        */

        //When divinding by k, remainders can be from 0, k-1
        if ((elsRemainderMap.get(0)!=null)&& !(elsRemainderMap.get(0)%2==0)) return false;

        for (int i=1; i<=k/2; i++) {
            if (!(Objects.equals(elsRemainderMap.get(i), elsRemainderMap.get(k - i)))) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        CheckIfArrayPairsDivisibleByK checkIfArrayPairsDivisibleByK = new CheckIfArrayPairsDivisibleByK();
        System.out.println(checkIfArrayPairsDivisibleByK.canArrange(new int[]{-1,1,-2,2,-3,3,-4,4}, 3));
    }
}
