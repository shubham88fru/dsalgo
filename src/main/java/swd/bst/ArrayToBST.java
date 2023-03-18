package swd.bst;

//@link - https://practice.geeksforgeeks.org/problems/array-to-bst4443/1
public class ArrayToBST {
    public int[] sortedArrayToBST(int[] nums) {
        // Code here
        int[] ans = new int[nums.length];
        int[] currIndex = {0};

        writePreorder(nums, currIndex, 0, nums.length-1, ans);

        return ans;
    }

    private void writePreorder(int[] nums, int[] currIndex, int treeStart, int treeEnd, int[] ans) {

        if (currIndex[0] >= nums.length) return;
        if (treeStart > treeEnd) return;

        int midIndex = (treeEnd + treeStart) / 2;

        ans[currIndex[0]] = nums[midIndex];
        currIndex[0] += 1;
        writePreorder(nums, currIndex, treeStart, midIndex-1, ans);
        writePreorder(nums, currIndex, midIndex+1, treeEnd, ans);

    }
}
