package ptrn.knowingwhattotrack;

import java.util.HashMap;
import java.util.Map;

//@link - https://www.naukri.com/code360/problems/palindrome-permutation_1171180
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6266438110412800
public class PalindromePermutation {
    public static boolean palindromeString(String s) {
        Map<Character, Integer> freq = new HashMap<>();
        for (char ch: s.toCharArray()) {
            freq.put(ch, freq.getOrDefault(ch, 0)+1);
        }

        int oddCount = 0;
        for (Map.Entry<Character, Integer> entry: freq.entrySet()) {
            if (entry.getValue()%2 != 0) {
                oddCount += 1;
                if (oddCount > 1) return false;
            }
        }

        return true;
    }
}
