package lc_potd;

//@link - https://leetcode.com/problems/add-binary/
public class AddBinary {
    public String addBinary(String a, String b) {
        int lena = a.length();
        int lenb = b.length();

        //ensure a is always the larger string (just to make it easier)
        if (lena < lenb) {
            String temp = a;
            a = b;
            b = temp;
        }

        //pointers to the end of both string.
        int carry = 0;
        int p1 = a.length()-1; //a
        int p2 = b.length()-1; //b

        final String ZERO = "0";
        final String ONE = "1";
        StringBuffer sb = new StringBuffer();
        while (p2 >= 0) {
            //int at p1 and p2
            int i1 = Integer.parseInt(Character.toString(a.charAt(p1)));
            int i2 = Integer.parseInt(Character.toString(b.charAt(p2)));

            /*
                nums can only be 0 and 1
                so sum of two integers can only be
                0, 1 or 2.
            */
            if (i1+i2 == 2) { //means both are 1
                if (carry == 1) { //if curr carry also one then sum of 1 1 and 1 will be 1 with new carry 1.
                    sb.append(ONE);
                } else {
                    sb.append(ZERO); //if curr carry zero then sum of 1 1 and 0 will be 0 with new carry 1.
                    carry = 1;
                }
            } else if (i1+i2 == 1) { //means one of the two is 1
                if (carry == 1) {
                    sb.append(ZERO); //sum 1 0 1 ==> 0 with carry 1
                } else {
                    sb.append(ONE); //sum 1 0 0 ==> 1 with carry 0.
                }
            } else { //both 0.
                if (carry == 1) {
                    sb.append(ONE); //sum 0 0 1 ==> with carry 0.
                    carry = 0;
                } else {
                    sb.append(ZERO); //sum 0 0 0 ==> with carry 0.
                }
            }
            p2 -= 1;
            p1 -= 1;
        }

        //if a was not the same length as b
        //it is defnitely larger since we ensured that at top.
        //now we just need to append the remaning bits in a while
        //taking into consideration the carry from previous summation.
        while (p1 >= 0) {
            int i1 = Integer.parseInt(Character.toString(a.charAt(p1)));
            if (i1+carry == 2) {
                sb.append(ZERO);
                carry = 1;
            } else if (i1+carry == 1) {
                sb.append(ONE);
                carry = 0;
            } else {
                sb.append(ZERO);
                carry = 0;
            }
            p1 -= 1;
        }

        //if in the end, carry still remaining,
        //append to the output.
        if (carry == 1) sb.append(carry+"");
        return sb.reverse().toString(); //reverse the string for final answer.
    }
}
