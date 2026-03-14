package lc_potd;

//@link - https://www.geeksforgeeks.org/problems/run-length-encoding/1
public class RunLengthEncoding {
    public static String encode(String s) {
        // code here
        return rle(s);
    }

    private static String rle(String s) {
        int n = s.length();

        if (n == 1) return s+"1";

        int l=0, r=0, count=0;
        StringBuilder sb = new StringBuilder();
        while (l < n) {
            while (r < n && s.charAt(r) == s.charAt(l)) {
                count += 1;
                r += 1;
            }

            sb.append(s.charAt(l));
            sb.append(count);

            count = 0;
            l = r;
        }

        return sb.toString();
    }

    private static String revise(String s) {
        int n = s.length();
        int l=0, r=0, count=1;
        StringBuilder sb = new StringBuilder();

        while (l < n) {
            while (r < n-1 && s.charAt(r) == s.charAt(r+1)) {
                r += 1;
                count += 1;
            }

            sb.append(s.charAt(l));
            sb.append(count);

            r += 1;
            l = r;
            count = 1;
        }

        return sb.toString();
    }
}
