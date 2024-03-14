package ptrn.stack;

import java.util.Stack;

//@link - https://leetcode.com/problems/basic-calculator/description/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/5527270229016576
public class SimpleCalculator {

    //1) Completely edctv soln. Read again, if confusing.
    public int calculate(String expression) {
        //1 -> +ve, -1 --> -ve
        int signValue = 1;
        int number = 0;
        int result = 0; //result of each subexpression.
        int secondValue = 0;
        Stack<Integer> stack = new Stack<>();
        //Iterate from left to right.
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            //If a digit is encountered, we append it to
            //the number formed till now, but adjusting the
            //decimal's place.
            // To store the consecutive digits that is 52 => 5 x 10 + 2 = 52
            if (Character.isDigit(c)) {
                number = number * 10 + Character.getNumericValue(c);
            }
            //If a '+'/'-' sign is observed, it means,
            //whatever number we were appending is done and dusted.
            //So we add the number formed (multiplied by its sign)
            //to the result, and reset the number back to 0.
            else if (c == '+' || c == '-') {
                result += number * signValue;
                if (c == '-')
                    signValue = -1;
                else
                    signValue = 1;
                number = 0;
            }

            //If we encounter an opening '(', means we are entering
            //a new subexpression, and so, we store aside the result or the outer expression
            //and the sign associated with the '(' (coz they'll be like ...+(...) or ...-(...)) in a stack
            //so that we can later multiply the entire result of the subexpression with this sign.
            //When entering into the subexpression, we also reset `result`, `signValue` and `number`.
            else if (c == '(') {
                stack.push(result);
                stack.push(signValue);
                result = 0;
                signValue = 1;
                number = 0;
            }

            //If we encounted a closing ')', means its the end of a subexpression.
            //so we add whatever number we have till now (respecting its sign) to the result.
            //Then we pop the signValue we stored in stack and apply it to the result of
            //the subexpression, and also add the result of the outer expression (which is also we stored in stack)
            //to the current result.
            else if (c == ')') {
                result += signValue * number;
                int popSignValue = stack.pop();
                result *= popSignValue;

                secondValue = stack.pop();
                result += secondValue;
                number = 0;
            }
        }

        //Not really sure why we are adding (number*signValue) to
        //the result here.
        return result + number * signValue;
    }
}
