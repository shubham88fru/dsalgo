package strvr.arrays2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.LongStream;

//@link - https://takeuforward.org/data-structure/find-the-repeating-and-missing-numbers/
//@strvr - https://www.interviewbit.com/problems/repeat-and-missing-number-array/
public class RepeatingAndMissingNumberInArray {
    public ArrayList<Integer> repeatedNumber(final List<Integer> A) {
        //return findRepeatingAndMissingBetter(A);
        return findRepeatingAndMissingOptimal1(A);
    }

    //1) Optimal solution1: T: O(N), S: O(1)
    //Using basic math.
    //Need to be careful with overflow since we're finding squares.
    //so use long instead of int.
    private ArrayList<Integer> findRepeatingAndMissingOptimal1(final List<Integer> A) {
        //soln needs squaring and all, so taking long
        //to avoid overflow problem.
        long x = -1l; //denotes repeating num.
        long y = -1l; //denotes missing num.

        int n = A.size();

        long s1 = sum(A);
        long s2 = sumSquares(A);

        long s1n = sumFirstN(n);
        long s2n = sumSquaresOfFirstN(n);

        //x-y = s1-s1n ----- 1
        //x^2 - y^2 = s2-s2n --> x + y = (s2-s2n)/(s1-s1n) ----- 2
        //x = (equation 1 + equation 2) / 2
        //y = x - (s1 - s1n)
        x = ((s1-s1n) + ((s2-s2n)/(s1-s1n)))/2l;
        y = x - (s1-s1n);

        ArrayList<Integer> ans = new ArrayList<>();
        ans.add((int) x);
        ans.add((int) y);

        return ans;

    }

    private long sum(final List<Integer> A) {
        return A.stream().mapToLong(el -> el).sum();
    }

    private long sumSquares(final List<Integer> A) {
        return A.stream().mapToLong(el -> (long)el * (long)el).sum();
    }

    private long sumFirstN(int n) {
        return LongStream.rangeClosed(1, n).map(el -> el).sum();
    }

    private long sumSquaresOfFirstN(int n) {
        return LongStream.rangeClosed(1, n).map(el -> (long)el * (long)el).sum();
    }

    //2) Better approach: T: O(N), S: O(N)
    private ArrayList<Integer> findRepeatingAndMissingBetter(final List<Integer> A) {
        ArrayList<Integer> ans = new ArrayList<>();

        Integer[] ansArr = new Integer[2];
        Arrays.fill(ansArr, -1);

        //will store the freq of each element of give list.
        //we need an array of size = list size + 1 to be able
        //to represent the largest element through an index in the
        //array.
        int[] hash = new int[A.size()+1];

        for (int el: A) {
            hash[el] += 1;
        }

        //Iterate from index 1 (not 0, because nums are from 1 to n, so
        //0 is always going to have a freq of 0)
        for (int i=1; i<hash.length; i++) {
            if (hash[i] == 2) ansArr[0] = i; //appears two times.
            else if (hash[i] == 0) ansArr[1] = i; //appears once.

            if (ansArr[0] != -1 && ansArr[1] != -1) break;
        }

        ArrayList<Integer> al = new ArrayList<Integer>();
        Collections.addAll(al, ansArr);
        return al;
    }

    //3) Brute force: T: O(N^2), S: O(1)
    //for each num in range 1 to n, iterate through
    //the array and check the frequency of that num.
}
