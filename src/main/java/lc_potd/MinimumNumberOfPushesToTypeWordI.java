package lc_potd;

//@link - https://leetcode.com/problems/minimum-number-of-pushes-to-type-word-i/?
public class MinimumNumberOfPushesToTypeWordI {
    public int minimumPushes(String word) {
        return pass1(word);
    }

    /*
        My sol. This problem is easy
        because of the constraint that
        chars are non-repeating.
    */
    private int pass1(String word) {
        int n = word.length();
        int pushes = 0;

        for (int i=0; i<n; i++) {
            if (i <= 7) pushes += 1;
            else if (i > 7 && i <= 15) pushes += 2;
            else if (i > 15 && i <= 23) pushes += 3;
            else pushes += 4;
        }

        return pushes;
    }
}
