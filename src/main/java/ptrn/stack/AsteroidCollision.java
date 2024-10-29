package ptrn.stack;

import java.util.*;

//@link - https://leetcode.com/problems/asteroid-collision/description/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/5883171821584384
public class AsteroidCollision {
    /********* SWD SOLN ********/
    public int[] asteroidCollision(int[] asteroids) {
//        return collideAsteroids(asteroids);
        return revise(asteroids);
    }

    private int[] collideAsteroids(int[] asteroids) {
        Deque<Integer> stack = new ArrayDeque<>();

        for (int currentAsteroid: asteroids) {
            if (currentAsteroid > 0) {
                stack.addFirst(currentAsteroid);
            } else {
                while (!stack.isEmpty() && stack.peekFirst() > 0 && stack.peekFirst() < Math.abs(currentAsteroid)) {
                    stack.removeFirst();
                }

                if (stack.isEmpty() || stack.peekFirst() < 0) {
                    stack.addFirst(currentAsteroid);
                }

                if (stack.peekFirst() == Math.abs(currentAsteroid)) {
                    stack.pop();
                }
            }
        }

        int[] result = new int[stack.size()];
        int currentIndex = stack.size()-1;

        while (!stack.isEmpty()) {
            result[currentIndex--] = stack.pop();
        }

        return result;
    }



    /****** MY SOLN *******/
    public int[] asteroidCollision2(int[] asteroids) {
        List<Integer> ans = collideAsteroids2(asteroids);
        Collections.reverse(ans);
        int[] finalAns = new int[ans.size()];


        for (int i=0; i<ans.size(); i++) {
            finalAns[i] = ans.get(i);
        }

        return finalAns;
    }

    private List<Integer> collideAsteroids2(int[] asteroids) {
        Deque<Integer> stack = new ArrayDeque<>();

        for (int asteroid: asteroids) {
            if (stack.isEmpty()) stack.addFirst(asteroid);

            else if (canCollide(asteroid, stack.peekFirst())) {
                stack.addFirst(asteroid);
                int first = stack.removeFirst();
                int second = stack.peekFirst();
                while (!stack.isEmpty() && canCollide(first, second)) {
                    if (Math.abs(first) > Math.abs(second)) {
                        stack.removeFirst();
                        stack.addFirst(first);
                    } else if (Math.abs(first) == Math.abs(second)) {
                        stack.removeFirst();
                    }

                    if (stack.isEmpty() || stack.size() == 1) break;

                    first = stack.removeFirst();
                    second = stack.peekFirst();
                }

                if (!canCollide(first, second)) stack.addFirst(first);
            } else {
                stack.addFirst(asteroid);
            }
        }

        return new ArrayList<>(stack);
    }

    private boolean canCollide(int val1, int val2) {
        //asteroid will collide only when the prev element of array
        //is +ve (i.e. moving right) and next element is -ve (moving left)
        //Note: we can simply say if signs of elements are opposite then theyll collide.
        //eg: if prev element is -ve (it will move left) and next element is +ve (it will move right)
        //here even though signs of the elements are different, they won't collide, as they are moving away.
        return ((val1 < 0 && val2 > 0));
    }


    //////////////////////
    private int[] revise(int[] asteroids) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (int asteroid: asteroids) {
            if (stack.isEmpty()) stack.addFirst(asteroid);
            else if (asteroid < 0) {
                int collide = asteroid;
                while (!stack.isEmpty() && stack.peekFirst() > 0 && collide < 0) {
                    int top = stack.removeFirst();
                    if (Math.abs(collide) == top) collide = 0;
                    else if (Math.abs(collide) < top) collide = top;
                }
                if (collide != 0) stack.addFirst(collide);
            } else stack.addFirst(asteroid);
        }

        int[] ans = new int[stack.size()];
        int i = ans.length-1;
        while (!stack.isEmpty()) {
            ans[i] = stack.removeFirst();
            i -= 1;
        }

        return ans;
    }
}
