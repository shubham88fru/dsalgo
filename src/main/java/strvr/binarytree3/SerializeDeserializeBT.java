package strvr.binarytree3;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Optional;

//@link - https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
//@strvr - https://takeuforward.org/data-structure/serialize-and-deserialize-a-binary-tree/
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
