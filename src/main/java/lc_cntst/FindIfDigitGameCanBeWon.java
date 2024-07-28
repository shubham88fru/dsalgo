package lc_cntst;

//@link - https://leetcode.com/problems/find-if-digit-game-can-be-won/description/
public class FindIfDigitGameCanBeWon {
    public boolean canAliceWin(int[] nums) {
        int sum1 = 0;
        int sum2 = 0;
        for (int num: nums) {
            if (num >= 10) sum1 += num;
            else sum2 += num;
        }


        return sum1 != sum2;
    }
}
