package lc_potd;

//@link - https://leetcode.com/problems/string-compression-iii/
public class StringCompressionIII {
    public String compressedString(String word) {
        int l = 0;
        int r = 0;
        StringBuffer sb = new StringBuffer();
        while (r < word.length()) {
            if (r - l + 1 <= 9 && (word.charAt(r) == word.charAt(l))) {
                r += 1;
            } else {
                sb
                .append(r - l)
                .append(word.charAt(l));
                l = r;
            }
        }

        sb
        .append(r - l)
        .append(word.charAt(l));

        return sb.toString();
    }
}
