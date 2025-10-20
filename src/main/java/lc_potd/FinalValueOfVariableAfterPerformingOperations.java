package lc_potd;

//@link - https://leetcode.com/problems/final-value-of-variable-after-performing-operations/description/?
public class FinalValueOfVariableAfterPerformingOperations {
    public int finalValueAfterOperations(String[] operations) {
        return revise(operations);
    }

    private int revise(String[] operations) {
        int n = operations.length;

        int val = 0;
        for (String op: operations) {
            if (op.charAt(1) == '+') {
                val += 1;
            } else {
                val -= 1;
            }
        }

        return val;
    }
}
