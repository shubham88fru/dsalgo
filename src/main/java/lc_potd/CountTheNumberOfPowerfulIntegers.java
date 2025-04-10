package lc_potd;


//@link - https://leetcode.com/problems/count-the-number-of-powerful-integers/description/
//@check - https://www.youtube.com/watch?v=DNbvaC-EqyQ&ab_channel=codestorywithMIK
public class CountTheNumberOfPowerfulIntegers {
    public long numberOfPowerfulInt(long start, long finish, int limit, String s) {
        return mikssol(start, finish, limit, s);
    }

    /*
        Coded by me but completely based on mik's explanation.
        straightforward, but a bit sketchy around the edge cases.
    * */
    private long mikssol(long start, long finish, int limit, String s) {
        String startStr = String.valueOf(start-1);
        String finishStr = String.valueOf(finish);

        return solve(finishStr,s,limit) - solve(startStr,s,limit);
    }

    private long solve(String str, String s, int limit) {
        if (str.length() < s.length()) return 0l;

        long count = 0;
        String trailString = str.substring(str.length()-s.length(), str.length());
        int remainL = str.length() - s.length();
        for (int i=0; i<remainL; i++) {
            int digit = str.charAt(i) - '0';
            if (digit <= limit) count += (digit * Math.pow(limit+1, remainL-i-1));
            else {
                count += Math.pow(limit+1, remainL-i);
                return count;
            }
        }

        if (Long.parseLong(trailString) >= Long.parseLong(s)) count += 1;

        return count;
    }
}
