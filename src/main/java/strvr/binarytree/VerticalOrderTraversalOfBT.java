package strvr.binarytree;

import java.util.*;
import java.util.stream.Collectors;

public class VerticalOrderTraversalOfBT {
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        Map<Integer, List<NodeWrapper>> tmap = bfsForVerticalOrder(root);

        for (Map.Entry<Integer, List<NodeWrapper>> entry:  tmap.entrySet()) {
            List<Integer> lst = new ArrayList<>();
            lst = entry.getValue().stream().map((ent) -> ent.node.val).collect(Collectors.toList());
            ans.add(lst);
        }

        return ans;
    }

    private Map<Integer, List<NodeWrapper>> bfsForVerticalOrder(TreeNode root) {
        Map<Integer, List<NodeWrapper>> tmap = new TreeMap<>();
        Deque<NodeWrapper> q = new ArrayDeque<>();
        q.addLast(new NodeWrapper(root, 0, 0));

        while (!q.isEmpty()) {
            NodeWrapper nw = q.removeFirst();
            if (!tmap.containsKey(nw.level)) {

                List<NodeWrapper> arr = new ArrayList<>();
                arr.add(nw);
                tmap.put(nw.level, arr);
            } else {
                Comparator<NodeWrapper> cmp = Comparator.comparingInt(nw2 -> nw2.depth);
                tmap.get(nw.level).add(nw);
                tmap.get(nw.level).sort(cmp.thenComparing(nw2 -> nw2.level).thenComparingInt(nw2 -> nw2.node.val));
                //System.out.println(tmap);
            }


            if (nw.node.left != null) {
                q.addLast(new NodeWrapper(nw.node.left, nw.level-1, nw.depth+1));
            }

            if (nw.node.right != null) {
                q.addLast(new NodeWrapper(nw.node.right, nw.level+1, nw.depth+1));
            }
        }

        return tmap;

    }
}

class NodeWrapper {
    TreeNode node;
    int level;
    int depth;

    public NodeWrapper(TreeNode node, int level, int depth) {
        this.node = node;
        this.level = level;
        this.depth = depth;
    }

    public String toString() {
        return "[" + node.val + ", " + level + ", " + depth + "]";
    }
}