package misc;

import java.util.Iterator;
import java.util.LinkedList;

//Given no. of persons `n` and the
//`k`th person to be killed in every iteration
//we need to return the last surviving person.
public class JosephusProblem {

    int josephus(int n, int k) {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i=0; i<n; i++) {
            list.add(i);
        }
        Iterator<Integer> it = list.iterator();
        while (list.size()>1) {
            int count = 0;
            //Navigate to `k`th item.
            while (count < k) {
                while (it.hasNext() && count < k) {
                    it.next();
                    count++;
                }
                if (count < k) {
                    //Move the iterator back to first pos,
                    //this mocks a circular linked list.
                    //Otherwise, java doesn't have a circular linked
                    //list implementation out-of-the-box.
                    it = list.iterator();

                    it.next();
                    count++;
                }
            }
            //remove `k`th item.
            it.remove();
        }
        return list.getFirst();
    }

    public static void main(String[] args) {
        JosephusProblem josephusProblem = new JosephusProblem();
        System.out.println(josephusProblem.josephus(7, 3));
    }
}
