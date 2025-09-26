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
     * My revision soln.
     * Approach is exactly the same and I blind coded
     * it, but for some reason runs slower than the
     * edctv OG soln above.
     */
    //Type conv to Long is imp. Because input can be Integer.MIN_VALUE
    //and it will blow up when doing Math.abs on it.
    private String revise(long numerator, long denominator) {
        if (numerator == 0) return "0";
        if (numerator%denominator == 0) return String.valueOf(numerator/denominator);

        boolean neg = false;
        int index = 1;
        String frac = ".";
        if (numerator * denominator < 0) {
            neg = true;
        }

        numerator = Math.abs(numerator);
        denominator = Math.abs(denominator);

        StringBuilder fraction = new StringBuilder();
        String real = "0";

        Map<Long, Integer> remainders = new HashMap<>();
        fraction.append(frac);
        if (numerator < denominator) {
            remainders.put(numerator, index);
        } else {
            real = String.valueOf(numerator/denominator);
            numerator %= denominator;
            remainders.put(numerator, index);
        }

        while (numerator != 0) {
            if (numerator >= denominator) {
                fraction.append(String.valueOf(numerator/denominator));
                numerator %= denominator;
            } else if (numerator*10 >= denominator) {
                fraction.append(String.valueOf((numerator*10)/denominator));
                numerator = (numerator*10)%denominator;
            } else {
                fraction.append("0");
                numerator *= 10;
            }

            index += 1;
            if (remainders.containsKey(numerator)) {

                int si = remainders.get(numerator);
                fraction.insert(si, '(');
                fraction.append(')');
                break;
            }
            remainders.put(numerator, index);
        }

        if (neg) return "-" + real + fraction.toString();

        return real + fraction.toString();
    }
}
