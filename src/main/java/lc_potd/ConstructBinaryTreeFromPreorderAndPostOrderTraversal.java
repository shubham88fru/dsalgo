package lc_potd;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/
//@check - https://www.youtube.com/watch?v=6JDV3kIFyjU&t=2145s&ab_channel=codestorywithMIK
public class ConstructBinaryTreeFromPreorderAndPostOrderTraversal {
    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        // return pass1(preorder, postorder);

        return mikssol(preorder, postorder);
    }
    /*
        Soln shamelessly copied from mik.
        Normally, i don't save copied soln to my repo
        but had to do it because this is an important
        question.

        Wasted nearly 3 hrs ( :( ) to solve it
        by myself, but couldn't. What a shame.
    */
    public TreeNode mikssol(int[] preorder, int[] postorder) {
        int n = preorder.length;
        Map<Integer, Integer> mp = new HashMap<>();
        for (int i = 0; i < n; i++) {
            mp.put(postorder[i], i);
        }
        return solve(0, 0, n - 1, preorder, postorder, mp);
    }

    public TreeNode solve(int prestart, int poststart, int preend, int[] preorder, int[] postorder,
                          Map<Integer, Integer> mp) {
        if (prestart > preend) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[prestart]);
        if (prestart == preend) {
            return root;
        }
        int nextNode = preorder[prestart + 1]; // root of left subtree

        int j = mp.get(nextNode);

        int num = j - poststart + 1;

        root.left = solve(prestart + 1, poststart, prestart + num, preorder, postorder, mp);
        root.right = solve(prestart + num + 1, j + 1, preend, preorder, postorder, mp);

        return root;
    }

    private TreeNode pass1(int[] preorder, int[] postorder) {
        Map<Integer, Integer> mp = new HashMap<>();
        for (int i=0; i<postorder.length; i++) {
            mp.put(postorder[i], i);
        }

        int[] pri = {0};
        return construct(preorder, postorder, pri, 0, postorder.length-1, mp);
    }

    private TreeNode construct(int[] preorder, int[] postorder, int[] pri, int mini, int maxi, Map<Integer, Integer> mp) {
        System.out.println(preorder[pri[0]] + " " + mini + " " + maxi);
        int curr = preorder[pri[0]];
        int poi = mp.get(curr);
        pri[0] += 1;

        TreeNode root = new TreeNode(curr);
        if (mini >= maxi) return root;
        // if (pri[0] > preorder.length) return null;


        int next = preorder[pri[0]];


        // if (mini >= maxi) return root;

        int ul = mp.get(next)-1;
        int ll = mp.get(next)+1;




        root.left = construct(preorder, postorder, pri, mini, ul, mp);
        root.right = construct(preorder, postorder, pri, ll, poi-1, mp);

        return root;
    }
}
