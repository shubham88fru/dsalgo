package strvr.strings;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/roman-to-integer/description/
//@strvr -
public class RomanToInteger {
    public int romanToInt(String s) {
        //Initialize a map of roman values against integer representations.
        Map<String, Integer> map = getMap();

        int res = 0;
        for (int i=0; i<s.length(); i++) {
            String twoChars = "";
            //First try to see, if two successive chars from current index
            //for a valid roman string.
            if (i < s.length()-1) {
                twoChars = s.substring(i, i+2);
                if (map.containsKey(twoChars)) {
                    res += map.get(twoChars);
                    i++;
                    continue;
                }
            }

            //If not, then go once char at a time.
            String oneChar = s.substring(i, i+1);
            res += map.get(oneChar);
        }
        return res;
    }

    private Map<String, Integer> getMap() {
        Map<String, Integer> map = new HashMap<>();

        map.put("I", 1);
        map.put("V", 5);
        map.put("X", 10);
        map.put("L", 50);
        map.put("C", 100);
        map.put("D", 500);
        map.put("M", 1000);
        map.put("IV", 4);
        map.put("IX", 9);
        map.put("XL", 40);
        map.put("XC", 90);
        map.put("CD", 400);
        map.put("CM", 900);

        return map;
    }
}
