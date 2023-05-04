package swd.binarysearch;

//@link - https://practice.geeksforgeeks.org/problems/the-painters-partition-problem1535/1
public class PainterPartitionProblemII {
    long minTime(int[] arr,int n,int k){
        //The problem is exactly same as allocate min pages problem,
        //the only diff is that in the former, each student must get a book
        //while in this problem, a painter can sit and chill even if he has no
        //board to paint. So just need to remove the edge case where we do
        //if (n < k) return -1;
        return binarySearch(arr, n, k);
    }

    private long binarySearch(int[] A, int N, int M) {
        long start = 0;
        long end = sumArray(A); //sum of all board lengths will be the max possible ans (when only 1 painter)

        long answer = -1;

        while (start <= end) {
            long mid = (start + (end-start)/2);

            if(isPossible(A, M, mid)) {
                answer = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return answer;
    }

    private boolean isPossible(int[] boards, long painters, long limit) {
        long currentSum = 0;
        long currentPainter = 1;

        for (int currentBoardLength: boards) {
            if (currentBoardLength > limit) return false;

            if (currentSum + currentBoardLength <= limit) {
                currentSum += currentBoardLength;
            } else {
                currentPainter += 1;
                currentSum = currentBoardLength;

                if (currentPainter > painters) return false;
            }
        }
        return true;
    }

    private long sumArray(int[] A) {
        long sum = 0;

        for (long num: A) {
            sum += num;
        }

        return sum;
    }
}
