package lc_potd;

//@link - https://leetcode.com/problems/number-of-laser-beams-in-a-bank/?
public class NumberOfLaserBeamsInABank {
    public int numberOfBeams(String[] bank) {
        return pass1(bank);
    }

    /**
     * Probably not the cleanest.
     *
     * @param bank
     * @return
     */
    private int pass1(String[] bank) {
        int n = bank.length;

        int i = 0;
        int j = 1;

        int sum = 0;
        while (j < n) {

            int counti = count(bank[i]);
            int countj = count(bank[j]);

            if (counti != 0 && countj != 0) {
                sum += (counti*countj);
                i += 1;
                j = i + 1;
            } else if (counti == 0) {
                i += 1;
                j = i + 1;
            } else j += 1;

        }

        return sum;
    }

    private int count(String num) {
        int count = 0;
        for (int i=0; i<num.length(); i++) {
            char ch = num.charAt(i);
            if (ch == '1') count += 1;
        }

        return count;
    }
}
