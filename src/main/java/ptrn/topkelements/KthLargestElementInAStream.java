package ptrn.topkelements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//@link -
//@check -

//1) Optimal approach
public class KthLargestElementInAStream {

}

//2) Brute force approach.
class KthLargest {
    int k;
    List<Integer> lst = new ArrayList<>();
    int kthLargest = Integer.MIN_VALUE;
    public KthLargest(int k, int[] nums) {
        this.k = k;
        for (int num: nums) lst.add(num);
    }

    public int add(int val) {
        lst.add(val);
        if (kthLargest != Integer.MIN_VALUE) {
            if (val < kthLargest) return kthLargest;
        }

        Collections.sort(lst, (a, b) -> b-a);

        for (int i=0; i<k; i++) {
            kthLargest = lst.get(i);
        }

        return kthLargest;
    }
}
