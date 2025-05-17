package lc_potd;

import java.util.*;

//@link - https://leetcode.com/problems/car-fleet/
//@check - https://www.youtube.com/watch?v=Pr6T-3yB9RM&ab_channel=NeetCode
public class CarFleet {
    public int carFleet(int target, int[] position, int[] speed) {
        // return pass1(target, position, speed);
        return pass2(target, position, speed);
    }

    /*
        Pass1 was my shot at this problem. Tried a lot but there
        were obvious issues. First, this problem will be very hard
        to solve when iterating from left to right.
        Second, my approach to check if two cars will fleet up
        was that standard physics time/speed thing which wouldn't
        always work because of fractions.

        This approach, as explained by nc (a lot of the points, I also
        had a hunch for), iterates the sorted list from the end to start
        and uses a different (smarter) trick to decide whether two
        cars will fleet up.

        UPDATE 05/17/25: We don't really need a stack for this soln.
        A 2D array is more than enough.
        @see - https://www.youtube.com/watch?v=PemeYPadst4&ab_channel=Pepcoding
    */
    private int pass2(int target, int[] position, int[] speed) {
        int n = position.length;
        List<int[]> pairs = new ArrayList<>();
        for (int i=0; i<n; i++) {
            pairs.add(new int[] { position[i], speed[i]});
        }

        Collections.sort(pairs, (p1, p2) -> p1[0]-p2[0]);

        Deque<Double> stack = new ArrayDeque<>();
        for (int i=n-1; i >=0; i--) {
            int ps = pairs.get(i)[0];
            int sp = pairs.get(i)[1];

            if (stack.isEmpty()) {
                stack.addFirst((target-ps)/(sp*1.0));
            } else {
                double timeToDestCurr = (target-ps)/(sp*1.0);
                if (stack.peekFirst() < timeToDestCurr) {
                    stack.addFirst(timeToDestCurr);
                }
            }
        }

        return stack.size();

    }

    private int pass1(int target, int[] position, int[] speed) {
        // Arrays.sort(position);
        int n = position.length;

        List<int[]> pairs = new ArrayList<>();
        for (int i=0; i<n; i++) {
            pairs.add(new int[] { position[i], speed[i]});
        }

        Collections.sort(pairs, (p1, p2) -> p1[0]-p2[0]);

        // System.out.println(pairs);

        Deque<int[]> stack = new ArrayDeque<>();

        int fleet = 1;
        for (int i=0; i<n; i++) {
            int pos = pairs.get(i)[0];
            int sp = pairs.get(i)[1];

            if (stack.isEmpty()) {
                stack.addFirst(new int[]{pos, sp});
            } else {

                while (!stack.isEmpty()) {
                    // int[] rem = stack.removeFirst
                    int prevCarSpeed = stack.peekFirst()[1];
                    int prevCarPos = stack.peekFirst()[0];

                    int relSpeed = prevCarSpeed - sp;
                    System.out.println("Stat.." + " " + prevCarPos + " " + pos + " " + prevCarSpeed + " " + sp);
                    // int juncPos = prevCarPos + (prevCarSpeed * (pos-prevCarPos)/relSpeed);
                    if (sp < prevCarSpeed && (prevCarPos + (prevCarSpeed * (pos-prevCarPos)/relSpeed)) <= target) {
                        System.out.println("Merging.." + pos + " " + prevCarPos);
                        // stack.removeFirst();
                        // stack.addFirst()
                        stack.peekFirst()[1] = sp;
                        stack.peekFirst()[0] = prevCarPos + (prevCarSpeed * (pos-prevCarPos)/relSpeed);
                    } else if (sp >= prevCarSpeed) {
                        stack.addFirst(new int[]{pos, sp});
                        // fleet += 1;
                        System.out.println("Exiting.." + pos + " " + sp + " fleet: " + fleet);

                        break;
                    } else {
                        stack.addFirst(new int[]{pos, sp});
                        // fleet += 1;
                        System.out.println("Exiting.." + pos + " " + sp + " fleet: " + fleet);

                        break;
                    }

                    int[] rem = stack.removeFirst();
                    sp = rem[1];
                    pos = rem[0];
                }

                if (stack.isEmpty()) stack.addFirst(new int[]{pos, sp});
                fleet += 1;
            }
        }

        return stack.size() == 0 ? 1 : stack.size();
    }
}
