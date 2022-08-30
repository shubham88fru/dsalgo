package basics.heap;

public interface IHeap {
    int left(int i);
    int right(int i);
    int parent(int i);
    void insert(int key);
}
