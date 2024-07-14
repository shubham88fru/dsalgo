package lc_potd;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.TreeMap;

//@link - https://leetcode.com/problems/number-of-atoms/?envType=daily-question&envId=2024-07-14
//@check - https://www.youtube.com/watch?v=NYLmQI0GkeM&ab_channel=AryanMittal
public class NumberOfAtoms {

    //My soln, but heavily influenced by aryan's explanation.
    //So, rewatch the video if in doubt.
    public String countOfAtoms(String formula) {
        Deque<Pair2> stack = new ArrayDeque<>();

        Map<String, Pair2> bucket = new TreeMap<>((k1, k2) -> k1.compareTo(k2));
        for (int i = 0; i < formula.length(); i++) {
            char ch = formula.charAt(i);
            if (ch == '(') stack.addFirst(new Pair2("(", -1));
            else if (ch == ')') {
                StringBuffer dig = new StringBuffer();
                while (i+1 < formula.length() && Character.isDigit(formula.charAt(i+1))) {
                    dig.append(formula.charAt(i+1));
                    i += 1;
                }

                int num = 1;
                if (dig.length() != 0) num = Integer.parseInt(dig.toString());
                while (!stack.isEmpty()) {
                    if (stack.peekFirst().atom == "(") {
                        stack.removeFirst();
                        for (Map.Entry<String, Pair2> entry: bucket.entrySet()) {
                            stack.addFirst(entry.getValue());
                        }
                        bucket.clear();
                        break;
                    }

                    Pair2 p = stack.removeFirst();
                    p.count *= num;
                    if (!bucket.containsKey(p.atom)) bucket.put(p.atom, p);
                    else {
                        bucket.put(p.atom, new Pair2(p.atom, bucket.get(p.atom).count + p.count));
                    }
                }
            } else if (ch >= 65 && ch <= 90) {
                StringBuffer sb = new StringBuffer(ch+"");
                while (i+1 < formula.length() && isLowerAlpha(formula.charAt(i+1))) {
                    sb.append(formula.charAt(i+1));
                    i += 1;
                }
                StringBuffer dig = new StringBuffer();
                while (i+1 < formula.length() && Character.isDigit(formula.charAt(i+1))) {
                    dig.append(formula.charAt(i+1));
                    i += 1;
                }
                if (dig.length() == 0) stack.addFirst(new Pair2(sb.toString(), 1));
                else stack.addFirst(new Pair2(sb.toString(), Integer.parseInt(dig.toString())));
            }
        }

        while (!stack.isEmpty()) {
            Pair2 p = stack.removeFirst();
            if (!bucket.containsKey(p.atom)) bucket.put(p.atom, p);
            else {
                bucket.put(p.atom, new Pair2(p.atom, bucket.get(p.atom).count + p.count));
            }
        }

        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, Pair2> entry: bucket.entrySet()) {
            sb.append(entry.getKey());
            if (entry.getValue().count != 1)
                sb.append(entry.getValue().count);
        }

        return sb.toString();
    }

    private boolean isLowerAlpha(char ch) {
        return (ch >= 97 && ch <= 122);
    }
}

class Pair2 {
    String atom;
    int count;

    public Pair2(String atom, int count) {
        this.atom = atom;
        this.count = count;
    }

    public String toString() {
        return "[" + atom + ", " + count + "]";
    }
}