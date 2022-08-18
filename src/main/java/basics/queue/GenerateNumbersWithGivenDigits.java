package basics.queue;

import java.util.LinkedList;
import java.util.Queue;

//Given a no. `n`,
//print first `n` nos. in increasing order
//which have digits 5 and 6 only.
public class GenerateNumbersWithGivenDigits {

    //T:Theta(N), S:Theta(N)
    void printFirstN(int n) {
        Queue<String> queue =
                new LinkedList<>();
        queue.add("5");
        queue.add("6");
        for (int count = 0; count < n; count++) {
            String curr = queue.poll();
            System.out.println(curr + " ");
            queue.add(curr + "5");
            queue.add(curr + "6");
        }
    }

    public static void main(String[] args) {
        GenerateNumbersWithGivenDigits digits =
                new GenerateNumbersWithGivenDigits();
        digits.printFirstN(4);
    }
}
