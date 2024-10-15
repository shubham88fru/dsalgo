package lc_potd;

//@link - https://leetcode.com/problems/separate-black-and-white-balls/
//@check - https://www.youtube.com/watch?v=E6AKLOdt9jc&t=600s&ab_channel=codestorywithMIK
public class SeperateBlackAndWhite {
    //my soln.
    public long minimumSteps(String s) {
        long swaps = 0;
        long oneCnt = 0;
        for (int i=0; i<s.length(); i++) {
            /*
                Since we can only swap adjacents,
                at any index if we encounter a 0, then
                we'll have to make those many swaps as many
                ones that we have seen before the zero.

                However, what threw me off was that how
                does this guarantee that this will be
                the min num of swaps? I guess its because
                with the constraint that only adjacent
                nums can be swapped, the only way to
                collect all zeros to left and ones to
                right is keep swapping as you see 0
                on the go.
            */
            if (s.charAt(i) == '0') {
                swaps += oneCnt;
            } else oneCnt += 1;
        }

        return swaps;
    }
}
