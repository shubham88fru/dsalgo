package misc;

import java.util.Arrays;

//Given an odd sized matrix with all distinct
//elements and sorted row-wise. Need to find
//the median.
public class MedianOfARowWiseSortedMatrix {

    int findMedianNaive(int[][] mat) {
        //flatten the matrix in a 1D array
        //sort this new array
        //return mid.
        return 0;
    }

    int findMedian(int[][] mat) {
        int r = mat.length;
        int c = mat[0].length;

        int min = mat[0][0], max = mat[0][c-1];

        //find min and max in entire mat.
        for (int i=1; i<r; i++) {
            if (mat[i][0]<min) min = mat[i][0];
            if (mat[i][c-1]>max) max = mat[i][c-1];
        }
        int medPos = (r*c+1)/2;
        while (min<max) {
            int mid = (min+max)/2, midPos=0;
            for (int i=0; i<r; i++) {
                int pos = Arrays.
                        binarySearch(mat[i], mid)+1;
                midPos += Math.abs(pos);
            }
            if (midPos<medPos)
                min = mid+1;
            else
                max=mid;
        }
        return min;
    }

    public static void main(String[] args) {
        MedianOfARowWiseSortedMatrix
                medianOfARowWiseSortedMatrix =
                new MedianOfARowWiseSortedMatrix();
        int[][] mat = { {5,10,20,30,40},
                        {1,2,3,4,6},
                        {11,13,15,17,19}
                      };
        System.out.println(medianOfARowWiseSortedMatrix
                .findMedian(mat));
    }
}
