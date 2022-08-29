package basics.bst;

public class DeleteFromBST {

    //1) If key to be deleted is leaf node, then
    //deletion is straight forward.
    //2) But otherwise, we need to be  extra careful
    //so that after deletion, the tree should still remain
    //a BST.

    //T: O(H), S:O(H)
    Node delete(Node root, int key) {
        //when root null, don't do anything.
        if (root == null) return null;

        //find the key
        if (key<root.key)
            root.left = delete(root.left, key);
        else if (key>root.key)
            root.right = delete(root.right, key);

        //only when root.key == key.
        else {
            /*CASE: When one of  left or right on the node to be deleted is null*/

            //if left child is null, return right node
            //so that it can be made the child of the caller.
            if (root.left==null) return root.right;

            //else if right child is null, return left node
            // so that it can be made the child of the caller.
            else if (root.right==null) return root.left;

           /*CASE: When node to be deleted has both children non-null*/
            else {
                Node successor = getSuccessor(root);

                //replace the node to be deleted with
                //successor
                root.key = successor.key;

                //set right child of node to a new subtree
                //which has the successor deleted (since we have
                //already moved the successor in prev step)
                root.right = delete(root.right, successor.key);
            }
        }
        return root;
    }

    Node getSuccessor(Node root) {
        //We are selecting the closes greater value
        //of the node to be deleted as the successor.

        //Going right, would mean any subtree would certainly be
        //greater than the node, and to find the closest greater,
        //we go left left left till leaf node, because it will be the
        //smallest greater value than the node which is to be deleted.
        Node curr = root.right;
        while (curr!=null && curr.left!=null)
            curr = curr.left;
        return curr;
    }

    public static void main(String[] args) {
        DeleteFromBST delete =
                new DeleteFromBST();

        Node root = new Node(10);
        root.left = new Node(5);
        root.right = new Node(15);
        root.right.left = new Node(12);
        root.right.right = new Node(18);
    }
}
