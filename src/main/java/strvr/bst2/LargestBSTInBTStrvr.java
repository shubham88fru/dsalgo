package strvr.bst2;

//@link - https://practice.geeksforgeeks.org/problems/largest-bst/1
//@strvr - https://www.youtube.com/watch?v=X0oXMdtUDwo&ab_channel=takeUforward
public class LargestBSTInBTStrvr {
    int largestBst(GfgNode root){
        // Write your code here
        return maxBST(root).size;

    }

    private BSTData maxBST(GfgNode root) {
        if (root == null) return new BSTData(0, Integer.MAX_VALUE, Integer.MIN_VALUE);
        //if (root.left == null && root.right == null) return new BSTData(1, root.val, root.val);

        BSTData left = maxBST(root.left);
        BSTData right = maxBST(root.right);

        //if curr node's value is more than max in left
        //and less than min in right, and we know that left and right
        //are bst's because there min and max are marked accordingly (read comments below),
        //then left, right and curr will form a bst too.
        if (root.data > left.max && root.data < right.min) {
            //left,right and curr form a valid bst.
            int size = 1+left.size+right.size; //left's size + right's size + curr node.
            return new BSTData(size, Math.min(root.data, left.min),
                    Math.max(root.data, right.max));
        } else {
            //left, right and curr don't form a valid bst.
            //In this case, we'll still keep track for the size of valid
            //bst that we have seen till now (so that we can return it later)
            //but for min and max, will mark min as INT_MIN and INT_MAX respectively
            //just that we ensure that when the parent tries to do the comparision
            //for min and max, it will fail and won't be able to form a bst with current
            //subtree. i.e. when parent compares its value with left's max, the check will
            //certainly fail because left's max is INT_MAX and nothing can be greater than that,
            //while when it compares its value with right's min, it will fail again since
            //right's min is INT_MIN and nothing can be smaller than that. Effectively, the
            //parent' won't be able to make a bst with such a subtree.
            //Note: we set the min and max to lowest and highest to mark this tree invalid bst,
            //but we still carry the max bst size that we have seen till now.
            return new BSTData(Math.max(left.size, right.size), Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
    }
}

class BSTData {
    int size;
    int min;
    int max;

    public BSTData(int size, int min, int max) {
        this.size = size;
        this.min = min;
        this.max = max;
    }
}
