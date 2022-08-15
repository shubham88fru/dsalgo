package basics.stack;

public class EvaluatePrefix {

    //doesn't work.
    int evaluatePreFix(String postFixExpression) {
        String[] els = postFixExpression.split(" ");
        int end = els.length-1;
        while (end>=0) {
            if (isOperator(els[end])) {
                els[end] = operateOnLastTwo(els[end+2], els[end+1], els[end]);
            } else {
                end--;
            }
        }
        return Integer.parseInt(els[0]);
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
        EvaluatePrefix evaluatePreFix = new EvaluatePrefix();
        System.out.println(evaluatePreFix.evaluatePreFix("+ * 10 2 3"));
        System.out.println(evaluatePreFix.evaluatePreFix("* + 10 2 3"));
    }
}
