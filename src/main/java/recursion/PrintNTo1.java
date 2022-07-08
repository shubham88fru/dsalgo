package recursion;

//Assume N >=1
public class PrintNTo1 {

    //T:O(N), S: O(N)
    static void printNto1(int n) {
        if (n==0) return;
        System.out.print(n+" ");
        printNto1(n-1);
    }

    public static void main(String[] args) {
        printNto1(10);
    }
}
