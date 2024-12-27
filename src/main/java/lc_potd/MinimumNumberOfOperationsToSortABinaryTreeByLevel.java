package lc_potd;

import java.util.*;

//@link - https://leetcode.com/problems/minimum-number-of-operations-to-sort-a-binary-tree-by-level/
public class MinimumNumberOfOperationsToSortABinaryTreeByLevel {
    public int minimumOperations(TreeNode root) {
        return suboptimal(root);
    }

    private int suboptimal(TreeNode root) {
        Deque<TreeNode> q = new ArrayDeque<>();

        q.addLast(root);

        int swaps = 0;
        List<Integer> nums = new ArrayList<>();
        while (!q.isEmpty()) {
            int sz = q.size();

            int cnt = 0;
            int idx = 0;
            Map<Integer, Integer> mp = new HashMap<>();
            nums.clear();
            mp.clear();

            while (sz > 0) {
                TreeNode node = q.removeFirst();
                if (node.left != null) {
                    q.addLast(node.left);
                    nums.add(node.left.val);
                    mp.put(node.left.val, idx);
                    idx += 1;
                }

                if (node.right != null) {
                    q.addLast(node.right);
                    nums.add(node.right.val);
                    mp.put(node.right.val, idx);
                    idx += 1;
                }

                sz -= 1;
            }

            // swaps += countUsingSelectionSort(nums);
            swaps += countUsingMap(nums, mp);
        }

        return swaps;
    }

    private int countUsingMap(List<Integer> nums, Map<Integer, Integer> mp) {
        int swap = 0;
        List<Integer> sorted = new ArrayList<>(nums);
        Collections.sort(sorted);
        // System.out.println("----------------");
        // System.out.println(nums);
        // System.out.println(sorted);

        for (int i=0; i<nums.size(); i++) {
            if (nums.get(i).intValue() != sorted.get(i).intValue()) {
                // System.out.println(nums.get(i) + " " + sorted.get(i));

                int targetIdx = mp.get(sorted.get(i));
                int temp = nums.get(i);
                nums.set(i, sorted.get(i));
                nums.set(targetIdx, temp);

                mp.put(temp, targetIdx);
                mp.put(sorted.get(i), i);
                swap += 1;
            }
        }



        // System.out.println(swap);
        return swap;
    }

    private int countUsingSelectionSort(List<Integer> nums) {
        int i = 0;
        int swap = 0;
        // System.out.println(nums);
        while (i<nums.size()-1) {
            int minI = i;
            int min = nums.get(i);
            for (int j=i; j<nums.size(); j++) {
                if (nums.get(j) < min) {
                    min = nums.get(j);
                    minI = j;
                }
            }
            // System.out.println(minI + " " + min);
            if (minI != i) {
                int temp = nums.get(minI);
                nums.set(minI, nums.get(i));
                nums.set(i, temp);
                swap += 1;
                // System.out.println("Sorted: " + nums);

            }

            i += 1;
        }

        return swap;
    }
}
