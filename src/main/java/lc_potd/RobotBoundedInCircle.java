package lc_potd;

//@link - https://leetcode.com/problems/robot-bounded-in-circle/
//@check - https://www.youtube.com/watch?v=nKv2LnC_g6E
public class RobotBoundedInCircle {
    public boolean isRobotBounded(String instructions) {
        return ncSol(instructions);
    }

    /*
        Coded by me completely based on
        NC's soln.
    */
    private boolean ncSol(String instructions) {
        int n = instructions.length();
        int xD=0, yD=1; //initially north direction
        int xP=0, yP=0;

        for (int i=0; i<n; i++) {
            char ch = instructions.charAt(i);
            if (ch == 'G') {
                xP += xD;
                yP += yD;
            }
            /*
                Math trick to rotate.
             */
            else if (ch == 'L') {
                int temp = xD;
                xD = -1*yD;
                yD = temp;
            } else {
                int temp = xD;
                xD = yD;
                yD = -1*temp;
            }
        }

        /*
            At the end, if the robot has not moved at all
            or if it has moved, the final direction is not
            same as initial direction, then its bound to
            be in a loop.
         */
        return (xP==0 && yP==0) || (xD != 0 || yD != 1);
    }
}
