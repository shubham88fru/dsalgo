package basics.searching;

//Given array is sorted.
public class IndexOfFirstOccurrence {

    //T: O(N), S: (1)
    int indexOfFirstOccurrenceNaive(int[] arr, int el) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == el) return i;
        }
        return -1;
    }

    int indexOfFirstOccurrenceMyIterative(int[] arr, int el) {
        int start = 0;
        int end = arr.length - 1;
        int mid;
        while (start <= end) {
            mid = (start + end) / 2;
            if (arr[mid] == el) {
                int minIndex = mid;
                while (mid > 0) {
                    mid--;
                    if (arr[mid] == el) {
                        minIndex = mid;
                    }
                }
                return minIndex;
            } else if (el < arr[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return -1;
    }


    int indexOfFirstOccurrenceSirIterative(int[] arr, int el) {
        int start = 0;
        int end = arr.length - 1;
        int mid;
        while (start <= end) {
            mid = (start + end) / 2;
            if (arr[mid] == el) {
                if (mid==0) return mid;
                else if(arr[mid-1] == el) {
                    end = mid -1;
                    continue;
                }
                return mid;
            } else if (el < arr[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return -1;
    }

    //T: O(logn)
    int indexOfFirstOccurrenceMyRecursive(int[] arr, int low, int high, int el) {
        if (low > high) return -1;
        int mid = (low + high) / 2;
        if (arr[mid]==el) {
            if (mid==0) return mid;
            else if (arr[mid-1]==el) {
                high = mid - 1;
                return indexOfFirstOccurrenceMyRecursive(arr, low, high, el);
            }
            return mid;
        }
        else if(el < arr[mid]) high = mid - 1;
        else low = mid + 1;
        return indexOfFirstOccurrenceMyRecursive(arr, low, high, el);
    }

    //T: O(logn)
    int indexOfFirstOccurrenceSirRecursive(int[] arr, int low, int high, int el) {
        if (low>high) return -1;
        int mid = (low+high)/2;
        if(el>arr[mid]) return indexOfFirstOccurrenceSirRecursive(arr, mid+1, high, el);
        else if(el<arr[mid]) return indexOfFirstOccurrenceSirRecursive(arr, low, mid-1, el);
        else {
            if (mid==0 || arr[mid-1]!=arr[mid]) return mid;
            else return indexOfFirstOccurrenceSirRecursive(arr, low, mid-1, el);
        }
    }

    public static void main(String[] args) {
        IndexOfFirstOccurrence index = new IndexOfFirstOccurrence();

        System.out.println(index.indexOfFirstOccurrenceNaive(new int[]{1, 1, 1, 2}, 1));
        System.out.println(index.indexOfFirstOccurrenceNaive(new int[]{1, 1, 1, 2}, 2));
        System.out.println(index.indexOfFirstOccurrenceNaive(new int[]{0, 1, 1, 2}, 1));
        System.out.println(index.indexOfFirstOccurrenceNaive(new int[]{0, 0, 1, 2}, 0));
        System.out.println(index.indexOfFirstOccurrenceNaive(new int[]{1, 1, 2, 3}, 2));
        System.out.println(index.indexOfFirstOccurrenceNaive(new int[]{5, 10, 10, 15, 15}, 15));


        System.out.println("----------------------");
        System.out.println(index.indexOfFirstOccurrenceMyIterative(new int[]{1, 1, 1, 2}, 1));
        System.out.println(index.indexOfFirstOccurrenceMyIterative(new int[]{1, 1, 1, 2}, 2));
        System.out.println(index.indexOfFirstOccurrenceMyIterative(new int[]{0, 1, 1, 2}, 1));
        System.out.println(index.indexOfFirstOccurrenceMyIterative(new int[]{0, 0, 1, 2}, 0));
        System.out.println(index.indexOfFirstOccurrenceMyIterative(new int[]{1, 1, 2, 3}, 2));
        System.out.println(index.indexOfFirstOccurrenceMyIterative(new int[]{5, 10, 10,15, 15}, 15));

        System.out.println("----------------------");
        System.out.println(index.indexOfFirstOccurrenceSirIterative(new int[]{1, 1, 1, 2}, 1));
        System.out.println(index.indexOfFirstOccurrenceSirIterative(new int[]{1, 1, 1, 2}, 2));
        System.out.println(index.indexOfFirstOccurrenceSirIterative(new int[]{0, 1, 1, 2}, 1));
        System.out.println(index.indexOfFirstOccurrenceSirIterative(new int[]{0, 0, 1, 2}, 0));
        System.out.println(index.indexOfFirstOccurrenceSirIterative(new int[]{1, 1, 2, 3}, 2));
        System.out.println(index.indexOfFirstOccurrenceSirIterative(new int[]{5, 10, 10,15, 15}, 15));

        System.out.println("----------------------");
        System.out.println(index.indexOfFirstOccurrenceMyRecursive(new int[]{1, 1, 1, 2}, 0, 3, 1));
        System.out.println(index.indexOfFirstOccurrenceMyRecursive(new int[]{1, 1, 1, 2}, 0, 3,2));
        System.out.println(index.indexOfFirstOccurrenceMyRecursive(new int[]{0, 1, 1, 2}, 0, 3,1));
        System.out.println(index.indexOfFirstOccurrenceMyRecursive(new int[]{0, 0, 1, 2}, 0, 3,0));
        System.out.println(index.indexOfFirstOccurrenceMyRecursive(new int[]{1, 1, 2, 3}, 0, 3,2));
        System.out.println(index.indexOfFirstOccurrenceMyRecursive(new int[]{5, 10, 10,15, 15}, 0, 4, 15));

        System.out.println("----------------------");
        System.out.println(index.indexOfFirstOccurrenceSirRecursive(new int[]{1, 1, 1, 2}, 0, 3, 1));
        System.out.println(index.indexOfFirstOccurrenceSirRecursive(new int[]{1, 1, 1, 2}, 0, 3,2));
        System.out.println(index.indexOfFirstOccurrenceSirRecursive(new int[]{0, 1, 1, 2}, 0, 3,1));
        System.out.println(index.indexOfFirstOccurrenceSirRecursive(new int[]{0, 0, 1, 2}, 0, 3,0));
        System.out.println(index.indexOfFirstOccurrenceSirRecursive(new int[]{1, 1, 2, 3}, 0, 3,2));
        System.out.println(index.indexOfFirstOccurrenceSirRecursive(new int[]{5, 10, 10,15, 15}, 0, 4, 15));

    }
}
