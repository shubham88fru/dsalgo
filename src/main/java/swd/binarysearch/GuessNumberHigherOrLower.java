package swd.binarysearch;

//@link - https://leetcode.com/problems/guess-number-higher-or-lower/description/
public class GuessNumberHigherOrLower extends GuessGame {
    public int guessNumber(int n) {
        return binarySearch(n);
    }

    private int binarySearch(int n) {
        int start = 1;
        int end = n;

        while (start <= end) {
            int mid = (start + (end-start)/2);

            //if found, return.
            if (guess(mid) == 0) return mid;
            //else if guessed num greater, actual no.
            //must lie before guessed no. Else, otherwise.
            if (guess(mid) == -1) end = mid - 1;
            else start = mid + 1;
        }

        return -1; //per constraints, shouldn't reach here ever.
    }
}

//Dummy. Actual implementation on leetcode.
class GuessGame {
    public int guess(int num) {
        return 0;
    }
}
