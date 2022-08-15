package basics.stack;


import java.util.HashMap;
import java.util.Stack;

//Possible chars - {,},(,),[,]
//Balanced means -> Whatever opens late, closes first and vice-versa.
public class BalancedParenthesis {

    //Not correct.
    //returns false for {}[()] even though it is balance
    //basically this algo will work only for symmetric scenario.
    boolean checkBalanced2Ptr(String s) {
        int start = 0;
        int end = s.length() - 1;
        HashMap<Character, Character> map = new HashMap<>();
        map.put('{', '}');
        map.put('(', ')');
        map.put('[', ']');

        while (start<=end) {
            if (map.get(s.charAt(start)) != s.charAt(end))
                return false;
            start++;
            end--;
        }
        return true;
    }

    boolean checkBalanced(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i=0; i<s.length(); i++) {
            if (s.charAt(i) == '[' || s.charAt(i) == '('
             || s.charAt(i) == '{') {
                stack.push(s.charAt(i)); //push to stack if opening.
            } else {
                if (stack.empty()) return false;
                if (!matching(stack.peek(), s.charAt(i)))
                    return false;
                else
                    stack.pop();
            }
        }
        return stack.empty(); //to handle extra opening brackets - ((())
    }

    private boolean matching(char a, char b) {
        return (a == '(' && b == ')') ||
                (a == '{' && b == '}') ||
                (a == '[' && b == ']');
    }

    public static void main(String[] args) {
            BalancedParenthesis balancedParenthesis = new BalancedParenthesis();
        System.out.println(balancedParenthesis.checkBalanced2Ptr("([])"));
        System.out.println(balancedParenthesis.checkBalanced2Ptr("[{]}"));
        System.out.println(balancedParenthesis.checkBalanced2Ptr("{}[()]"));

        System.out.println("-------------------");
        System.out.println(balancedParenthesis.checkBalanced("([])"));
        System.out.println(balancedParenthesis.checkBalanced("[{]}"));
        System.out.println(balancedParenthesis.checkBalanced("{}[()]"));

    }
}
