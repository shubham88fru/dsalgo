package lc_potd;

import java.util.Map;

//@link - https://leetcode.com/problems/find-the-punishment-number-of-an-integer/description/
//@check - https://www.youtube.com/watch?v=SmnRAvkkohA&t=2494s&ab_channel=codestorywithMIK
public class FindThePunishmentNumberOfAnInteger {
    public int punishmentNumber(int n) {
        return pass1(n);
    }

    //My soln.
    private int pass1(int n) {

        int sm = 0;
        for (int i=1; i<=n; i++) {
            if (valid(String.valueOf(i*i), 0, i)) {
                // if (validWithMemo(String.valueOf(i*i), 0, i, new HashMap<>())) {
                sm += (i*i);
            }
        }

        return sm;
    }

    private boolean valid(String num, int sum, int target) {
        int n = num.length();

        //optimization - if at any point sum becomes larger than
        //than the target, no point checking further.
        if (sum > target) return false;

        if (sum == target && n == 0) return true;

        for (int i=0; i<n; i++) {
            int part = Integer.parseInt(num.substring(0, i+1));
            if (valid(num.substring(i+1, n), part+sum, target)) {
                return true;
            }
        }

        return false;
    }

    //2) Coded by me but mik indicated the repeating
    // subproblem. Performance wise, its even poorer
    // than the soln without memo. Possibly because of
    // the string key.
    private boolean validWithMemo(String num, int sum, int target, Map<String, Boolean> memo) {
        int n = num.length();
        if (sum == target && n == 0) return true;

        String key = num + "_" + sum;
        if (memo.containsKey(key)) return memo.get(key);

        for (int i=0; i<n; i++) {
            int part = Integer.parseInt(num.substring(0, i+1));
            if (validWithMemo(num.substring(i+1, n), part+sum, target, memo)) {
                return true;
            }
        }

        memo.put(key, false);
        return false;
    }
}
