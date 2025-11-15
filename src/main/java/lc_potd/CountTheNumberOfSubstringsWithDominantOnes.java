package lc_potd;

//@link - https://leetcode.com/problems/count-the-number-of-substrings-with-dominant-ones/description/?
//@check - https://www.youtube.com/watch?v=gWRnztyET8s
public class CountTheNumberOfSubstringsWithDominantOnes {
    public int numberOfSubstrings(String s) {
        return mikssol(s);
    }

    //0) Probably the optimal
    // Sliding window.

    //1) Enumeration.
    //Still O(nˆ2) soln but
    //make smart jumps.
    //Coded by me completely based on mik's
    //explanation.
    private int mikssol(String s) {
        int n = s.length();
        int[] p = new int[n]; //count of ones.
        for (int i=0; i<n; i++) {
            char ch = s.charAt(i);
            if (ch == '0') {
                p[i] = (i==0?0: p[i-1]);
            } else {
                p[i] = (i==0?1:p[i-1]+1);
            }
        }

        int res = 0;
        for (int i=0; i<n; i++) {
            int j=i;
            while (j < n) {
                int o = p[j] - ((i == 0) ? 0: p[i-1]); //ones in i..j
                int z = j-i+1 - o; //remaining in i..j is zero.
                int zSq = z * z;
                if (zSq > o) {
                    int neededOnes = zSq - o;
                    j += neededOnes; //no matter what atleast these many moves will be needed to collect enough 1s.
                } else if (zSq == o) {
                    res += 1;
                    j += 1;
                } else {
                    int k = (int) Math.sqrt(o) - z;
                    int next = j + k;

                    if (next >= n) {
                        res += n-j;
                        break;
                    } else {
                        res += (k+1);

                    }
                    j = next+1;
                }
            }
        }

        return res;
    }

    //2) Slightly better
    //Brute force but instead of
    //couting 1/0 in each sub, maintain
    //a prefix array and count in O(1)
    //TC - O(nˆ2)

    //3) Brute force.
    //Generate all subs and count 1/0
    //TC - O(nˆ3)
}
