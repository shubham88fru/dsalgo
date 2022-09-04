package misc;

import java.util.HashSet;
import java.util.Set;

public class PrintRepeatingElements {

    //T: theta(N), S: O(N)
    void printRepeating(int[] arr) {
        Set<Integer> set =
                new HashSet<>();
        for (int j : arr) {
            if (set.contains(j))
                System.out.println(j + " ");
            else set.add(j);
        }
    }

    public static void main(String[] args) {
        PrintRepeatingElements printRepeatingElements
                = new PrintRepeatingElements();
        printRepeatingElements
                .printRepeating(new int[]{10, 8, 10, 8});
    }
}
