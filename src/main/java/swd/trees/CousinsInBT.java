package swd.trees;

import strvr.binarytree.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

//@link - https://leetcode.com/problems/cousins-in-binary-tree/description/
public class CousinsInBT {
    public boolean isCousins(TreeNode root, int x, int y) {

        //1) DFS
        /**
         String[] depthAndParent1 = dfs(root, x, 0, null).split("-");
         String[] depthAndParent2 = dfs(root, y, 0, null).split("-");

         //if x's parent is not same as y's parent and
         //depth of x is same as dept of y, then x and y are cousins.
         return (depthAndParent1[0].equals(depthAndParent2[0])
         && !depthAndParent1[1].equals(depthAndParent2[1]));
         **/

        //2) BFS
        return bfs(root, x, y);
    }

    //1) (DFS) Pre-order traversal algorithm
    private String dfs(TreeNode root, int val, int depth, TreeNode parent) {
        if (root == null) return null;
        if (root.val == val) {
            return (depth + "-" + parent);
        }

        String left = dfs(root.left, val, depth + 1, root);
        if (left != null) return left;

        String right = dfs(root.right, val, depth + 1, root);
        if (right != null) return right;

        return null;
    }

    //2) BFS Algorithm for the problem.
    private boolean bfs(TreeNode root, int x, int y) {
        if (root == null) return false;

        Deque<TreeNodeWrapper> queue = new ArrayDeque<>();
        queue.addLast(new TreeNodeWrapper(root, null, 0));

        TreeNode xParent = null;
        TreeNode yParent = null;

        int level = 0;
        int xLevel = 0;
        int yLevel = 0;

        while (!queue.isEmpty()) {
            int nodesToProcessAtCurrLevel = queue.size();

            while (nodesToProcessAtCurrLevel > 0) {
                TreeNodeWrapper wrappedNode = queue.removeFirst();
                TreeNode currNode = wrappedNode._node;

                //if found, update the level and parent.
                if (currNode.val == x) {
                    xLevel = wrappedNode._level;
                    xParent = wrappedNode._parent;
                } else if (currNode.val == y) {
                    yLevel = wrappedNode._level;
                    yParent = wrappedNode._parent;
                }

                if (currNode.left != null) {
                    queue.addLast(new TreeNodeWrapper(currNode.left, currNode, level + 1));
                }

                if (currNode.right != null) {
                    queue.addLast(new TreeNodeWrapper(currNode.right, currNode, level + 1));
                }

                nodesToProcessAtCurrLevel -= 1;
            }
            level += 1;
        }

        //x and y are cousins if their levels are same
        //but their parents are different.
        return ((xLevel == yLevel) && (xParent != yParent));
    }
}

//A wrapper to store extra info for each node
//like its parent and the level/depth in tree.
class TreeNodeWrapper {
    public TreeNode _node;
    public TreeNode _parent;
    public int _level;

    public TreeNodeWrapper(TreeNode node, TreeNode parent, int level) {
        this._node = node;
        this._parent = parent;
        this._level = level;
    }
}
