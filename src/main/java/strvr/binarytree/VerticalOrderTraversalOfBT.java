package strvr.binarytree;

import java.util.*;

//@link - https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/description/
//@strvr - https://takeuforward.org/data-structure/vertical-order-traversal-of-binary-tree/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6157238315253760
public class VerticalOrderTraversalOfBT {

    //1) Strvr's soln.
    ////////////////////////////////////////////////////////////////////////////////////////
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        Map<Integer, PriorityQueue<NodeWrapper>> tmap = bfsForVerticalOrder(root);

        //once we get the data in desired data structure.
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
        //Treemap to store a priority queue (sorted wrt depth->level->value) against each
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
    ////////////////////////////////////////////////////////////////////////////////////////

    //2) My soln - kinda on the lines of strvr only.
    ////////////////////////////////////////////////////////////////////////////////////////
    public List<List<Integer>> verticalTraversal2(TreeNode root) {
        return bfsSuboptimal(root);
    }


    private List<List<Integer>> bfsSuboptimal(TreeNode root) {
        if (root == null) return null;

        //Map to store the vertical index and all nodes at that index.
        //We use treemp because it will store the keys in order, and
        //as per question we have to return from left to right.
        TreeMap<Integer, List<NodeWrapper3>> tm = new TreeMap<>();
        Deque<NodeWrapper3> q = new ArrayDeque<>();

        q.addLast(new NodeWrapper3(root, 0, 0));

        while (!q.isEmpty()) {
            NodeWrapper3 wrapper = q.removeFirst();
            int val = wrapper.node.val;
            int row = wrapper.row;
            int col = wrapper.col;
            if (!tm.containsKey(col)) {
                tm.put(col, new ArrayList<>());
            }
            tm.get(col).add(wrapper);
            if (wrapper.node.left != null) {
                q.addLast(new NodeWrapper3(wrapper.node.left, row+1, col-1));
            }

            if (wrapper.node.right != null) {
                q.addLast(new NodeWrapper3(wrapper.node.right, row+1, col+1));
            }
        }

        //Once the treemap is populated,
        //get the ans.
        List<List<Integer>> ans = new ArrayList<>();
        for (Map.Entry<Integer, List<NodeWrapper3>> entry: tm.entrySet()) {
            //When iterating over the entries of the treemap, although the entries
            //will be in order of their vertical index (left to right), but the list corresponding
            //to each entry may have values which are not sorted as required by question.
            //So, we'll sort the values based on their depth and if the depth is same i.e. two
            //values for a vertical index at same level/depth, we'll sort them in increasing order (ATQ).
            Comparator<NodeWrapper3> rowCmp = (nw1, nw2) -> nw1.row - nw2.row;
            Comparator<NodeWrapper3> colCmp = (nw1, nw2) -> nw1.node.val - nw2.node.val;
            Collections.sort(entry.getValue(), rowCmp.thenComparing(colCmp));
            List<Integer> lst = new ArrayList<>();
            for (NodeWrapper3 nw: entry.getValue()) {
                lst.add(nw.node.val);
            }
            ans.add(lst);
        }

        return ans;
    }
    ////////////////////////////////////////////////////////////////////////////////////////

    //3) Edctv soln.
    //Edctv has made the problem very easy. Per their version of the problem,
    //we if for a vertical index, there are more than one values at the same row,
    //we just need the return values from left to right.
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

class NodeWrapper3 {
    TreeNode node;
    int row;
    int col;
    public NodeWrapper3(TreeNode node, int row, int col) {
        this.node = node;
        this.row = row;
        this.col = col;
    }
}