package recursion;

public class PalindromeCheck {

    //My soln, doesn't work.
    static boolean checkPalindrome(String str, int ptr) {
        //abccba
        int l = str.length();
        if (ptr<l) return str.charAt(ptr) == str.charAt(--l);
        ptr++;
        return checkPalindrome(str, ptr);
    }

    //T:O(N), S:O(N)
    static boolean checkPalindromeSirInput(String str) {
        int l = str.length();
        if (l==0 || l==1) return true; //palindrome for emtpy of 1 length string.
        if (str.charAt(0) != str.charAt(l-1)) return false;
        return checkPalindromeSirInput(str.substring(1, l-1));
    }

    static boolean checkPalindromeSir(String str, int start, int end) {
        if (start >= end) return true;
        return (str.charAt(start) == str.charAt(end)
                && checkPalindromeSir(str, start+1, end-1));
    }


    public static void main(String[] args) {
        System.out.println(checkPalindromeSirInput("abcba"));
        System.out.println(checkPalindromeSirInput("abcba"));
        System.out.println(checkPalindromeSirInput("acbca"));
        System.out.println(checkPalindromeSirInput("geek"));

        System.out.println("----------------------------------");

        System.out.println(checkPalindromeSir("abcba", 0, "abcba".length()-1));
        System.out.println(checkPalindromeSir("abcba", 0, "abcba".length()-1));
        System.out.println(checkPalindromeSir("acbca", 0, "acbca".length()-1));
        System.out.println(checkPalindromeSir("geek", 0, "geek".length()-1));
    }
}
