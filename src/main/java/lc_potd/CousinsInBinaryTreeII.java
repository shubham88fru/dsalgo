package lc_potd;

import java.util.ArrayDeque;
import java.util.Deque;

//@link - https://leetcode.com/problems/cousins-in-binary-tree-ii/
public class CousinsInBinaryTreeII {
    public TreeNode replaceValueInTree(TreeNode root) {
        return mysol(root);
    }

    /*
        Following is my soln.
        Mik's soln was also on similar lines, but also showed
        an approach (slight modification to mine) which resulted in one pass.
        If this problem is a recurring question for some company, rewatch the video.
     */
    private TreeNode mysol(TreeNode root) {

        root.val = 0;
        Deque<PairBT> q = new ArrayDeque<>();

        if (root.left != null) {
            root.left.val = 0;
            q.addLast(new PairBT(root.left, 0));
        }

        if (root.right != null) {
            root.right.val = 0;
            q.addLast(new PairBT(root.right, 0));
        }

        while (!q.isEmpty()) {
            int pass = 0;
            int levelSum = 0;
            while (pass < 2) { //2 pass each level.
                int sz = q.size();
                while (sz > 0) {
                    PairBT p = q.removeFirst();

                    int siblingSum = 0;
                    if (pass == 0) { //on first pass, calculate child sibling sum and level sum.
                        if (p.node.left != null) siblingSum += p.node.left.val;
                        if (p.node.right != null) siblingSum += p.node.right.val;
                        q.addLast(new PairBT(p.node, siblingSum));
                        levelSum += siblingSum;
                    } else if (pass == 1) { //on second pass, update the values of children.
                        if (p.node.left != null) {
                            p.node.left.val = levelSum -  p.siblingSum;
                            q.addLast(new PairBT(p.node.left, 0));
                        }

                        if (p.node.right != null) {
                            p.node.right.val = levelSum - p.siblingSum;
                            q.addLast(new PairBT(p.node.right, 0));
                        }
                    }
                    sz -= 1;
                }
                pass += 1;
            }
        }

        return root;
    }
}

class PairBT {
    TreeNode node;
    int siblingSum;

    public PairBT(TreeNode node, int siblingSum) {
        this.node = node;
        this.siblingSum = siblingSum;
    }
}