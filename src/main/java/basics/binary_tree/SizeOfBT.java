package basics.binary_tree;

//count the no. of nodes
public class SizeOfBT {

    //My soln.
    int size2(Node root) {
        //do traversal, and increment count on
        //each iteration.
        return traverse(root, 0);
    }

    private int traverse(Node root, int initCount) {
        if (root == null) return initCount;
        initCount++;
        initCount = traverse(root.left, initCount);
        initCount = traverse(root.right, initCount);
        return initCount;
    }

    //Sir's soln.
    //T: O(N), S:O(Height)
    int size(Node root) {
        if (root == null) return 0;
        else return 1 + size(root.left) + size(root.right);
    }

    public static void main(String[] args) {
        SizeOfBT size = new SizeOfBT();

        Node root1 = new Node(10);
        root1.left = new Node(80);
        root1.right = new Node(70);
        root1.left.left = new Node(40);
        root1.left.right = new Node(50);

        Node root2 = new Node(80);
        root2.left = new Node(70);
        root2.left.left = new Node(60);

        System.out.println(size.size(root1));
        System.out.println(size.size2(root1));
        System.out.println("----------------");
        System.out.println(size.size(root2));
        System.out.println(size.size2(root2));
    }
}
