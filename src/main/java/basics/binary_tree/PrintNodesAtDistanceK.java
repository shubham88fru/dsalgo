package basics.binary_tree;

//Given a BT and integer k, find nodes that
//are at distance k from the root.
public class PrintNodesAtDistanceK {

    //Works like a charm, but maybe doesn't
    //handle edge case perfectly.
    void nodesAtDistanceKMy(Node root, int k) {
        if (root != null) {
            if (k == 0) System.out.print(root.key + " ");
            nodesAtDistanceKMy(root.left, k - 1);
            nodesAtDistanceKMy(root.right, k - 1);
        }

    }



    //T: O(N), S: Theta(Height)
    void nodesAtDistanceKSir(Node root, int k) {
        //if root is null, don't have to print anything.
        if (root == null) return;

        //if k is zero, print the root.
        if (k == 0) System.out.print(root.key + " ");

        else {
            nodesAtDistanceKSir(root.left, k - 1);
            nodesAtDistanceKSir(root.right, k - 1);
        }
    }

    public static void main(String[] args) {
        Node root = new Node(10);
        root.left = new Node(20);
        root.right = new Node(30);
        root.left.left = new Node(40);
        root.left.right = new Node(50);
        root.right.right = new Node(70);
        root.right.right.right = new Node(80);

        PrintNodesAtDistanceK nodesAtDistanceKMy
                = new PrintNodesAtDistanceK();
        nodesAtDistanceKMy.nodesAtDistanceKMy(root, 3);
        System.out.println();
        System.out.println("------------");
        nodesAtDistanceKMy.nodesAtDistanceKMy(root, 2);
        System.out.println();
        System.out.println("------------");
        nodesAtDistanceKMy.nodesAtDistanceKMy(root, 1);
        System.out.println();

        System.out.println("------SIR------");
        PrintNodesAtDistanceK nodesAtDistanceKSir
                = new PrintNodesAtDistanceK();
        nodesAtDistanceKSir.nodesAtDistanceKSir(root, 3);
        System.out.println();
        System.out.println("------------");
        nodesAtDistanceKSir.nodesAtDistanceKSir(root, 2);
        System.out.println();
        System.out.println("------------");
        nodesAtDistanceKSir.nodesAtDistanceKSir(root, 1);
    }
}
