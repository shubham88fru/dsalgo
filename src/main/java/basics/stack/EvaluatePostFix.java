package basics.stack;

//Sample input -> "10 2 * 3 +"
public class EvaluatePostFix {

    int evaluatePostFix(String postFixExpression) {
        String[] els = postFixExpression.split(" ");
        int start = 0;
        while (start<els.length) {
            if (isOperator(els[start])) {
                els[start] = operateOnLastTwo(els[start-2], els[start-1], els[start]);
            } else {
                start++;
            }
        }
        return Integer.parseInt(els[start-1]);
    }

    private boolean isOperator(String s) {
        return (s.equals("*") || s.equals("+") ||
                s.equals("-") || s.equals("/") ||
                s.equals("^"));
    }

    private String operateOnLastTwo(String a, String b, String operator) {
        int aint = Integer.parseInt(a);
        int bint = Integer.parseInt(b);
        switch (operator) {
            case "+":
                return Integer.toString(aint+bint);
            case "-":
                return Integer.toString(aint-bint);
            case "*":
                return Integer.toString(aint*bint);
            case "/":
                return Integer.toString(aint/bint);
            case "^":
                return Integer.toString(aint^bint);
        }
        return null;
    }

    public static void main(String[] args) {
        EvaluatePostFix evaluatePostFix = new EvaluatePostFix();
        System.out.println(evaluatePostFix.evaluatePostFix("10 2 * 3 +"));
        System.out.println(evaluatePostFix.evaluatePostFix("10 2 + 3 *"));
    }
}
