package ptrn.topkelements;

import java.util.PriorityQueue;

//@link - https://leetcode.com/problems/k-closest-points-to-origin/description/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/4941192799453184
public class KClosestPointsToOrigin {
    //This problem basically wants us to find k points with
    //least distance from origin. And so, we'll use a maxHeap with the
    //top k elements pattern.
    PriorityQueue<PointWrapper> maxHeap =
            new PriorityQueue<PointWrapper>(
                    (pw1, pw2) -> pw2.distanceToOrigin.compareTo(pw1.distanceToOrigin)
            );

    public int[][] kClosest(int[][] points, int k) {
        int[] orig = new int[] {0, 0};

        //calculate distance of each point from the origin
        //and push to heap.
        for (int[] point: points) {
            PointWrapper pw = new PointWrapper(point, euclideanDistance(point, orig));
            addToHeap(pw, k);
        }

        //heap to array.
        int[][] ans = new int[k][2];
        int i = 0;
        while (!maxHeap.isEmpty()) {
            PointWrapper pw = maxHeap.remove();
            ans[i] = pw.point;
            i += 1;
        }

        return ans;
    }

    //calculated euclidean distance b/w two given points.
    private double euclideanDistance(int[] p1, int[] p2) {
        int x1 = p1[0];
        int y1 = p1[1];

        int x2 = p2[0];
        int y2 = p2[1];

        return Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));
    }

    //Add to maxHeap.
    //First k elements will be added as is.
    //Then, if the new element's distance from origin is smaller
    //than the element at the top of heap, remove top of heap and add the new element.
    private void addToHeap(PointWrapper pw, int k) {
        if (maxHeap.size() < k) {
            maxHeap.add(pw);
        } else if (pw.distanceToOrigin < maxHeap.peek().distanceToOrigin) {
            maxHeap.remove();
            maxHeap.add(pw);
        }
    }
}

class PointWrapper {
    int[] point;
    Double distanceToOrigin;

    public PointWrapper(int[] point, Double distanceToOrigin) {
        this.point = point;
        this.distanceToOrigin = distanceToOrigin;
    }
}
