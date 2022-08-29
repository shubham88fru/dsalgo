package basics.bst;

//Given a bst and a key, find the
//closest larger or equal value in bst
//to the given key.
public class CeilInBST {

    //T:O(H), S:O(1)
    Node findCeil(Node root, int key) {
        if (root == null) return null;
        Node curr = root;
        Node res = null;
        while (curr != null) {
            if (curr.key == key) return curr;
            else if (key < curr.key) {
                //when we see a node greater than
                //key, record it. This is our last closest
                //seen value.
                res = curr;
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        CeilInBST ceilInBST =
                new CeilInBST();

        Node root = new Node(10);
        root.left = new Node(5);
        root.right = new Node(15);
        root.right.left = new Node(12);
        root.right.right = new Node(30);

        Node root2 = new Node(50);
        root2.left = new Node(30);
        root2.left.left = new Node(20);
        root2.left.right = new Node(40);
        root2.right = new Node(70);
        root2.right.left = new Node(60);
        root2.right.left.left = new Node(55);
        root2.right.left.right = new Node(65);
        root2.right.right = new Node(80);

        System.out.println(ceilInBST
                .findCeil(root, 14));
        System.out.println(ceilInBST
                .findCeil(root, 3));
        System.out.println(ceilInBST
                .findCeil(root2, 58));
        System.out.println(ceilInBST
                .findCeil(root2, 35));
        System.out.println(ceilInBST
                .findCeil(root2, 63));
    }
}
