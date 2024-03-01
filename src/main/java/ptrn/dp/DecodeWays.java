package ptrn.dp;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/decode-ways/description/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/5941105247649792
public class DecodeWays {
    private static final Map<String, String> map = new HashMap<>();
    static {
        map.put("1", "A");
        map.put("2", "B");
        map.put("3", "C");
        map.put("4", "D");
        map.put("5", "E");
        map.put("6", "F");
        map.put("7", "G");
        map.put("8", "H");
        map.put("9", "I");
        map.put("10", "J");
        map.put("11", "K");
        map.put("12", "L");
        map.put("13", "M");
        map.put("14", "N");
        map.put("15", "O");
        map.put("16", "P");
        map.put("17", "Q");
        map.put("18", "R");
        map.put("19", "S");
        map.put("20", "T");
        map.put("21", "U");
        map.put("22", "V");
        map.put("23", "W");
        map.put("24", "X");
        map.put("25", "Y");
        map.put("26", "Z");
        map.put("", ""); //to handle first call to recursive method.

    }
    public int numDecodings(String s) {
        return decode(s, "", 0, new HashMap<>());
    }

    //1) My top-down dp soln.
    private int decode(String s, String picked, int curr, Map<String, Integer> cache) {
        if (!map.containsKey(picked)) return 0;
        if (curr >= s.length()) return 1;

        String key = picked + "_" + curr;
        if (cache.containsKey(key)) return cache.get(key);

        //choose one num.
        int pickOne = 0;
        pickOne += decode(s, s.substring(curr, curr+1), curr+1, cache);

        //choose two nums.
        int pickTwo = 0;
        if (curr < s.length()-1)
            pickTwo += decode(s, s.substring(curr, curr+2), curr+2, cache);

        cache.put(key, (pickOne+pickTwo));
        return cache.get(key);
    }

    //2) Check edctv for bottom-up soln.
}
