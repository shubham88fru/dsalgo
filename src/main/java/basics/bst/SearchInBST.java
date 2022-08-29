package basics.bst;

//A typical node in a
//binary tree.
class Node {
    int key;
    Node left;
    Node right;

    Node(int k) {
        key = k;
    }

    @Override
    public String toString() {
        return "key="+key+"left="+left+"right="+right;
    }
}

public class SearchInBST {


    /*
        Note that `Height` of a binary search
        tree can easily become `n` (no. of nodes)
        if it is completely unbalanced. And so the
        Time complexity of search will become O(N).
        Else if binary search tree is balanced, best case
        time complexity of search can become O(logn)
     */
    //T: O(Height), S: O(Height)
    boolean searchRecursive(Node root, int key) {
        if (root == null) return false;
        else if (key == root.key) return true;
        else if (key < root.key) return searchRecursive(root.left, key);
        else return searchRecursive(root.right, key);
    }

    //T: O(Height), S: O(1)
    boolean searchIterative(Node root, int key) {
        Node curr = root;
        while (curr != null) {
            if (key == curr.key) return true;
            else if (key < curr.key) curr = curr.left;
            else curr = curr.right;
        }
        return false;
    }

    public static void main(String[] args) {
        SearchInBST search =
                new SearchInBST();

        Node root = new Node(15);
        root.left = new Node(5);
        root.right = new Node(20);
        root.left.left = new Node(3);
        root.right.left = new Node(18);
        root.right.right = new Node(80);
        root.right.left.left = new Node(16);

        System.out.println(
                search.searchRecursive(root,
                        16));
        System.out.println(
                search.searchRecursive(root,
                        15));
        System.out.println(
                search.searchRecursive(root,
                        160));

        System.out.println("---------------");

        System.out.println(
                search.searchIterative(root,
                        16));
        System.out.println(
                search.searchIterative(root,
                        15));
        System.out.println(
                search.searchIterative(root,
                        160));
    }
}
