package swd.recursionbacktracking;

import java.util.*;

//@link - https://leetcode.com/problems/letter-combinations-of-a-phone-number/description/
public class LetterCombinationsOfAPhoneNo {
    public List<String> letterCombinations(String digits) {
        //store all keypad maping in a java map.
        Map<Character, List<String>> keypad = getKeyPad();
        return allPossibleCombinations(digits, keypad, 0, "");
    }

    private List<String> allPossibleCombinations(String digits, Map<Character, List<String>> keypad, int currIndex, String currStr) {

        //If exhausted all digits, means we have a
        //valid combination, therefore, store it in a
        //list and return.
        if (currIndex == digits.length()) {
            List<String> ans = new ArrayList<>();
            if (!currStr.isEmpty()) ans.add(currStr);
            return ans;
        }

        //digit at current index in the digits string.
        Character currDigit = digits.charAt(currIndex);

        //all the nums present for that digit on a keypad.
        List<String> mappedStrings = keypad.get(currDigit);

        List<String> allAns = new ArrayList();

        //find combinations of each alphabet for the current num
        //with each alphabets for subsequent nums in the digits string.
        for (int i = 0; i < mappedStrings.size(); i++) {
            String withCurrLetter = currStr + mappedStrings.get(i);
            List<String> ansWithCurrLetter = allPossibleCombinations(digits, keypad, currIndex+1, withCurrLetter);
            allAns.addAll(ansWithCurrLetter);
        }
        return allAns;
    }

    //Returns a mapping of all digits against the alphabets
    //present on those digit in a typical keypad.
    private Map<Character, List<String>> getKeyPad() {
        Map<Character, List<String>> keypad = new HashMap<>();
        keypad.put('2', Arrays.asList("a", "b", "c"));
        keypad.put('3', Arrays.asList("d", "e", "f"));
        keypad.put('4', Arrays.asList("g", "h", "i"));
        keypad.put('5', Arrays.asList("j", "k", "l"));
        keypad.put('6', Arrays.asList("m", "n", "o"));
        keypad.put('7', Arrays.asList("p", "q", "r", "s"));
        keypad.put('8', Arrays.asList("t", "u", "v"));
        keypad.put('9', Arrays.asList("w", "x", "y", "z"));

        return keypad;
    }
}
