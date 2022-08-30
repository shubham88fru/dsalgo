package basics.heap;

import java.util.Arrays;

public class MinHeap implements IHeap {
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

    //T: log(size)
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
