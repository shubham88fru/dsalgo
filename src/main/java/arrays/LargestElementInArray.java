package arrays;

public class LargestElementInArray {
    //Naive approach
    //T: O(N^2)
     static int getLargestElIndex(int arr[]) {
        int n = arr.length;
        for (int i = 0; i<n; i++) {
            boolean flag = true;
            for (int j = 0; j<n; j++) {
                if (arr[j] > arr[i]) {
                    flag = false;
                    break;
                }
            }
            if (flag) return i;
        }
        return -1;
    }

    //T: O(N), infact Theta(N)
    static int getLargestElIndex2(int arr[]) {
        int n = arr.length;
        int largestIdx = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= arr[largestIdx]) largestIdx = i;
        }
        return largestIdx;
    }

    public static void main(String[] args) {
        int arr[] = { 21, 2 ,300, 42, 5};
        System.out.println(getLargestElIndex(arr));
        System.out.println(getLargestElIndex2(arr));
    }
}
