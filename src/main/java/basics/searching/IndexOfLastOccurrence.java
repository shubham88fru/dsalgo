package basics.searching;

//array is sorted.
public class IndexOfLastOccurrence {

    //Naive
    //T: O(N), S:(1)
    int lastIndexOf(int[] arr, int el) {
        for (int i = arr.length-1; i>=0; i--) {
            if (arr[i] == el) return i;
        }
        return -1;
    }

    //Iterative, using binary search
    //T:O(logn)
    int lastIndexOf2(int[] arr, int el) {
        int start = 0;
        int end = arr.length - 1;
        int mid;
        while(start<=end) {
            mid = (start+end) / 2;
            if (arr[mid]==el) {
                if (mid == arr.length-1) return mid;
                else if (arr[mid + 1] == el) {
                    start = mid + 1;
                    continue;
                }
                return mid;
            } else if(el < arr[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }

    //recursive
    //T: O(N), S:O(N)
    int lastIndexOf3(int[] arr, int start, int end, int el) {
        int mid = (start+end) / 2;
        if ((start>end)) return -1;
        if (arr[mid] == el) {
            if (mid == arr.length-1) return mid;
            if (arr[mid + 1] == el) {
                start = mid + 1;
            } else {
                return mid;
            }
        } else if (el < arr[mid]) {
            end = mid - 1;
        } else {
            start = mid + 1;
        }
        return lastIndexOf3(arr, start, end, el);
    }

    public static void main(String[] args) {
        IndexOfLastOccurrence index = new IndexOfLastOccurrence();
        System.out.println(index.lastIndexOf(new int[]{5, 10, 10, 10, 10, 20, 20}, 10));
        System.out.println(index.lastIndexOf2(new int[]{5, 10, 10, 10, 10, 20, 20}, 10));
        System.out.println(index.lastIndexOf3(new int[]{5, 10, 10, 10, 10, 20, 20}, 0, 6, 10));
    }
}
