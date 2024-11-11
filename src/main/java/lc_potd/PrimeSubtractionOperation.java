package lc_potd;

//@link - https://leetcode.com/problems/prime-subtraction-operation/
//@check - https://www.youtube.com/watch?v=jsk0Ajqhu1Y&ab_channel=codestorywithMIK
public class PrimeSubtractionOperation {
    public boolean primeSubOperation(int[] nums) {
        return mysol(nums);
    }

    /*
        Following is completely my soln.
        Mik's soln was also on same lines, but he kinda relied
        on the small constraints and didn't use binary search.

    */
    private boolean mysol(int[] nums) {

        //Traverse the array from right to left (instead of left to right)
        for (int i=nums.length-2; i >= 0; i--) {
            if (nums[i] >= nums[i+1]) {
                //if we find a num that is greater than the right,
                //we need to fix it. Therefore, we calculate the min
                //and max that can be subtracted from the current num, so
                //that it becomes smaller than the next num.
                int minSub = (nums[i]-nums[i+1] + 1);
                int maxSub = nums[i] - 1;

                //then we perform a binary search to find the "smallest" prime
                //that we can find in the above range. Note that we find the smallest
                //prime because, we want to reduce the curr num such that it just becomes
                //smaller than its right num, however, at the same time not reducing so much
                //that the there's no way left for the num on left to be reduced.
                int primeSub = binarySearchForPrime(minSub, maxSub);
                if (primeSub == Integer.MAX_VALUE) return false;
                nums[i] -= primeSub;
            }
        }

        return true;
    }

    /*
        Binary search to find the smallest prime number in the range.
        Note, how this also uses recursion with binary search!
    */
    private int binarySearchForPrime(int minSub, int maxSub) {
        int l = minSub;
        int r = maxSub;
        if (l > r) return Integer.MAX_VALUE;

        if (isPrime(minSub)) return minSub;

        int min = Integer.MAX_VALUE;
        while (l < r) {
            int mid = l + (r-l)/2;
            //If mid is prime, we record it and
            //we definitely know that we don't the right half now.
            if (isPrime(mid)) {
                min = mid;
                r = mid -1;
            } else {
                //however, if mid isn't prime, we don't know which side
                //the smallest prime would be. Therefore, we check both sides
                //take the smallest of the prime found on either side.
                int left = binarySearchForPrime(minSub, mid);
                int right = binarySearchForPrime(mid+1, maxSub);
                return Math.min(left, right);
            }
        }

        return min;
    }

    public boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        if (number <= 3) {
            return true;
        }
        if (number % 2 == 0 || number % 3 == 0) {
            return false;
        }
        for (int i = 5; i * i <= number; i += 6) {
            if (number % i == 0 || number % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }
}
