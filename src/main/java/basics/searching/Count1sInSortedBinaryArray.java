package basics.searching;

public class Count1sInSortedBinaryArray {

    int countOnes(int[] arr) {
        IndexOfFirstOccurrence firstOccurrence = new IndexOfFirstOccurrence();
        int firstIdx = firstOccurrence.indexOfFirstOccurrenceSirIterative(arr, 1);
        return firstIdx == -1 ? 0: arr.length - firstIdx;
    }


    public static void main(String[] args) {
        Count1sInSortedBinaryArray count = new Count1sInSortedBinaryArray();
        System.out.println(count.countOnes(new int[]{0, 0, 0, 1,1,1,1}));
        System.out.println(count.countOnes(new int[]{0, 0, 0,1}));
        System.out.println(count.countOnes(new int[]{0, 0, 0}));
        System.out.println(count.countOnes(new int[]{1,1,1, 1,1}));
        System.out.println(count.countOnes(new int[]{0, 0, 1,1,1,1}));
    }
}
