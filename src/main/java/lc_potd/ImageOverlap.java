package lc_potd;

//@link - https://leetcode.com/problems/image-overlap/?
//@check - https://www.youtube.com/watch?v=3--u8aPXzWs
public class ImageOverlap {
    public int largestOverlap(int[][] img1, int[][] img2) {
        return mikssol(img1, img2);
    }

    /**
     Coded by me completely based on mik's
     explanation.
     The trick to this problem is realising that
     we just need to slide matrix a over matrix b
     and not necessarily move individual cells
     in matrix a.
     */
    private int mikssol(int[][] img1, int[][] img2) {
        int n = img1.length, maxCount = 0, offsetLowerBound = -n+1, offsetUpperBound = n-1;
        for (int ro=offsetLowerBound; ro<=offsetUpperBound; ro++) {
            for (int co=offsetLowerBound; co<=offsetUpperBound; co++) {
                //check each cell of a against
                //the current offsetted b.
                int count = 0;
                for (int i=0; i<n; i++) {
                    for (int j=0; j<n; j++) {
                        int bRow = i + ro, bCol = j + co;
                        if (bRow < 0 || bRow >= n || bCol < 0 || bCol >= n) continue;
                        if (img2[bRow][bCol] == 1 && img2[bRow][bCol] == img1[i][j]) count += 1;
                    }
                }
                maxCount = Math.max(count, maxCount);
            }
        }
        return maxCount;
    }
}
