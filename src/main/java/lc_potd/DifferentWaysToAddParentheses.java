package lc_potd;

import java.util.ArrayList;
import java.util.List;

//@link - https://leetcode.com/problems/different-ways-to-add-parentheses/description
//@check - https://www.youtube.com/watch?v=vWW67t_a--8&ab_channel=codestorywithMIK
public class DifferentWaysToAddParentheses {
    public List<Integer> diffWaysToCompute(String expression) {
        return solve(expression);
    }

    private List<Integer> solve(String expression) {
        // List<Integer> st = new ArrayList<>();
        // dp(expression, 0, expression.length(), st);
        // System.out.println(st);
        // return null;

        return dpMik(expression);
    }

    //mik's soln.
    //my soln (commented below) was working but wasn't returning all possible values.
    private List<Integer> dpMik(String expression) {
        // if (expression.length() == 1) return Character.getNumericValue(expression.charAt(0));

        List<Integer> res = new ArrayList<>();
        for (int i=0; i<expression.length(); i++) {
            char ch = expression.charAt(i);
            if (ch == '+' || ch == '-' || ch == '*') {
                List<Integer> left = dpMik(expression.substring(0, i));
                List<Integer> right = dpMik(expression.substring(i+1));


                for (int x: left) {
                    for (int y: right) {
                        if (ch == '+') {
                            res.add(x+y);
                        } else if (ch == '-') {
                            res.add(x-y);
                        } else {
                            res.add(x*y);
                        }
                    }
                }
            }
        }

        if (res.size() == 0) { //no operator
            res.add(Integer.parseInt(expression));
        }

        return res;
    }

    // private int dp2(String left, String right, int curr, int ogLen, List<Integer> st) {
    //     if (curr >= exp.length()) return 0;
    //     if (left.length() == 1) return Character.getNumericValue(exp.charAt(curr));
    // }

    // private int dp(String exp, int curr, int ogLen, List<Integer> st) {
    //     // System.out.println(exp);

    //     if (curr >= exp.length()) return 0;
    //     if (exp.length() == 1) return Character.getNumericValue(exp.charAt(curr));
    //     // return dp(exp, curr+1, ogLen, st);
    //     for (int i=0; i<exp.length(); i++) {
    //         if (exp.length() == ogLen) {
    //             // System.out.println("--------------------");
    //             // System.out.println(i);
    //         }
    //         char ch = exp.charAt(i);
    //         // int left = 0, right = 0;
    //         if (!(ch == '-' || ch == '+' || ch == '*')) continue;
    //         int split = 0, right = 0;
    //         if (ch == '-' || ch == '+' || ch == '*') {
    //             split = dp(exp.substring(0, i), 0, ogLen, st);
    //             right = dp(exp.substring(i+1, exp.length()), 0, ogLen, st);
    //             if (ch == '-') {
    //                 if (exp.length() == ogLen){
    //                     // System.out.println(exp + " " + ch);
    //                     st.add(left-right);
    //                 }
    //                 else return left-right;
    //             }

    //             if (ch == '+') {
    //                 if (exp.length() == ogLen) {
    //                     // System.out.println(exp + " " + ch);
    //                     st.add(left+right);
    //                 }
    //                 else return left + right;
    //             }

    //             if (ch == '*') {
    //                 if (exp.length() == ogLen) {
    //                     // System.out.println(exp + " " + ch);
    //                     st.add(left*right);
    //                 }
    //                 else return left*right;
    //             }

    //         }

    //         dp(exp, i+1, ogLen, st);

    //     }

    //     // return dp(exp, curr+1, ogLen, st);
    //     return 0;
    // }
}
