package lc_potd;

//@link - https://leetcode.com/problems/count-and-say/
public class CountAndSay {
    public String countAndSay(int n) {
        return pass1(n);
    }

    /*
        My soln. Mik had the same solution.
     */
    private String pass1(int n) {
        return recursive(n);
    }

    private String recursive(int n) {
        if (n == 1) return "1";
        if (n == 2) return "11";

        String rlePrev = recursive(n-1);
        StringBuffer sb = new StringBuffer();

        int i = 0;
        int len = rlePrev.length();
        while (i<len) {
            int count = 0;
            char dig = rlePrev.charAt(i);
            while (i < len && rlePrev.charAt(i) == dig) {
                count += 1;
                i += 1;
            }
            sb.append(count).append(dig);
        }

        return sb.toString();
    }
}
