package lc_potd;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

//@link - https://leetcode.com/problems/coupon-code-validator/?
public class CouponCodeValidator {
    Set<String> bls = Set.of("electronics", "grocery", "pharmacy", "restaurant");

    public List<String> validateCoupons(String[] code, String[] businessLine, boolean[] isActive) {
        return pass1(code, businessLine, isActive);
    }

    private List<String> pass1(String[] code, String[] businessLine, boolean[] isActive) {
        int n = code.length;

        List<Data123> data = new ArrayList<>();

        for (int i=0; i<n; i++) {
            if (!isActive[i] || code[i] == null || code[i].isEmpty()
                    || businessLine[i] == null || !bls.contains(businessLine[i])) continue;

            if (check(code[i], businessLine[i], isActive[i])) data.add(new Data123(code[i], businessLine[i]));
        }

        Comparator<Data123> cmp1 = (d1, d2) -> d1.bl.compareTo(d2.bl);
        Comparator<Data123> cmp2 = (d1, d2) -> d1.code.compareTo(d2.code);
        data.sort(cmp1.thenComparing(cmp2));

        return data.stream().map(d -> d.code).toList();
    }

    private boolean check(String code, String businessLine, boolean isActive) {

        for (int i=0; i<code.length(); i++) {
            char ch = code.charAt(i);
            int ich = (int)ch;
            if (ch != '_' && !Character.isDigit(ch) &&
                    (ich < 0 || (ich > 9 && ich < 65) || (ich > 90 && ich < 96) || (ich > 122))) return false;
        }

        return true;
    }
}

class Data123 {
    String code;
    String bl;

    public Data123(String code, String bl) {
        this.code = code;
        this.bl = bl;
    }

    public String toString() {
        return code + " " + bl;
    }
}