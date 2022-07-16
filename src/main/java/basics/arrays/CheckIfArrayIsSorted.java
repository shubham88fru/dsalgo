package basics.arrays;

public class CheckIfArrayIsSorted {
    //Naive soln.
    //T: O(N^2)
    static boolean isSorted(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j ++) {
                if (arr[i] > arr[j]) {
                    return false;
                }
            }
        }
        return true;
    }

    //T: O(N)
    static boolean isSorted2(int arr[]) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i-1]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isSorted(new int[]{17, 18, 90, 100}));
        System.out.println(isSorted2(new int[]{77, 18, 90, 100}));
    }
}
