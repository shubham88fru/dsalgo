package lc_potd;

//@link - https://leetcode.com/problems/divide-a-string-into-groups-of-size-k/description/
public class DivideAStringIntoGroupsOfSizeK {
    public String[] divideString(String s, int k, char fill) {
        return revise(s, k, fill);
    }

    private String[] revise(String s, int k, char fill) {
        int n = s.length();

        String[] ans = new String[(int)Math.ceil(n*1.0/k)];

        int i = 0;
        int idx = 0;
        while (i < n) {

            if (i+k <= n) {
                ans[idx] = s.substring(i, i+k);
                idx += 1;
                i += k;
            } else {
                ans[idx] = s.substring(i, n);
                break;
            }
        }

        if (ans[ans.length-1].length() != k) {
            StringBuilder sb = new StringBuilder(ans[ans.length-1]);
            while (sb.length() != k) sb.append(fill);
            ans[ans.length-1] = sb.toString();
        }

        return ans;

    }
}
