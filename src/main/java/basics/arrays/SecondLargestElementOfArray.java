package basics.arrays;

public class SecondLargestElementOfArray {
    //Naive sol (two traversals)
    //T: O(N)
    //doesn't work if multiple same largest elements in array
    static int secondLargestElementIndex(int arr[]) {
        int largestIdx = LargestElementInArray.getLargestElIndex2(arr);
        arr[largestIdx] = Integer.MIN_VALUE;
        return LargestElementInArray.getLargestElIndex2(arr);
    }

    //Naive sol (sir) (two traversals)
    //T: O(N)
    static int secondLargestElementIndexSir(int arr[]) {
        int largestIdx = LargestElementInArray.getLargestElIndex2(arr);
        int res = -1; // In case there's no second largest, need to return -1
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != arr[largestIdx]) {
                if (res == -1) res = i;
                else if (arr[i] > arr[res]) res = i;
            }
        }
        return res;
    }

    //one traversal
    //T: O(N) or Theta(N), S: Theta(1)
    static int secondLargestElementIndex3(int arr[]) {
        int res = -1, largest = 0; // res --> second largest
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[largest]) {
                // set second largest idx = prev largest idx (i.e. res)
                // and new largest idx = new idx.
                res = largest;
                largest = i;
            } else if (arr[i] != arr[largest]) {
                if (res == -1 || arr[i] > arr[res]) res = i;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(secondLargestElementIndex(new int[]{10, 20, 14, 4}));
        System.out.println(secondLargestElementIndexSir(new int[]{10, 20, 14, 4}));
        System.out.println(secondLargestElementIndexSir(new int[]{10, 10, 10}));
        System.out.println(secondLargestElementIndex3(new int[]{10, 10, 10}));
        System.out.println(secondLargestElementIndex3(new int[]{10, 20, 14, 4}));
    }
}
