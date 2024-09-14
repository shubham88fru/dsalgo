package ptrn.bitmanipulation;

//@link - https://leetcode.com/problems/find-the-difference/description/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/5419858583093248
public class FindTheDifference {
    public char findTheDifference(String s, String t) {
        return optimal(s, t);
        // return suboptimal(s, t);
    }

    private char optimal(String s, String t) {
        //s and t in a single string.
        String comb = s+t;

        //calculate xor of the chars in the
        //combined string. All common chars will
        //cancel out. Only the unique char will remain.
        int xor = 0;
        for (char ch: comb.toCharArray()) {
            xor ^= (int)ch;
        }

        //return the unique char.
        return (char)xor;
    }

    private char suboptimal(String s, String t) {
        int[] sf = new int[26];
        int[] tf = new int[26];
        for (char ch: s.toCharArray()) {
            sf[ch-'a'] += 1;
        }

        for (char ch: t.toCharArray()) {
            tf[ch-'a'] += 1;
        }

        for (int i=0; i<26; i++) {
            if (sf[i] != tf[i]) return (char)(i+'a');
        }

        return '\0';
    }
}
