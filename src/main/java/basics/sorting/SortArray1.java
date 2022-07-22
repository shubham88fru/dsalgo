package basics.sorting;


import java.util.Arrays;
import java.util.Comparator;

//Sort an array such that all
//even nos. come first followed
//by odd nos.
public class SortArray1 {

    private static class EvenComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o1%2-o2%2;
        }
    }


    public static void main(String[] args) {
        Integer[] arr = new Integer[] {1, 2, 3, 4, 5, 6};
        Arrays.sort(arr, new EvenComparator());
        System.out.println(Arrays.toString(arr));
    }
}
