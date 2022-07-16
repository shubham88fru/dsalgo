package basics.hashing;

import java.util.*;

public class CountDistinctElementsInArray {

    //Naive Soln
    //T: O(N^2), S:O(1)
    int countDistinctNaive(int[] arr) {
        if (arr.length == 0) return 0;
        int distinct = 1;
        for (int i=0; i<arr.length;i++) {
            for (int j=i+1;j<arr.length;j++) {
                if (arr[i] == arr[j]) break;
                if (j==arr.length-1) distinct++;
            }
        }
        return distinct;
    }

    int countDistinctNaiveSir(int [] arr) {
        int res = 0;
        for (int i = 0; i< arr.length; i++) {
            boolean flag = false;
            for (int j = 0; j<i; j++) {
                if (arr[i]==arr[j]) {
                    flag = true;
                    break;
                }
            }
            if (!flag) res++;
        }
        return res;
    }

    //T: O(N), S:O(N)
    int countDistinct(int[] arr) {
        if (arr.length==0) return 0;
        Map<Integer, Boolean> map = new HashMap<>();
        for (int j : arr) {
            if (map.containsKey(j)) continue;
            map.put(j, true);
        }
        return map.size();
    }

    int countDistinctSir(int[] arr) {
        if (arr.length==0) return 0;
        Set<Integer> set = new HashSet<>();
        for (int j: arr) {
            set.add(j);
        }
        return set.size();
    }

    int countDistinctSir2(Integer[] arr) {
        return new HashSet<>(Arrays.asList(arr)).size();
    }

    public static void main(String[] args) {
        CountDistinctElementsInArray distinctElementsInArray = new CountDistinctElementsInArray();
        System.out.println(distinctElementsInArray.countDistinctNaive(new int[] {4, 4, 4, 4, 4}));
        System.out.println(distinctElementsInArray.countDistinctNaive(new int[] {10, 20, 10, 20, 30}));
        System.out.println(distinctElementsInArray.countDistinctNaive(new int[] {}));
        System.out.println(distinctElementsInArray.countDistinctNaive(new int[] {1}));

        System.out.println("----------------------------");

        System.out.println(distinctElementsInArray.countDistinctNaiveSir(new int[] {4, 4, 4, 4, 4}));
        System.out.println(distinctElementsInArray.countDistinctNaiveSir(new int[] {10, 20, 10, 20, 30}));
        System.out.println(distinctElementsInArray.countDistinctNaiveSir(new int[] {}));
        System.out.println(distinctElementsInArray.countDistinctNaiveSir(new int[] {1}));

        System.out.println("----------------------------");

        System.out.println(distinctElementsInArray.countDistinct(new int[] {4, 4, 4, 4, 4}));
        System.out.println(distinctElementsInArray.countDistinct(new int[] {10, 20, 10, 20, 30}));
        System.out.println(distinctElementsInArray.countDistinct(new int[] {}));
        System.out.println(distinctElementsInArray.countDistinct(new int[] {1}));

        System.out.println("----------------------------");

        System.out.println(distinctElementsInArray.countDistinctSir(new int[] {4, 4, 4, 4, 4}));
        System.out.println(distinctElementsInArray.countDistinctSir(new int[] {10, 20, 10, 20, 30}));
        System.out.println(distinctElementsInArray.countDistinctSir(new int[] {}));
        System.out.println(distinctElementsInArray.countDistinctSir(new int[] {1}));

        System.out.println("----------------------------");

        System.out.println(distinctElementsInArray.countDistinctSir2(new Integer[] {4, 4, 4, 4, 4}));
        System.out.println(distinctElementsInArray.countDistinctSir2(new Integer[] {10, 20, 10, 20, 30}));
        System.out.println(distinctElementsInArray.countDistinctSir2(new Integer[] {}));
        System.out.println(distinctElementsInArray.countDistinctSir2(new Integer[] {1}));

    }
}
