package basics.sorting;

public class BuildMaxHeap {

    static void maxHeapify(int[] arr, int n, int i) {
        int largest = i, left = 2*i+1, right = 2*i+2;
        if (left < n && arr[left] > arr[largest])
            largest = left;
        if (right < n && arr[right] > arr[largest])
            largest = right;
        if(largest!=i) {
            //swap(arr[largest], arr[i])
            int temp = arr[largest];
            arr[largest] = arr[i];
            arr[i] = temp;
            maxHeapify(arr, n, largest);
        }
    }

    //Step 1
    static void buildHeap(int[] arr) {
        int n = arr.length;
        for (int i=(n-2)/2; i>=0; i--) {
            maxHeapify(arr, n, i);
        }
    }

    public static void main(String[] args) {

    }
}
