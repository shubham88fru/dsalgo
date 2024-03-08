package ptrn.toposort;

//@link - https://leetcode.com/problems/verifying-an-alien-dictionary/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6510682163118080
public class VerifyingAnAlienDictionary {
    public boolean isAlienSorted(String[] words, String order) {
        int[] locations = new int[26];
        for (int i=0; i < order.length(); i++) {
            locations[order.charAt(i) - 'a'] = i;
        }

        for (int i=0; i < words.length-1; i++) {
            String curr = words[i];
            String next = words[i+1];
            int l1 = curr.length();
            int l2 = next.length();
            int p1 = 0;
            int p2 = 0;

            while (p1 < l1 && p2 < l2) {
                char cc = curr.charAt(p1);
                char cn = next.charAt(p2);
                if (locations[cc-'a'] > locations[cn-'a']) return false;
                else if (locations[cc-'a'] < locations[cn-'a']) break;
                else {
                    p1 += 1;
                    p2 += 1;
                }
            }

            //To handle the case -
            /**
             The first three characters "app" match, and the
             second string is shorter (in size.) According to
             lexicographical rules "apple" > "app", because 'l' > '∅',
             where '∅' is defined as the blank character
             which is less than any other character
             */
            if (p2 == l2 && p1 < l1) return false;
        }

        return true;
    }
}
