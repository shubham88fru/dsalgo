package lc_potd;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

//@link - https://leetcode.com/problems/design-hit-counter/description/?

/**
 * My approach 1:
 * Store hits in list
 * and binary search in getHits.
 * This works because it is guaranteed
 * that the timestamps will always be
 * in increasing order.
 *
 * getHits TC: O(log(n))
 * SC: O(n)
 *
 * The problem with this approach is
 * that the list size can grow to a very
 * large size.
 */
public class DesignHitCounter {
    List<Integer> hits;

    public DesignHitCounter() {
        hits = new ArrayList<>();
    }

    public void hit(int timestamp) {
        hits.add(timestamp);
    }

    public int getHits(int timestamp) {
        return binarySearch(hits, timestamp-300);
    }

    private int binarySearch(List<Integer> hits, int gt) {
        int n = hits.size();
        int l = 0, r = n-1;

        int i = n;
        while (l <= r) {
            int mid = l + (r-l)/2;
            if (hits.get(mid) > gt) {
                i = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return n-i;
    }
}

/**
 * My approach 2:
 * Store hits in queue
 * and in every getHits call,
 * remove from end.
 *
 * getHits TC: O(n)
 * SC: O(n)
 *
 * Plus point is that we are
 * clearing up the queue
 */
class DesignHitCounter2 {
    Deque<Integer> dq = new ArrayDeque<>();

    public DesignHitCounter2() {

    }

    public void hit(int timestamp) {
        dq.addLast(timestamp);
    }

    public int getHits(int timestamp) {
        while (!dq.isEmpty()) {
            int gt = timestamp - 300;
            if (dq.peekFirst() <= gt) dq.removeFirst();
            else break;
        }

        return dq.size();
    }
}