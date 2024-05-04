package ptrn.trees.dfs;

import java.util.*;

//@link - https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
//@strvr - https://takeuforward.org/data-structure/serialize-and-deserialize-a-binary-tree/
//@link - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/4656843298439168

//1) Strvr soln - using BFS
public class SerializeDeserializeBT {
    // Encodes a tree to a single string.
    /*
        This problem can be solved by serializing in mutliple ways - inorder, preorder etc
        but serialzing in level order is by far the easiest way
    */
    public String serialize(TreeNode root) {
        return ser(root);
    }

    //Follow level order traversal and add all the elements
    //to a string. Adding # to indicate null nodes. Using Optional
    //here because the addLast/removeLast APIs don't accept null values
    //so tricking it by wrapping the null in an optional.
    private String ser(TreeNode root) {
        if (root == null) return "#";
        StringBuilder op = new StringBuilder();
        Deque<Optional<TreeNode>> q = new ArrayDeque<>();
        q.addLast(Optional.ofNullable(root));

        while (!q.isEmpty()) {
            int sz = q.size();
            while (sz > 0) {
                TreeNode node = q.removeFirst().orElse(null);
                if (node != null) {
                    op.append(node.val + ",");
                    q.addLast(Optional.ofNullable(node.left));
                    q.addLast(Optional.ofNullable(node.right));
                } else op.append("#" + ",");
                sz -= 1;
            }
        }

        return op.toString();
    }

    // Decodes your encoded data to tree.
    //In a level order traversal string, we know as we iterate,
    //next two elements will be children of current.
    public TreeNode deserialize(String data) {
        System.out.println(data);
        String[] arr = data.split(",");
        //edge case - null
        if (arr[0].equals("#")) return null;

        //add root to queue.
        Deque<TreeNode> q = new ArrayDeque<>();
        TreeNode root = new TreeNode(Integer.parseInt(arr[0]));
        q.addLast(root);

        //notice, running in batch of 2.
        for (int i=1; i<arr.length;i+=2) {
            if (q.isEmpty()) continue;
            //starting with root, we'll keep adding next two
            //element in the string as left and right child of curr.
            //At the same time, we'll add the left and right to queue as
            //well so they'll become current' in next iteration
            TreeNode curr = q.removeFirst();
            int idx = i;
            if (!arr[idx].equals("#")) {
                TreeNode left = new TreeNode(Integer.parseInt(arr[idx]));
                curr.left = left;
                q.addLast(left);
            }

            idx += 1;
            if (!arr[idx].equals("#")) {
                TreeNode right = new TreeNode(Integer.parseInt(arr[idx]));
                curr.right = right;
                q.addLast(right);
            }
        }

        return root;
    }
}

//2) Edctv soln - using Preorder DFS soln.
class SerializeDeserialize {
    // Initializing our marker as the max possible int value
    private static final String MARKER = "M";
    private static int m = 1;

    private static void serializeRec(EdctvTreeNode<Integer> node, List<String> stream) {
        if (node == null) {
            String s = Integer.toString(m);
            stream.add(MARKER + s);
            m = m + 1;
            return;
        }

        stream.add(String.valueOf(node.data));

        serializeRec(node.left, stream);
        serializeRec(node.right, stream);
    }

    // Function to serialize tree into a list.
    public static List<String> serialize(EdctvTreeNode<Integer> root) {
        List<String> stream = new ArrayList<>();
        serializeRec(root, stream);
        return stream;
    }

    public static EdctvTreeNode<Integer> deserializeHelper(List<String> stream) {
        String val = stream.remove(stream.size() - 1);

        if (val.charAt(0) == MARKER.charAt(0)) {
            return null;
        }

        EdctvTreeNode<Integer> node = new EdctvTreeNode<Integer>(Integer.parseInt(val));

        node.left = deserializeHelper(stream);
        node.right = deserializeHelper(stream);

        return node;
    }

    // Function to deserialize list into a binary tree.
    public static EdctvTreeNode<Integer> deserialize(List<String> stream) {
        Collections.reverse(stream);
        EdctvTreeNode<Integer> node = deserializeHelper(stream);
        return node;
    }
}


class EdctvTreeNode<T> {
    T data;
    EdctvTreeNode<T> left;
    EdctvTreeNode<T> right;

    EdctvTreeNode(T data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}