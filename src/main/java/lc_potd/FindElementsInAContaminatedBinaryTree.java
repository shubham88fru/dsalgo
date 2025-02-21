package lc_potd;

import java.util.HashSet;
import java.util.Set;

//@link - https://leetcode.com/problems/find-elements-in-a-contaminated-binary-tree/
public class FindElementsInAContaminatedBinaryTree {

    //1) Approach 1: Optimal
    static class FindElements {
        TreeNode root;
        Set<Integer> st;

        /* Store the element of tree in a set during initialization. */
        /* This will make find call O(1) operation. */
        public FindElements(TreeNode root) {
            this.root = root;
            this.st = new HashSet<>();
            dfs(this.root, 0, st);
        }

        //O(1)
        public boolean find(int target) {
            return st.contains(target);
        }

        private void dfs(TreeNode root, int val, Set<Integer> st) {
            if (root == null) return;

            st.add(val);

            dfs(root.left, 2*val+1, st);
            dfs(root.right, 2*val+2, st);
        }
    }

     //Approach 2) Suboptimal
     static class FindElements2 {
         TreeNode root;


         public FindElements2(TreeNode root) {
             this.root = root;
             this.root.val = 0;
         }

         /* Run DFS on every find call. Every find call will be O(N) -- bad */
         public boolean find(int target) {
             return dfs(this.root, 0, target);
         }

         private boolean dfs(TreeNode root, int val, int t) {
             if (root == null) return false;


             if (val == t) return true;

             boolean left = dfs(root.left, 2*val+1, t);
             boolean right = dfs(root.right, 2*val+2, t);

             return left || right;

         }
     }

/**
 * Your FindElements object will be instantiated and called as such:
 * FindElements obj = new FindElements(root);
 * boolean param_1 = obj.find(target);
 */
}
