package basics.binary_tree;

public class MaximumInBT {

    //T: O(N), S: O(Height)
    int findMaxInBT(Node root) {
        if (root==null) return Integer.MIN_VALUE;
        else
            //compare root with max in left subtree and max in right subtree.
            return Math.max(root.key, Math.max(findMaxInBT(root.left), findMaxInBT(root.right)));
    }

    public static void main(String[] args) {
        MaximumInBT maximum =
                new MaximumInBT();

        Node root1 = new Node(10);
        root1.left = new Node(80);
        root1.right = new Node(70);
        root1.left.left = new Node(40);
        root1.left.right = new Node(50);

        System.out.println(maximum.findMaxInBT(root1));
    }
}
