package ptrn.kwaymerge;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

//@link - https://leetcode.com/problems/find-k-pairs-with-smallest-sums/description/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/4879046048743424
//@tag - TO_REVISIT
public class FindKPairsWithSmallestSums {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        int p1 = 0;
        int j = 0;

        List<List<Integer>> ans = new ArrayList<>();
        //min heap to store the pairs sorted by their sums.
        //0 --> element of list 1.
        //1 --> element of list 2.
        //2 --> index of element from list 1.
        //3 --> index of element from list 2.
        //4 --> sum of the pair.
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a1, a2) -> a1[4] - a2[4]);

        //First, push all pairs by fixing first element of second array, into the minHeap.
        for (int i=0; i<nums1.length; i++) {
            minHeap.add(new int[]{nums1[i], nums2[j], i, j, nums1[i]+nums2[j]});
        }

        //Then, as long as we haven't found k pairs or the minHeap is empty.
        while (k > 0 && !minHeap.isEmpty()) {
            //keep taking out the min sum pair from the heap.
            List<Integer> pair = new ArrayList<>();

            int[] item = minHeap.remove();
            int x = item[0]; //list 1 element.
            int y = item[1]; //list 2 element.
            int i = item[2]; //index of list 1 element in list 1.
            j = item[3]; //index of list 2 element in list 2.
            int sum = item[4]; //sum of pair.

            //since the sum is minimum (coz came from min heap)
            //add the pair to the ans list.
            pair.add(item[0]);
            pair.add(item[1]);
            ans.add(pair);

            //Next, if the second list has elements next
            //to the index that was just popped, for a pair
            //of the next element of second list with the same element
            //of first list and add to heap.
            if (j+1 < nums2.length) {
                minHeap.add(new int[]{nums1[i], nums2[j+1], i, j+1, nums1[i]+nums2[j+1]});
            }

            k -= 1;
        }

        return ans;
    }
}
