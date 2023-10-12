package swd.trees;


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/description/
public class LowestCommonAncestorInBT {
    /*** My Soln - Works, but crappy ***/
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Map<TreeNode, TreeNode> parentMap = getParentsMap(root);
        Map<TreeNode, Integer> pmap = getDistanceFromAncestors(p, parentMap);
        if (pmap.containsKey(q)) return q;

        Map<TreeNode, Integer> qmap = getDistanceFromAncestors(q, parentMap);
        if (qmap.containsKey(p)) return p;

        if (qmap.size() >= pmap.size()) return lowestCommonAncestor(qmap, pmap);
        else if (qmap.size() <= pmap.size()) return lowestCommonAncestor(pmap, qmap);

        return root;
    }

    private TreeNode lowestCommonAncestor(Map<TreeNode, Integer> larger, Map<TreeNode, Integer> smaller) {
        int min = Integer.MAX_VALUE;
        TreeNode ans = null;
        for (Map.Entry<TreeNode, Integer> entry: larger.entrySet()) {
            TreeNode key = entry.getKey();
            if (smaller.containsKey(key)) {
                if (smaller.get(key) < min) {
                    min = smaller.get(key);
                    ans = key;
                }
            }
        }

        return ans;
    }

    private Map<TreeNode, Integer> getDistanceFromAncestors(TreeNode p, Map<TreeNode, TreeNode> parentMap) {
        Map<TreeNode, Integer> map = new HashMap<>();
        int dist = 0;
        map.put(p, dist);
        TreeNode currNode = p;
        while (currNode != null) {
            TreeNode parent = parentMap.get(currNode);

            if (parent != null) {
                dist += 1;
                map.put(parent, dist);
            }

            currNode = parent;
        }

        return map;
    }

    private Map<TreeNode, TreeNode> getParentsMap(TreeNode root) {
        if (root == null) return null;

        Map<TreeNode, TreeNode> map = new HashMap<>();
        Deque<NodeWrapper> queue = new ArrayDeque<>();
        queue.addLast(new NodeWrapper(root, null));

        while (!queue.isEmpty()) {
            NodeWrapper wrappedNode = queue.removeFirst();
            TreeNode currNode = wrappedNode._node;
            TreeNode parent = wrappedNode._parent;
            map.put(currNode, parent);

            if (currNode.left != null) queue.addLast(new NodeWrapper(currNode.left, currNode));
            if (currNode.right != null) queue.addLast(new NodeWrapper(currNode.right, currNode));
        }

        return map;
    }

    /*** SWD Soln ***/
    public TreeNode lowestCommonAncestorSWD(TreeNode root, TreeNode p, TreeNode q) {
        return searchInSubTree(root, p, q);
    }


    private TreeNode searchInSubTree(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;

        if (root.val == p.val || root.val == q.val) return root;

        TreeNode left = searchInSubTree(root.left, p, q);

        TreeNode right = searchInSubTree(root.right, p, q);

        if (left != null && right != null) return root;

        //Note this works for this question, because
        //this variation of the question guarantees that
        //p and q certainly exist in the bt.
        if (left != null) return left;
        if (right != null) return right;

        return null;
    }
}


class NodeWrapper {
    TreeNode _node;
    TreeNode _parent;

    public NodeWrapper(TreeNode node, TreeNode parent) {
        this._node = node;
        this._parent = parent;
    }
}