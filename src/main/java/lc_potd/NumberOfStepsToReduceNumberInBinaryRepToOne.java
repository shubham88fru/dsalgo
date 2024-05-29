package lc_potd;

//@link - https://leetcode.com/problems/number-of-steps-to-reduce-a-number-in-binary-representation-to-one/
public class NumberOfStepsToReduceNumberInBinaryRepToOne {

    //1) My soln. Kind of basic, TC wise not very good.
    public int numSteps(String s) {

        int steps = 0;
        while (!isOne(s)) {
            steps += 1;
            if (isEven(s)) {
                s = divideBy2(s);
            } else {
                s = addOne(s);
            }
        }

        return steps;
    }

    //add one to binary string.
    private String addOne(String s) {
        StringBuffer sb = new StringBuffer();

        sb.append('0'); //we'll add one only when s is odd, i.e. when last bit is one to start.
        char carry = '1'; //so, carry will be 1 to start with.

        //keep adding with carry in mind.
        for (int i=s.length()-2; i>=0; i--) {
            char ch = s.charAt(i);
            if (ch == '1') {
                if (carry == '1') {
                    sb.append('0');
                } else sb.append('1');
            } else {
                sb.append(carry);
                carry = '0';
            }
        }
        if (carry == '1') sb.append('1');
        return sb.reverse().toString();
    }

    //divide by 2 by right shifting.
    private String divideBy2(String s) {
        StringBuffer sb = new StringBuffer();
        char prev = s.charAt(0);
        for (int i=1; i<s.length(); i++) {
            sb.append(prev);
            prev = s.charAt(i);
        }

        return sb.toString();
    }

    //check if binary string is one.
    private boolean isOne(String s) {
        for (int i=0; i<=s.length()-2;i++) {
            if (s.charAt(i) == '1') return false;
        }

        return s.charAt(s.length()-1) == '1';
    }

    //is it even.
    private boolean isEven(String s) {
        return s.charAt(s.length()-1) == '0';
    }
}
