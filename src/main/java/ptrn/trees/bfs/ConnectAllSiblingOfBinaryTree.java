package ptrn.trees.bfs;

import java.util.*;

//@link - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6328543824052224
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6328543824052224

/**
 * Given the root of a perfect binary tree, where each node is equipped with an additional pointer,
 * next, connect all nodes from left to right. Do so in such a way that the next pointer of each node
 * points to its immediate right sibling except for the rightmost node, which points to the first node of the next level.
 * The next pointer of the last node of the binary tree (i.e., the rightmost node of the last level) should be set to NULL.
 *
 * Slight modification of @see {@link ptrn.trees.bfs.PopulateNextRightPointerInEachNode}
 */
public class ConnectAllSiblingOfBinaryTree {
    public static EduTreeNode<Integer> connectAllSiblings(EduTreeNode<Integer> root) {
        //bfs(root);
        optimal(root);
        return root;
    }

    //1) Optimized approach - Leverage the fact
    //that tis a perfect/balanced tree.
    private static void optimal(EduTreeNode<Integer> root) {
        if (root == null) return;
        if (root.left == null) return;
        EduTreeNode<Integer> level = root;
        EduTreeNode<Integer> curr = null;
        while (level.left != null) {
            if (curr != null) curr.next = level;
            curr = level;
            while (true) {
                curr.left.next = curr.right;
                if (curr.next != null) {
                    curr.right.next = curr.next.left;
                    curr = curr.next;
                } else break;
            }
            level = level.left;
        }

        //link the second last level
        //to the last level.
        curr.next = level;
    }

    //2) Simple BFS
    private static void bfs(EduTreeNode<Integer> root) {
        if (root == null) return;
        Deque<EduTreeNode<Integer>> q = new ArrayDeque<>();
        EduTreeNode<Integer> prev = null;
        q.addLast(root);

        while (!q.isEmpty()) {
            EduTreeNode<Integer> curr = q.removeFirst();
            int sz = q.size();
            if (prev != null) prev.next = curr;
            prev = curr;
            if (curr.left != null) q.addLast(curr.left);
            if (curr.right != null) q.addLast(curr.right);

            while (sz > 0) {
                EduTreeNode<Integer> next = q.removeFirst();
                prev.next = next;
                prev = next;
                if (next.left != null) q.addLast(next.left);
                if (next.right != null) q.addLast(next.right);

                sz -= 1;
            }
        }
    }
}

////////////////////Edctv internal////////////////////////////
class EduTreeNode<T> {
    T data;
    EduTreeNode<T> left;
    EduTreeNode<T> right;
    EduTreeNode<T> next;

    EduTreeNode(T data) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.next = null;
    }
}

class EduBinaryTree<T> {
    EduTreeNode<T> root;

    EduBinaryTree(List<EduTreeNode<T>> ListOfNodes) {
        root = createBinaryTree(ListOfNodes);
    }

    private EduTreeNode<T> createBinaryTree(List<EduTreeNode<T>> ListOfNodes) {
        if (ListOfNodes.isEmpty()) {
            return null;
        }

        // Create the root node of the binary tree
        EduTreeNode<T> root = new EduTreeNode<>(ListOfNodes.get(0).data);

        // Create a queue and add the root node to it
        Queue<EduTreeNode<T>> q = new LinkedList<>();
        q.add(root);

        // Start iterating over the list of ListOfNodes starting from the second node
        int i = 1;
        while (i < ListOfNodes.size()) {
            // Get the next node from the queue
            EduTreeNode<T> curr = q.remove();

            // If the node is not null, create a new EduTreeNodeobject for its left child,
            // set it as the left child of the current node, and add it to the queue
            if (ListOfNodes.get(i) != null) {
                curr.left = new EduTreeNode<>(ListOfNodes.get(i).data);
                q.add(curr.left);
            }

            i++;

            // If there are more ListOfNodes in the list and the next node is not null,
            // create a new EduTreeNode object for its right child, set it as the right child
            // of the current node, and add it to the queue
            if (i < ListOfNodes.size() && ListOfNodes.get(i) != null) {
                curr.right = new EduTreeNode<>(ListOfNodes.get(i).data);
                q.add(curr.right);
            }

            i++;
        }

        // Return the root of the binary tree
        return root;
    }
}
////////////////////Edctv internal////////////////////////////