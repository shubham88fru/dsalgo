package msic;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//Given an array of integers and a no. `k`.
//need to return a list of nos. in the array
//that are smaller than `k`.
public class ListOfSmallerElements {

    List<Integer> filterLessThan(List<Integer> lst, int k) {
        return lst.stream()
                .filter(el -> el < k)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        ListOfSmallerElements listOfSmallerElements = new ListOfSmallerElements();
        System.out.println(listOfSmallerElements.filterLessThan(Arrays.asList(8, 100, 20, 40, 3, 7), 10));
        System.out.println(listOfSmallerElements.filterLessThan(Arrays.asList(100, 20, 40, 60, 80, 200), 50));
    }
}
