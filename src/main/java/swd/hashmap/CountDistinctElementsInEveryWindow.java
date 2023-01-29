package swd.hashmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

//@link - https://practice.geeksforgeeks.org/problems/count-distinct-elements-in-every-window/1?utm_source=gfg&utm_medium=article&utm_campaign=bottom_sticky_on_article
public class CountDistinctElementsInEveryWindow {
    /* My Soln (Brute force) - O(N^2) */
    /****************
    ArrayList<Integer> countDistinct(int A[], int n, int k) {
        ArrayList<Integer> res = new ArrayList<>();
        for (int i=0; k+i<=n; i++) {
            Map<Integer, Integer> mp = new HashMap<>();
            int ans=0;
            for (int j=i; j<k+i; j++) {
                if (!mp.containsKey(A[j])) {
                    mp.put(A[j], 1);
                    ans++;
                }
            }
            res.add(ans);
        }
        return res;
    }
    ******************/

    /** My Soln - O(N) **/
    ArrayList<Integer> countDistinct(int A[], int n, int k) {
        ArrayList<Integer> res = new ArrayList<>();
        Map<Integer, Integer> mp = new HashMap<>();
        int ans = 0;
        int head = 0;
        for (int j = 0; j < n; j++) {
            if (j <= k - 1) {
                if (!mp.containsKey(A[j])) {
                    mp.put(A[j], mp.getOrDefault(A[j], 0) + 1);
                    ans++;
                } else mp.put(A[j], mp.getOrDefault(A[j], 0) + 1);
            } else {
                res.add(ans);
                mp.put(A[head], mp.get(A[head]) - 1);
                if (Objects.equals(mp.get(A[head]), 0)) {
                    ans--;
                }
                if (!mp.containsKey(A[j]) || Objects.equals(mp.get(A[j]), 0)) {
                    mp.put(A[j], mp.getOrDefault(A[j], 0) + 1);
                    ans++;
                } else mp.put(A[j], mp.getOrDefault(A[j], 0) + 1);
                head++;
            }
        }
        res.add(ans);
        return res;
    }

    /** SWD Soln **/
    ArrayList<Integer> countDistinctSWD(int A[], int n, int k) {
        ArrayList<Integer> answer = new ArrayList<>();
        Map<Integer, Integer> memo = new HashMap<>();
        int distinct = 0;

        //acquire first k elements;
        for (int i=0; i<k; i++) {
            int currentValue = A[i];
            if (memo.containsKey(currentValue)) {
                memo.put(currentValue, memo.get(currentValue)+1);
            } else {
                memo.put(currentValue, 1);
                distinct++;
            }
        }

        //first k elements (first window) are acquired. add answer for 1st window.
        answer.add(distinct);
        int release = 0;

        //now with every acquire, we have to start releasing as well, so as to
        //maintain the size of window.
        for (int acquire = k; acquire < n; acquire++) {
            int discardElement = A[release]; //release the element
            //decrease the frequency of the discarded element.
            memo.put(discardElement, memo.get(discardElement)-1);

            //if zero frequency after discarding, delete from memo altogether.
            if (Objects.equals(memo.get(discardElement), 0)) {
                memo.remove(discardElement);
                distinct -= 1;
            }

            //move release
            release++;

            //Finally, acquire curr element;
            int currentElement = A[acquire];

            if (memo.containsKey(currentElement)) {
                memo.put(currentElement, memo.get(currentElement)+1);
            } else {
                memo.put(currentElement, 1);
                distinct++;
            }

            answer.add(distinct);
        }

        return answer;
    }

    public static void main(String[] args) {
        CountDistinctElementsInEveryWindow countDistinctElementsInEveryWindow
                = new CountDistinctElementsInEveryWindow();
        countDistinctElementsInEveryWindow.countDistinct(new int[]{66, 60, 37, 33, 52, 38, 29, 76, 8, 75, 22, 59, 96, 30, 38, 36, 94, 19, 29, 44, 12, 29}, 22, 14);
    }
}
