package ptrn.hashmap;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/fraction-to-recurring-decimal/description/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/4682539618009088
public class FractionToRecurringDecimal {
    public String fractionToDecimal(int numerator, int denominator) {
        return divide(numerator, denominator);
    }

    private String divide(long num, long den) {

        //no matter what, ans will be 0.
        if (num == 0) return "0";

        boolean neg = false;

        //Sign of the final answer.
        if (num < 0 && den > 0) neg = true;
        if (num > 0 && den < 0) neg = true;

        //Work with positives. will adjust the sign at the end.
        num = Math.abs(num);
        den = Math.abs(den);

        //Direct division of integers will
        //give the portion before the decimal.
        StringBuffer sb = new StringBuffer();
        sb.append(num/den);

        //The remainder is what we'll perform
        //step by step divsion on to get the portion
        //after the decimal.
        num = num%den;

        //if remainder is 0, then it is a perfect
        //integer, and num/den would already have
        //given us the answer. Just adjust the sign
        //and return the answer.
        if (neg) sb.insert(0, '-');
        if (num == 0) {
            return sb.toString();
        }

        //otherwise, the answer is going to be
        //a decimal. Add a decimal and multiply
        //the num (remiander) by 10 as we'd normally
        //do when adding a decimal during division.
        sb.append('.');
        num = num*10;

        /**
         The idea from here on is that we'll simulate a
         step-by-step division. At each step, we'll keep
         track of the remainder and check if we have already
         seen the remainder, if so, then all subsequent steps
         will only be a repeatition and so, we can break and just
         return the answer by adding proper parens.
         */

        Map<Long, Integer> st = new HashMap<>();
        boolean repeating = false;
        int index = sb.length();
        int parenStart = -1; //to keep track of the first occurrence of a particular remainder.
        while (num != 0) {
            if(st.containsKey(num)) {
                repeating = true;
                parenStart = st.get(num);
                break;
            }
            st.put(num, index++);

            if (num < den) {
                sb.append('0');
                num = num*10; //typical division step (post decimal) when num is smaller than den.
            } else {
                sb.append(num/den);
                num = (num%den)*10; //typical division step (post decimal) when num is greater than den.
            }
        }

        //add parens if repeating.
        if (repeating) {
            sb.insert(parenStart, '(');
            sb.append(')');
        }

        return sb.toString();
    }

    /**
     * My revision code coming soon..
     */
}
