package misc;

import java.util.HashSet;
import java.util.Set;

//Given 2 unsorted arrays with distinct els each,
// print intersection
//of the two arrays.
public class IntersectionOfArrays {

    //We want to print the intersection
    //in the order in which the els appear
    //in the first array.
    //T: O(N+M), S: O(N)
    void printIntersection(int[] arr1,
                           int[] arr2) {
        Set<Integer> set = new HashSet<>();
        for (int el: arr2)
            set.add(el);

        for (int el: arr1)
            if (set.contains(el))
                System.out.print(el+" ");
    }

    public static void main(String[] args) {
        IntersectionOfArrays intersectionOfArrays
                = new IntersectionOfArrays();

        int[] arr1 = {10, 15, 20, 25, 30, 50};
        int[] arr2 = {30, 5, 15, 80};

        intersectionOfArrays
                .printIntersection(arr1, arr2);

    }
}
