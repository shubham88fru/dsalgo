package ptrn.trees.dfs;

import java.util.*;

//@link - https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/description/
//@strvr - https://takeuforward.org/data-structure/lowest-common-ancestor-for-two-given-nodes/
public class LowestCommonAncestorInBT {

    //1) My sol1 (works but crappy)
    /*****************************************************************************/
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
        Deque<NodeWrapper22> queue = new ArrayDeque<>();
        queue.addLast(new NodeWrapper22(root, null));

        while (!queue.isEmpty()) {
            NodeWrapper22 wrappedNode = queue.removeFirst();
            TreeNode currNode = wrappedNode._node;
            TreeNode parent = wrappedNode._parent;
            map.put(currNode, parent);

            if (currNode.left != null) queue.addLast(new NodeWrapper22(currNode.left, currNode));
            if (currNode.right != null) queue.addLast(new NodeWrapper22(currNode.right, currNode));
        }

        return map;
    }
    /*****************************************************************************/

    //2) My sol 2 (kinda brute forceish)
    /*****************************************************************************/
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> ppath = new ArrayList<>();
        List<TreeNode> qpath = new ArrayList<>();

        //run two dfs for each nodes and get paths to each
        //node in a list.
        dfs(root, p, new ArrayList<>(), ppath);
        dfs(root, q, new ArrayList<>(), qpath);

        //take the paths and find the lowest common value.
        return lowestCommon(ppath, qpath);
    }

    private void dfs(TreeNode root, TreeNode node, List<TreeNode> curr, List<TreeNode> path) {
        if (root == null) return ;
        curr.add(root);
        if (root.val == node.val) {
            for (TreeNode n: curr) {
                path.add(n);
            }
        }


        dfs(root.left, node, curr, path);
        dfs(root.right, node, curr, path);
        curr.remove(curr.size()-1);
    }

    private TreeNode lowestCommon(List<TreeNode> ppath, List<TreeNode> qpath) {
        List<TreeNode> smaller = ppath.size() < qpath.size() ? ppath: qpath;
        List<TreeNode> larger = ppath.size() >= qpath.size() ? ppath: qpath;

        int s = smaller.size()-1;
        while (s >= 0) {
            if (larger.contains(smaller.get(s))) return smaller.get(s);
            s -= 1;
        }

        return null;

    }
    /*****************************************************************************/

    //3) SWD's soln
    /*****************************************************************************/
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
    /*****************************************************************************/
}


class NodeWrapper22 {
    TreeNode _node;
    TreeNode _parent;

    public NodeWrapper22(TreeNode node, TreeNode parent) {
        this._node = node;
        this._parent = parent;
    }
}