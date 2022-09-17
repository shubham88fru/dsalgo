package misc;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

//Given an array, need to sort it but also
//need to print the element's original index
//in the unsorted array (raw array).
public class RetainIndexesAfterSorting {

    class ArrItem {
        int item;
        int index;
        ArrItem(int im, int ix) {
            item = im;
            index = ix;
        }
    }

    void printIndexSorted(int[] arr) {
        int n = arr.length;
        List<ArrItem> al = new ArrayList<>(n);
        for (int i=0; i<n; i++)
            al.add(new ArrItem(arr[i], i));
        al.sort(Comparator.comparingInt(ai -> ai.item));

        for (ArrItem x: al)
            System.out.println(x.item+" "+x.index);
    }

    public static void main(String[] args) {
        RetainIndexesAfterSorting
                retainIndexesAfterSorting
                = new RetainIndexesAfterSorting();
        int[] arr = {20, 10, 5, 5};
        retainIndexesAfterSorting
                .printIndexSorted(arr);
    }
}
