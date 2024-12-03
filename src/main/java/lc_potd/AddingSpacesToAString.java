package lc_potd;

//@link - https://leetcode.com/problems/adding-spaces-to-a-string/
public class AddingSpacesToAString {
    public String addSpaces(String s, int[] spaces) {
        // return ap1(s, spaces);
        return ap2(s, spaces);

    }

    private String ap2(String s, int[] spaces) {
        StringBuilder sb = new StringBuilder("");
        int spI = 0;
        for (int i=0; i<s.length(); i++) {
            if (spI < spaces.length && i == spaces[spI]) {
                sb.append(" ");
                spI += 1;
            }
            sb.append(s.charAt(i));
        }

        return sb.toString();
    }

    private String ap1(String s, int[] spaces) {
        StringBuilder sb = new StringBuilder(s);
        int inc = 0;
        for (int sp: spaces) {
            sb.insert(sp+inc, ' ');
            inc += 1;
        }

        return sb.toString();
    }
}
