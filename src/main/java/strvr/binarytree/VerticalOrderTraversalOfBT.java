package strvr.binarytree;

import java.util.*;

//@link - https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/description/
//@strvr - https://takeuforward.org/data-structure/vertical-order-traversal-of-binary-tree/
public class VerticalOrderTraversalOfBT {
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        Map<Integer, PriorityQueue<NodeWrapper>> tmap = bfsForVerticalOrder(root);

        //once we get the the data in desired datastructre.
        //iterate over the map and return the list.
        for (Map.Entry<Integer, PriorityQueue<NodeWrapper>> entry:  tmap.entrySet()) {
            List<Integer> lst = new ArrayList<>();
            PriorityQueue<NodeWrapper> pq = entry.getValue();
            while (!pq.isEmpty()) {
                lst.add(pq.remove().node.val);
            }
            ans.add(lst);
        }

        return ans;
    }

    private Map<Integer, PriorityQueue<NodeWrapper>> bfsForVerticalOrder(TreeNode root) {
        //Treemap to store a priorty queue (sorted wrt depth->level->value) against each
        //vertical level in a sorted fashion. This entire treemap is what we will return
        //from this method, after populating it correctly.
        Map<Integer, PriorityQueue<NodeWrapper>> tmap = new TreeMap<>();

        Deque<NodeWrapper> q = new ArrayDeque<>();
        q.addLast(new NodeWrapper(root, 0, 0));

        while (!q.isEmpty()) {
            NodeWrapper nw = q.removeFirst();

            //If seeing a vertical level for the first time, initialize.
            if (!tmap.containsKey(nw.level)) {

                //comparators for inserts in desired fashion.
                Comparator<NodeWrapper> cmp = Comparator.comparingInt(nw2 -> nw2.depth);
                PriorityQueue<NodeWrapper> arr = new PriorityQueue<>(cmp.thenComparingInt(nw2 -> nw2.level).thenComparingInt(nw2 -> nw2.node.val));
                arr.add(nw);
                tmap.put(nw.level, arr);
            } else {
                tmap.get(nw.level).offer(nw);
            }


            //typical bfs.
            if (nw.node.left != null) {
                //when moving left child. level-1 and depth+1;
                q.addLast(new NodeWrapper(nw.node.left, nw.level-1, nw.depth+1));
            }

            if (nw.node.right != null) {
                //when moving right child. level+1 and depth+1;
                q.addLast(new NodeWrapper(nw.node.right, nw.level+1, nw.depth+1));
            }
        }

        return tmap;

    }
}

//a utility class that will store
//useful information for each node.
//like the level (vertical), and depth.
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