package misc;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PrintDistinctElements {

    //Not caring about order
    //since using hashset.
    void printDistinct(Integer[] arr) {
        List<Integer> lst = Arrays.asList(arr);
        Set<Integer> set = new HashSet<>(lst);
        set.forEach(System.out::println);
    }

    public static void main(String[] args) {
        PrintDistinctElements printDistinctElements
                = new PrintDistinctElements();
        printDistinctElements
                .printDistinct(new Integer[]{10, 8, 10, 10, 7});
    }
}
