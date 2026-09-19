package lc_potd;

//@link - https://leetcode.com/problems/circle-and-rectangle-overlapping/description/?
//@check - https://www.youtube.com/watch?v=YQoN-z3HKI4
public class CircleAndRectangleOverlapping {
    public boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        return mikssol(radius, xCenter, yCenter, x1, y1, x2, y2);
    }

    /**
     Coded by me after taking hints from mik.
     I was on the right track but the question is kind of
     worded in a confusing way. Cases like the entire rect inside
     the cicle with not intersection of its sides with the circle
     are still considered overlapping.
     Further the problem becomes much easier coz of the constraint that
     the rectangle is always axis aligned, making it easier to find
     the closes point on rect from the circle's center.
     */
    private boolean mikssol(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        int xClosest = -1;
        if (xCenter > x1 && xCenter < x2) xClosest = xCenter;
        else if (xCenter <= x1) xClosest = x1;
        else if (xCenter >= x2) xClosest = x2;

        int yClosest = -1;
        if (yCenter > y1 && yCenter < y2) yClosest = yCenter;
        else if (yCenter <= y1) yClosest = y1;
        else if (yCenter >= y2) yClosest = y2;

        return dist(xCenter, yCenter, xClosest, yClosest) <= radius;
    }

    private double dist(int xCenter, int yCenter, int xClosest, int yClosest) {

        return Math.sqrt((xCenter-xClosest)*(xCenter-xClosest) + (yCenter-yClosest)*(yCenter-yClosest));
    }
}
