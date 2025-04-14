package lc_potd;

//@link - https://leetcode.com/problems/count-good-triplets/description/
public class CountGoodTriplets {
    public int countGoodTriplets(int[] arr, int a, int b, int c) {
        return brute(arr, a, b, c);
    }

    /*
    * There ain't a lot of better solns.
    * Mik also had the same solution, with a slight
    * optimization.
    * */
    private int brute(int[] arr, int a, int b, int c) {
        int n = arr.length;

        int count = 0;
        for (int i=0; i<n; i++) {
            for (int j=i+1; j<n; j++) {
                for (int k=j+1; k<n; k++) {
                    if (Math.abs(arr[i] - arr[j]) <= a
                            && Math.abs(arr[j] - arr[k]) <= b
                            && Math.abs(arr[i] - arr[k]) <= c
                    ) count += 1;
                }
            }
        }

        return count;
    }
}
