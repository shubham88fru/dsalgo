package basics.bst;

public class InsertInABST {

    //T: O(Height), S: O(Height)
    Node insertRecursive(Node root, int key) {
        if (root==null) return new Node(key);
        if (key<root.key) root.left =  insertRecursive(root.left, key);
        else if (key>root.key) root.right =  insertRecursive(root.right, key);
        return root;
    }

    //T: O(Height), O(1)
    Node insertIterative(Node root, int key) {
        Node temp = new Node(key);
        Node parent = null, curr = root;
        while (curr!=null) {
            parent = curr; //so that we can maintain a ref to leaf node.
            if (key<curr.key) {
                curr = curr.left;
            } else if (key>curr.key) {
                curr = curr.right;
            } else {
                return root;
            }
        }
        if (parent==null)
            return temp;
        if (key< parent.key)
            parent.right = temp;
        else parent.left = temp;

        return root;
    }

    public static void main(String[] args) {
        InsertInABST insert =
                new InsertInABST();

        Node root = new Node(10);
        root.left = new Node(5);
        root.right = new Node(15);
        root.right.left = new Node(12);
        root.right.right = new Node(18);
    }
}
