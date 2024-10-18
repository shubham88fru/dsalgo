package ptrn.matrices;

import java.util.ArrayList;
import java.util.List;

//@link - https://leetcode.com/problems/multiply-strings/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/5816048567451648
public class MultiplyStrings {
    /*
        My soln. There must be a better way to solve this question.
        Check mik/yt on next encounter.
    */
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) return "0";

        if (num1.length() < num2.length()) {
            String temp = num1;
            num1 = num2;
            num2 = temp;
        }

        int p2 = num2.length()-1;
        List<List<Integer>>  pdts = new ArrayList<>();
        int row = 0;
        int shift = 0;
        while ((p2 >= 0)) {
            int p1 = num1.length()-1;
            int mul1 = Character.getNumericValue(num2.charAt(p2));
            int carry = 0;
            pdts.add(new ArrayList<>());
            for (int i=0 ;i<(num2.length()+num1.length()); i++) pdts.get(row).add(0);
            int idx = 0;
            while (p1 >= 0) {
                int mul2 = Character.getNumericValue(num1.charAt(p1));
                int pdt = (mul1 * mul2) + carry;
                if (pdt >= 10) {
                    carry = pdt/10;
                    pdt %= 10;
                } else carry = 0;
                pdts.get(row).set(row+idx, pdt);
                idx += 1;

                p1 -= 1;
            }
            if (carry != 0) pdts.get(row).set(row+idx, carry);
            shift += 1;
            row += 1;
            p2 -= 1;
        }

        int carry = 0;
        StringBuffer ans = new StringBuffer();
        for (int j=0; j<pdts.get(0).size(); j++) {

            int sum = 0;
            for (int i=0; i<pdts.size(); i++) {
                sum += pdts.get(i).get(j);
            }
            sum += carry;
            if (sum >= 10) {
                carry = sum/10;
                ans.append(sum%10);
                // carry = 1;
            } else {
                ans.append(sum);
                carry = 0;
            }
        }

        ans.reverse();
        if (ans.charAt(0) == '0') return ans.substring(1).toString();
        return ans.toString();

    }
}
