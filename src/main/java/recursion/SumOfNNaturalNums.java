package recursion;

public class SumOfNNaturalNums {

    static int sumOfNNaturalNums(int n) {
        if (n==0) return 0;
        return n+sumOfNNaturalNums(n-1);
    }
    static int sumOfNNaturalNumsTailRecursive(int n, int sum) {
        if (n==0) return sum;
        return sumOfNNaturalNumsTailRecursive(n-1, sum+n);
    }

    public static void main(String[] args) {
        System.out.println(sumOfNNaturalNums(0));
        System.out.println(sumOfNNaturalNums(10));
        System.out.println(sumOfNNaturalNums(12));

        System.out.println("-----------------------------");

        System.out.println(sumOfNNaturalNumsTailRecursive(0, 0));
        System.out.println(sumOfNNaturalNumsTailRecursive(10, 0));
        System.out.println(sumOfNNaturalNumsTailRecursive(12, 0));
    }
}
