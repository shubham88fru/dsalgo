package basics.sorting;

import java.util.Arrays;

//Given an array and 3 indexes (low, mid, and high)
//and that elements from low to mid are sorted, while
//elements from mid+1 to high are also sorted. Need
//to sort the elements from low to high.
//low need not be first index of array,
//high need not be last index of array.
public class MergeFunctionOfMergeSort {

    //T: Theta(N), N -> no. of els from low to high;
    static void  sortFromLowToHighMy(int[] arr, int low, int mid, int high) {
        int[] left = Arrays.copyOfRange(arr, low, mid+1);
        int[] right = Arrays.copyOfRange(arr, mid+1, high+1);
        int[] temp = new int[high-low+1];
        int idx=0;
        int i = 0;
        int j = 0;

        while (i<left.length && j<right.length) {
            if (left[i]<=right[j]) {
                temp[idx] = left[i];
                i++;
            } else {
                temp[idx]=right[j];
                j++;
            }
            idx++;
        }

        while (i<left.length) {
            temp[idx] = left[i];
            i++;idx++;
        }
        while (j<right.length) {
            temp[idx] = right[j];
            j++;idx++;
        }

        System.out.println(Arrays.toString(temp));
    }

    static void mergeFunctionSir(int[] arr, int low, int mid, int high) {
        int n1=mid-low+1, n2=high-mid;
        int[] left = new int[n1];
        int[] right = new int[n2];
        System.arraycopy(arr, low, left, 0, n1);
        System.arraycopy(arr, n1, right, 0, n2);

        int i=0,j=0,k=0;
        while(i<n1 && j<n2) {
            if (left[i] <= right[j]) {
                arr[k] = left[i];
                i++;
                k++;
            } else {
                arr[k] = right[j];
                k++;
                j++;
            }
        }
        while (i<n1) {
            arr[k] = left[i];
            i++;
            k++;
        }
        while (j<n2) {
            arr[k] = right[j];
            j++;
            k++;
        }
        //System.out.println(Arrays.toString(arr));
    }


    //This method works with mergeSort algo.
    //TODO: Need to check what is wrong with above two methods.
    //since they aren't working with merge sort algo.
    static void mergeFunctionSir2(int[] arr, int l, int m, int h) {
        int n1=m-l+1, n2=h-m;
        int[] left=new int[n1];int[]right=new int[n2];
        System.arraycopy(arr, l, left, 0, n1);
        for(int j=0;j<n2;j++)
            right[j]=arr[m+1+j];
        int i=0,j=0,k=l;
        while(i<n1 && j<n2) {
            if(left[i]<=right[j])
                arr[k++]=left[i++];
            else
                arr[k++]=right[j++];
        }
        while(i<n1)
            arr[k++]=left[i++];
        while(j<n2)
            arr[k++]=right[j++];
    }
    public static void main(String[] args) {
        sortFromLowToHighMy(new int[]{10, 15, 20, 40, 8, 11, 55}, 0, 3, 6);
        System.out.println("------------------------------");
        sortFromLowToHighMy(new int[]{10, 20, 40, 20, 30}, 0, 2, 4);

        mergeFunctionSir(new int[]{10, 15, 20, 40, 8, 11, 55}, 0, 3, 6);
        System.out.println("------------------------------");
        mergeFunctionSir(new int[]{10, 20, 40, 20, 30}, 0, 2, 4);


    }
}
