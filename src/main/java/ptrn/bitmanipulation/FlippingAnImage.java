package ptrn.bitmanipulation;

//@link - https://leetcode.com/problems/flipping-an-image/description/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/5360581323522048
public class FlippingAnImage {
    public int[][] flipAndInvertImage(int[][] image) {
        return usingXor(image);
        // return normal(image);
    }

    //xor to flip
    private int[][] usingXor(int[][] image) {
        for (int[] row: image) {
            flipAndInvert(row);
        }

        return image;
    }

    private void flipAndInvert(int[] row) {
        int l = 0;
        int r = row.length-1;
        while (l < r) {
            int temp = row[l];
            row[l] = row[r];
            row[r] = temp;
            row[l] ^= 1;
            row[r] ^= 1;
            l += 1;
            r -= 1;
        }

        //handle the middle element
        //seperately or else it will
        //get flipped twice in the loop.
        if (l==r) {
            row[l] ^= 1;
        }
    }

    //if-else to flip.
    private int[][] normal(int[][] image) {
        int m = image.length;
        int n = image[0].length;

        for (int i=0; i<m; i++) {
            //reverse each row.
            flip(image[i]);
        }

        return image;
    }

    private void flip(int[] row) {
        int l = 0;
        int r = row.length-1;

        while (l <= r) {
            int temp = row[r];
            //while flipping, invert each bit.
            row[r] = invert(row[l]);
            row[l] = invert(temp);
            l += 1;
            r -= 1;
        }
    }

    private int invert(int bit) {
        return bit == 0 ? 1 : 0;
    }
}
