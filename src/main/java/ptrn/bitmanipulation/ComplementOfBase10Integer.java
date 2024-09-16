package ptrn.bitmanipulation;

//@link - https://leetcode.com/problems/complement-of-base-10-integer/description/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/5098761006350336
public class ComplementOfBase10Integer {
    public int bitwiseComplement(int n) {
        return revise2(n);
        //return revise1(n);
        //return og(n);
    }

    //edctv soln
    /**
     * The idea is that taking xor of a num with all 1s effectively
     * flips all the bits in the number.
     */
    private int viaxor(int num) {
        if (num == 0) return 1;

        //trick to calculate the num of bits "required" to represent the num in binary.
        int bitCount = (int)Math.floor((int)(Math.log(num)/Math.log(2))) + 1;
        int allBitsSet = (1<<bitCount)-1; //eg. (1<<3)-1 --> 1000b-1 --> 8-1 --> 7 --> 111
        return num^allBitsSet;
    }

    //my soln.
    private int revise2(int n) {
        if (n==0) return 1;

        int ans = 0;
        int pos = 0;
        while (n > 0) {
            ans = ans | ((1 - n&1) << pos);
            n = n >> 1;
            pos += 1;
        }

        return ans;
    }

    //my soln
    private int revise1(int num) {
        if (num == 0) return 1;

        int pow = 0;
        int cpy = num;
        int cplmnt = 0;
        while (cpy != 0) {
            cplmnt += (Math.pow(2, pow) * (1 - (cpy & 1)));
            pow += 1;
            cpy = cpy >> 1;
        }

        return cplmnt;
    }

    private int og(int n) {
        if (n == 0) return 1;
        /**
         Note that simply doing ~n doesn't work coz,
         all the leading zeros (and the signed) bit will
         also be flipped and we'll get a wrong answer.
         Therefore, we have some masking for the leading bits.
         Not 100% sure why line 12 works though.

         @see - https://stackoverflow.com/questions/47126402/not-operation-on-unsigned-int-in-java
         */
        int mask = (Integer.highestOneBit(n) << 1) - 1;
        return (~n & mask);
    }
}
