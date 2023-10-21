package strvr.bst2;


//Given a bst and a key, find the
//closest larger or equal value in bst
//to the given key.
//@link - https://practice.geeksforgeeks.org/problems/implementing-ceil-in-bst/0
//@strvr - https://www.youtube.com/watch?v=KSsk8AhdOZA&ab_channel=takeUforward
public class CeilInBST {

    //T:O(H), S:O(1)
    GfgNode findCeil(GfgNode root, int key) {
        if (root == null) return null;
        GfgNode curr = root;
        GfgNode res = null;
        while (curr != null) {
            if (curr.data == key) return curr;
            else if (key < curr.data) {
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

        GfgNode root = new GfgNode(10);
        root.left = new GfgNode(5);
        root.right = new GfgNode(15);
        root.right.left = new GfgNode(12);
        root.right.right = new GfgNode(30);

        GfgNode root2 = new GfgNode(50);
        root2.left = new GfgNode(30);
        root2.left.left = new GfgNode(20);
        root2.left.right = new GfgNode(40);
        root2.right = new GfgNode(70);
        root2.right.left = new GfgNode(60);
        root2.right.left.left = new GfgNode(55);
        root2.right.left.right = new GfgNode(65);
        root2.right.right = new GfgNode(80);

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
