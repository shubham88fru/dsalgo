package swd.graphs;

//@link - https://practice.geeksforgeeks.org/problems/the-celebrity-problem/1?utm_source=gfg&utm_medium=article&utm_campaign=bottom_sticky_on_article
public class TheCelebrityProblem {
    int celebrity(int[][] M, int n) {
        int[] inDegree = new int[n];
        int[] outDegree = new int[n];

        for (int knower = 0; knower < n; knower += 1) {
            int[] relation = M[knower];
            for (int knowee = 0; knowee < n; knowee += 1) {
                int status = relation[knowee];
                if (status == 1) {
                    inDegree[knowee] += 1;
                    outDegree[knower] += 1;
                }
            }
        }

        for (int guestNum = 0; guestNum < n; guestNum += 1) {
            if (inDegree[guestNum] == n-1 && outDegree[guestNum] == 0) return guestNum;
        }

        return -1;
    }
}
