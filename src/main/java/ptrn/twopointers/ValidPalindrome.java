package ptrn.twopointers;

//@link - https://leetcode.com/problems/valid-palindrome/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6319371854282752
public class ValidPalindrome {
    public boolean isPalindrome(String str) {
        //convert all to lowercase,
        //makes it easier to compare the two pointers
        String s = str.toLowerCase();

        //two pointers.
        int p1 = 0; //start
        int p2 = str.length()-1; //end

        while (p1<=p2) {
            int p1int = (int) s.charAt(p1);
            int p2int = (int) s.charAt(p2);

            //ignore if not alphanumeric.
            if (!isAlphaNumeric(p1int)) {
                p1 += 1;
                continue;
            }

            if (!isAlphaNumeric(p2int)) {
                p2 -= 1;
                continue;
            }

            //at any point if chars
            //at the two pointers not same,
            //then string is not palindrome.
            if (p1int != p2int) return false;

            //move the pointers.
            p1 += 1;
            p2 -= 1;
        }

        return true;
    }

    private boolean isAlphaNumeric(int num) {
        //[0-9]                      //[a-z]
        return ((num >= 48 && num <= 57) || (num >= 97 && num <= 122));
    }
}
