package lc_potd;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//@link - https://leetcode.com/problems/finding-3-digit-even-numbers/description/
//@check - https://www.youtube.com/watch?v=og7s7dhWRbE&t=1103s&ab_channel=codestorywithMIK
public class Finding3DigitEvenNumbers {
    public int[] findEvenNumbers(int[] digits) {
        // return brute(digits);
        return optimal(digits);
    }

    /*
        TC: O(1), S: O(1)
    */
    private int[] optimal(int[] digits) {
        int[] freq = new int[10];
        for (int digit: digits) {
            freq[digit] += 1;
        }

        List<Integer> nums = new ArrayList<>();

        //Options for first digit  -> 1 to 9 (0 not allowed)
        //Options for second digit -> 0 to 9
        //Options for third digit  -> 0, 2, 4, 6, 8 (coz even)

        for (int i=1; i<=9; i++) {
            if (freq[i] == 0) continue;
            freq[i] -= 1;

            for (int j=0; j<=9; j++) {
                if (freq[j] == 0) continue;
                freq[j] -= 1;

                for (int k=0; k<=8; k += 2) {
                    if (freq[k] == 0) continue;
                    freq[k] -= 1;
                    nums.add(i*100 + j*10 + k);
                    freq[k] += 1;
                }

                freq[j] += 1;
            }

            freq[i] += 1;
        }

        //NOTE: Interesting that no sorting is needed in this approach,
        //the result will already be sorted.
        return nums.stream().mapToInt(el -> el).toArray();
    }

    private int[] brute(int[] digits) {
        int n = digits.length;

        /*
            Brute force to generate
            3 length perms.
            TC is O(n^3)
        */
        Set<Integer> nums = new HashSet<>();
        for (int i=0; i<n; i++) {
            if (digits[i] == 0) continue;
            for (int j=0; j<n; j++) {
                if (j == i) continue;
                for (int k=0; k<n; k++) {
                    if (k != i && k != j && digits[k]%2 == 0) {
                        nums.add(digits[i]*100 + digits[j]*10 + digits[k]);
                    }
                }
            }
        }

        return nums.stream().sorted().mapToInt(i -> i).toArray();
    }
}
