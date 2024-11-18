package lc_potd;

//@link - https://leetcode.com/problems/defuse-the-bomb/
public class DefuseTheBomb {
    public int[] decrypt(int[] code, int k) {
        int[] ans = new int[code.length];
        if (k > 0) {
            int sum = 0;
            int sz = k;
            int i = 1;
            while (sz > 0) {
                sum += code[i];
                i = (i+1)%code.length;
                sz -= 1;
            }

            ans[0] = sum;
            for (int j=1; j<code.length; j++) {

                ans[j] = sum-code[j]+code[i];
                sum = ans[j];
                i = (i+1)%code.length;
            }


        } else if (k < 0) {
            int sum = 0;
            int sz = -k;
            int i = (((code.length-2)%code.length)+code.length)%code.length;
            while (sz > 0) {
                sum += code[i];
                i = (((i-1)%code.length)+code.length)%code.length;
                sz -= 1;
            }

            ans[code.length-1] = sum;
            for (int j=code.length-2; j >= 0; j--) {

                ans[j] = sum-code[j]+code[i];
                sum = ans[j];
                i = (((i-1)%code.length)+code.length)%code.length;
            }
        }

        return ans;
    }
}
