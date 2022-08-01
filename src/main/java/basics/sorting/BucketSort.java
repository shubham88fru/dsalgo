package basics.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BucketSort {

    void bucketSortMy(int[] arr, int k) {
        int max = arr[0];
        for (int el: arr) {
            if (el>max) max = el;
        }
        List<List<Integer>> buckets = new ArrayList<>();
        for (int i=0; i<=k;i++) {
            buckets.add(i, new ArrayList<>());
        }
        int range = max/k;
        for (int j : arr) {
            int bucketNum = j / range;
            buckets.get(bucketNum).add(j);
        }
        System.out.println(buckets);
    }

    void bucketSortSir(int[] arr, int k) {
        int n = arr.length;
        int max_val = arr[0];

        //1: Find max
        for (int i=1;i<n;i++) {
            max_val = Math.max(max_val, arr[i]);
        }

        //increment max so that we get correct bucket nums
        //bucket indexes start from zero.
        max_val++;
        ArrayList<ArrayList<Integer>> bkt
                = new ArrayList<>();

        //2: Fill buckets
        for (int i=0; i<k; i++) {
            bkt.add(new ArrayList<>());
        }
        for (int value : arr) {
            int bi = (k * value) / max_val;
            bkt.get(bi).add(value);
        }

        //3: sort
        for (int i=0; i<k; i++) {
            Collections.sort(bkt.get(i));
        }
        int index=0;

        //4: join buckets
        for (int i=0; i<k; i++) {
            for (int j=0; j<bkt.get(i).size(); j++) {
                arr[index++] = bkt.get(i).get(j);
            }
        }
        System.out.println(bkt);
        System.out.println(Arrays.toString(arr));
    }

    public static void main(String[] args) {
        BucketSort bucketSort = new BucketSort();
        int[] arr = new int[] {30, 40, 10, 80, 5, 12, 70};
        bucketSort.bucketSortMy(arr, 4);


        System.out.println("-----------------------");
        int[] arr2 = new int[] {30, 40, 10, 80, 5, 12, 70};
        bucketSort.bucketSortSir(arr2, 4);
    }
}
