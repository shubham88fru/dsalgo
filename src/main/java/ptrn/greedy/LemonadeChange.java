package ptrn.greedy;

//@link - https://leetcode.com/problems/lemonade-change/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6293749757116416
public class LemonadeChange {
    public boolean lemonadeChange(int[] bills) {
        int fives = 0;
        int tens = 0;

        for (int i=0; i<bills.length; i++) {
            if (bills[i] == 5) {
                fives += 1;
            } else if (fives == 0) return false;
            else if (bills[i] == 10) {
                fives -= 1;
                tens += 1;
            } else {

                if (tens > 0) {
                    tens -= 1;
                    fives -= 1;
                } else if (fives >= 3) fives -= 3;
                else return false;
            }
        }

        return true;
    }
}
