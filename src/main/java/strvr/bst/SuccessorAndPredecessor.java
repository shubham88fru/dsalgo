package strvr.bst;

//@link - https://practice.geeksforgeeks.org/problems/predecessor-and-successor/1
//@strvr - https://www.youtube.com/watch?v=SXKAD2svfmI&ab_channel=takeUforward
public class SuccessorAndPredecessor {
    static GfgNode pre = null;
    static GfgNode suc = null;
    public static void findPreSuc(GfgNode root, int key)
    {
        // code here.
        bstInorderSuccessor(root, key);
        bstInorderPre(root, key);
    }

    private static void bstInorderSuccessor(GfgNode root, int key) {
        if (root == null) return;
        //if (root.val == key) return root;

        if (root.data <= key) {
            bstInorderSuccessor(root.right, key);
        } else {
            suc = root;
            bstInorderSuccessor(root.left, key);
        }
    }

    private static void bstInorderPre(GfgNode root, int key) {
        if (root == null) return;
        //if (root.val == key) return root;

        if (root.data >= key) {
            bstInorderPre(root.left, key);
        } else {
            pre = root;
            bstInorderPre(root.right, key);
        }
    }
}
