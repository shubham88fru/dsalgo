package misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SortOneArrayWrtOther {

    class ArrItem {
        int num;
        char ch;
        public ArrItem(int num, char ch) {
            this.num = num;
            this.ch = ch;
        }
    }

    void sortOtherArray(int[] numArr, char[] charArr) {
        int n = numArr.length;
        List<ArrItem> lst = new ArrayList<>(n);
        for (int i=0; i<n; ++i) {
            lst.add(new ArrItem(numArr[i], charArr[i]));
        }
        lst.sort(Comparator
                .comparingInt((el1 -> el1.num)));
        for (int i=0; i<n; i++) {
            numArr[i] = lst.get(i).num;
            charArr[i] = lst.get(i).ch;
        }

        System.out.println(Arrays.toString(numArr));
        System.out.println("---------------------");
        System.out.println(Arrays.toString(charArr));


    }

    public static void main(String[] args) {
        SortOneArrayWrtOther
                sortOneArrayWrtOther
                = new SortOneArrayWrtOther();
        int[] numArr = {10, 15, 5};
        char[] charArr = {'X', 'Y', 'Z'};
        sortOneArrayWrtOther
                .sortOtherArray(numArr, charArr);
    }
}
