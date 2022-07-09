package recursion;

public class Print1ToN {

    //T:O(N), S:O(N)
    static void print1ToN(int n) {
        if (n==0) return;
        print1ToN(n-1);
        System.out.print(n+" ");
    }

    static void print1ToNTailRecursive(int n, int k) {
        if (n==0) return;
        System.out.print(k+" ");
        print1ToNTailRecursive(n-1, k+1);
    }

    public static void main(String[] args) {
        print1ToN(9);
        System.out.println();
        print1ToNTailRecursive(9, 1); //k must be called with 1
    }
}
