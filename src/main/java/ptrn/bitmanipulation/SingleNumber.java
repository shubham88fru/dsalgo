package ptrn.bitmanipulation;

//@link - https://leetcode.com/problems/single-number/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6096547407462400
public class SingleNumber {
    public int singleNumber(int[] nums) {
        int xor = 0;
        for (int num: nums) xor ^= num;
        return xor;
    }
}
