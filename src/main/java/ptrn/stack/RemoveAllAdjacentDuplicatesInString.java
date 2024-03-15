package ptrn.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.Collectors;

//@link - https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6559519430934528
public class RemoveAllAdjacentDuplicatesInString {
    public String removeDuplicates(String s) {
        //initialize a stack.
        Deque<Character> stack = new ArrayDeque<>();
        for (char ch: s.toCharArray()) {
            //if stack is empty, push current char.
            if (stack.isEmpty()) stack.addFirst(ch);
                //else if current char not same as top of stack, then also push.
            else if (ch != stack.peekFirst()) stack.addFirst(ch);
            else stack.removeFirst();
        }

        //convert stack to string.
        StringBuffer sb = new StringBuffer(stack.stream()
                .map(ch -> Character.toString(ch)).collect(Collectors.joining("")));
        return sb.reverse().toString();
    }
}
