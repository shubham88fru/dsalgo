package lc_potd;

import java.util.*;
import java.util.stream.Collectors;

//@link - https://leetcode.com/problems/robot-collisions/
public class RobotCollision {

    //My soln but is kinda nasty. Didn't check aryan's soln.
    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a1, a2) -> a1[0]-a2[0]);
        List<Integer> survivors = new ArrayList<>();

        for (int i=0; i<positions.length; i++) {
            minHeap.add(new int[] { positions[i], i });
            survivors.add(healths[i]);
        }


        Deque<Pair3> stack = new ArrayDeque<>();
        while (!minHeap.isEmpty()) {
            int[] data = minHeap.remove();
            // int pos = data[0];
            int idx = data[1];

            char ch = directions.charAt(idx);
            // System.out.println(idx + " " + ch);
            if (stack.isEmpty()) stack.addFirst(new Pair3(ch, idx));

            else if (stack.peekFirst().dir == 'R') {
                while (!stack.isEmpty() && stack.peekFirst().dir == 'R') {
                    // System.out.println(".....");
                    if (ch == 'L') {
                        int f = stack.peekFirst().idx;
                        int s = idx;
                        if (survivors.get(f) > survivors.get(s)) {
                            survivors.set(s, 0);
                            survivors.set(f, survivors.get(f)-1);

                        } else if (survivors.get(s) > survivors.get(f)) {
                            survivors.set(f, 0);
                            survivors.set(s, survivors.get(s)-1);
                            stack.removeFirst();
                            // stack.addFirst(new Pair3(ch, s));
                        } else {
                            survivors.set(f, 0);
                            survivors.set(s, 0);
                            stack.removeFirst();
                        }

                        if (survivors.get(s) == 0) break;
                    } else {
                        stack.addFirst(new Pair3(ch, idx));
                        break;
                    }
                }

            } else stack.addFirst(new Pair3(ch, idx));

            // System.out.println(idx + " " + ch + " " + stack);
        }

        return survivors.stream().filter(val -> val != 0).collect(Collectors.toList());
    }
}

class Pair3 {
    char dir;
    int idx;

    Pair3(char dir, int idx) {
        this.dir = dir;
        this.idx = idx;
    }

}
