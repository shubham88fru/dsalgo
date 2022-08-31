package basics.heap;

import java.util.Arrays;

public class MinHeap implements IHeap {
    //Binary heap is nothing but an array.
    private final int[] arr;
    private int size;
    private final int capacity;

    public MinHeap(int capacity) {
        this.arr = new int[capacity];
        this.size = 0;
        this.capacity = capacity;
    }


    @Override
    public int left(int i) {
        return (2*i + 1);
    }

    @Override
    public int right(int i) {
        return (2*i + 2);
    }

    @Override
    public int parent(int i) {
        return (i-1)/2;
    }

    //T: O(log(size)) --> `H` for a min heap is log(size)
    @Override
    public void insert(int key) {
        //already full, don't insert
        if (size==capacity) return;

        //key is inserted in the array
        //at last pos, therefore, after
        //insertion, its still a BT.
        size++;
        int i = size -1;
        arr[i] = key;

        //Now, ensure that after insertion
        //it is still a min heap i.e. place
        //the item at its correct pos as per
        //rules of min heap (increasing order
        //as we go down the tree).

        //while not root and parent is smaller,
        //keep swapping.
        while (i!=0 && arr[parent(i)]>arr[i]) {
            //swap
            int temp = arr[i];
            arr[i] = arr[parent(i)];
            arr[parent(i)] = temp;

            //i to parent
            i = parent(i);
        }

    }

    //Given a binary heap with one possible
    //index that is violating the min heap rule
    //we need to fix it.
    //T: O(log(size)), S: O(log(size))
    void minHeapify(int i) {
        //first, find the smallest among
        //the i, its left and right.
        int left = left(i), right = right(i);
        int smallest = i;
        if (left<size && arr[left]<arr[i])
            smallest = left;
        if (right<size && arr[right]<arr[smallest])
            smallest = right;

        //once we have smallest among the 3, if the root
        //(i.e. i) of the subtree is not smallest,swap and recurse.
        if (smallest!=i) {
            //swap curr with smallest
            int temp = arr[i];
            arr[i] = arr[smallest];
            arr[smallest] = temp;

            minHeapify(smallest);
        }
    }

    //Removes min element from a min heap
    //and returns it.
    //T:O(log(size)
    int extractMin() {
        if (size==0) return Integer.MAX_VALUE;
        if (size==1) { size--; return arr[0]; }

        //arr[0] is always the min in
        //a min heap. But we need to take
        //care of the min heap structure
        //once we remove it.

        //First, swap arr[0] with last el of
        //the min heap, so that after the swap
        //size-- will effectively remove the min el.
        int min = arr[0];
        arr[0] = arr[size-1];
        arr[size-1] = min;
        size--;

        //once swapped, the min heap is disrupted
        //at index 0. So, we simply min heapify it.
        minHeapify(0);

        return min;
    }

    @Override
    public String toString() {
        return Arrays.toString(arr);
    }
}

class Test {
    public static void main(String[] args) {
        MinHeap minHeap = new MinHeap(9);
        minHeap.insert(10);
        minHeap.insert(20);
        minHeap.insert(15);
        minHeap.insert(40);
        minHeap.insert(50);
        minHeap.insert(100);
        minHeap.insert(25);
        minHeap.insert(45);

        minHeap.insert(12);

        System.out.println(minHeap);
    }
}
