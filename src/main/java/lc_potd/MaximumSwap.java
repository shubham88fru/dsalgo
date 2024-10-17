package lc_potd;

import java.util.Arrays;

//@link - https://leetcode.com/problems/maximum-swap/
public class MaximumSwap {
    public int maximumSwap(int num) {
//        return mysol(num);
        return revise(num);
    }

    private int mysol(int num) {
        /**
         My soln, but isn't optimal.
         Maybe try to find a optimal soln on next encounter.
         */
        StringBuffer ns = new StringBuffer(String.valueOf(num));
        int max = num;
        for (int i=0; i<ns.length(); i++) {
            for (int j=i+1; j<ns.length(); j++) {
                //////optimization
                if (
                        Character.getNumericValue(ns.charAt(j)) < Character.getNumericValue(ns.charAt(i))

                ) continue;
                ////////
                char temp = ns.charAt(i);
                ns.setCharAt(i, ns.charAt(j));
                ns.setCharAt(j, temp);

                if (Integer.parseInt(ns.toString()) > max) {
                    max = Integer.parseInt(ns.toString());
                }

                ns.setCharAt(j, ns.charAt(i));
                ns.setCharAt(i, temp);
            }
        }
        return max;
    }

    private int revise(int num) {
        StringBuffer sb = new StringBuffer(String.valueOf(num));
        Pair55[] arr = new Pair55[sb.length()];

        /*
            Find the largest possible number.
        */
        for (int i=0; i<arr.length; i++) {
            arr[i] = new Pair55(sb.charAt(i), i);
        }
        Arrays.sort(arr, (p1, p2) -> p2.ch - p1.ch);

        /*
            Find a suitable place to swap.
        */
        for (int i=0; i<sb.length(); i++) {
            char ch1 = sb.charAt(i);
            char ch2 = arr[i].ch;
            int sourceIdx = i;

            if (Character.getNumericValue(ch1) < Character.getNumericValue(ch2)) {

                /*
                    Find the last index to swap.
                */
                while ((i+1 < sb.length()) && (arr[i+1].ch == ch2)) i += 1;

                int targetIdx = arr[i].idx;
                sb.setCharAt(sourceIdx, ch2);
                sb.setCharAt(targetIdx, ch1);

                break;
            }
        }

        return Integer.parseInt(sb.toString());
    }
}

class Pair55 {
    char ch;
    int idx;

    public Pair55(char ch, int idx) {
        this.ch = ch;
        this.idx = idx;
    }

    public String toString() {
        return "Pair55(" + ch + ", " + idx + ")";
    }
}