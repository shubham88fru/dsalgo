package ptrn.unionfind;

import java.util.*;
import java.util.Set;
import java.util.stream.Collectors;

//@link - https://leetcode.com/problems/longest-consecutive-sequence/description/
//@strvr - https://takeuforward.org/data-structure/longest-consecutive-sequence-in-an-array/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6180939001757696
public class LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        //return longestConsecutiveBrute(nums);
        //return longestConsecutiveBetter(nums);
        return longestConsecutiveOptimal(nums);
    }

    //0)Edctv has some soln using union find @check.
    /**
     * We initialize the Union Find data structure
     * with each element in the array as a separate component using dictionaries.
     * Then, we iterate through the array, evaluating for each element if
     * its neighbor with a value of one greater is present in the Union Find data structure.
     * If found, the two components are combined. Once all the elements have been processed,
     * the size of the largest connected component is returned as the
     * length of the longest consecutive sequence.
     */
    private int longestConsecutiveDSU(int[] nums) {
        if(nums.length == 0){
            return 0;
        }
        // data structure for implementing union find
        UnionFindLCS ds = new UnionFindLCS(nums);

        for (int num : nums) {
            // check if the next consecutive number
            // is in the input array
            if (ds.parent.containsKey(num + 1)) {
                ds.union(num, num + 1);
            }
        }

        return ds.maxLength;
    }

    //1) Optimal approach: T: O(3N), S: O(N)
    //using hashset
    private int longestConsecutiveOptimal(int[] nums) {
        int n = nums.length;
        if (n==0) return 0;

        //Collect the input array as hashset.
        Set<Integer> sett = Arrays.stream(nums)
                .mapToObj(el -> (int)el)
                .collect(Collectors
                        .toCollection(HashSet::new));

        int currSeq = 1; //length of curr sequence.
        int longestSeq = 1; //length of largest sequence.

        for (int num: sett) {
            int curr = num;
            //if curr num's prev exists in the
            //set, mean's the sequence can't start from the
            //the curr element. Curr element will be a 'part' of
            //the sequence and not  the 'start' of the sequence.
            //so no point to start counting from this element, since
            //anyways when we reach its corresponding sequence's start
            //element, this element will be accounted for.
            if (sett.contains(curr-1)) continue;

            //if curr element is start (i.e. no prev) then
            //continue the sequence.
            while (sett.contains(curr+1)) {
                currSeq += 1;
                curr += 1;
            }

            //update longest sequence to whichever is
            //larger.
            longestSeq = Math.max(longestSeq, currSeq);
            currSeq = 1;
        }

        return longestSeq;
    }

    //2) Better approach: T: O(nlogn), S: O(1)
    //using sorting.
    private int longestConsecutiveBetter(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;

        Arrays.sort(nums);
        int currSeq = 1; //length of curr sequence.
        int longestSeq = 1; //longest sequence in entire array.
        int prev = Integer.MIN_VALUE; //prev value (to compare with next and check if sequence or not)

        for (int num: nums) {
            if (num-1 == prev) { //curr and prev and consecutive - continue the sequence.
                currSeq += 1;
                prev = num;
            } else if (prev != num) { //prev is neither one less than curr, nor same as curr - break the sequence.
                currSeq = 1;
                prev = num;
            } //if curr value is same as prev (eg: 1, 1, 1) - just ignore, we only need consecutive.

            longestSeq = Math.max(longestSeq, currSeq); //if current sequence is larger than last recorded - update it.
        }

        return longestSeq;
    }

    //3) Brute force: T: O(N^2)
    //for every element of array keep checking if
    //we have its next element and so on.
    private int longestConsecutiveBrute(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        int count = 1;

        for (int i=0; i<n; i++) {
            int num = nums[i];
            int consecutive = 1;
            while (linearSearch(nums, num+1)) {
                num += 1;
                consecutive += 1;
            }
            count = Math.max(count, consecutive);
        }

        return count;
    }

    private boolean linearSearch(int[] nums, int num) {
        for (int n: nums) {
            if (num == n) return true;
        }
        return false;
    }
}

class UnionFindLCS {
    public Map<Integer, Integer> parent;
    public Map<Integer, Integer> size;
    public int maxLength;

    // Constructor
    public UnionFindLCS(int[] nums) {
        parent = new HashMap<>();
        size = new HashMap<>();
        maxLength = 1;

        for (int num : nums) {
            parent.put(num, num);
            size.put(num, 1);
        }
    }

    // Function to find the root of a sequence to which num belongs
    public int find(int num) {
        if (parent.get(num) != num) {
            parent.put(num, find(parent.get(num)));
        }
        return parent.get(num);
    }

    // Function to merge the two sequences and update lengths
    public void union(int num1, int num2) {
        int xRoot = find(num1);
        int yRoot = find(num2);
        if (xRoot != yRoot) {
            if (size.get(xRoot) < size.get(yRoot)) {
                int temp = xRoot;
                xRoot = yRoot;
                yRoot = temp;
            }
            parent.put(yRoot, xRoot);
            size.put(xRoot, size.get(xRoot) + size.get(yRoot));
            maxLength = Math.max(maxLength, size.get(xRoot));
        }
    }
}
