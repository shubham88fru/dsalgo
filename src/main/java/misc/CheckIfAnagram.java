package misc;

import java.util.Arrays;

//Given 2 string, check if they
//are permutation of each other.
public class CheckIfAnagram {

    private static final int MAX_CHARS = 256;
    //T: O(nlogn)
    boolean checkIfAnagram1(String s1, String s2) {
        if (s1.length() != s2.length())
            return false;
        char[] a1 = s1.toCharArray();
        Arrays.sort(a1);
        s1 = new String(a1);

        char[] a2 = s2.toCharArray();
        Arrays.sort(a2);
        s2 = new String(a2);

        return s1.equals(s2);
    }

    //T: O(N)
    boolean checkIfAnagram2(String s1, String s2) {
        if (s1.length()!=s2.length())
            return false;
        int[] count = new int[MAX_CHARS];
        for (int i=0; i<s1.length(); i++) {
            count[s1.charAt(i)]++;
            count[s2.charAt(i)]--;
        }
        for (int i=0; i<MAX_CHARS; i++) {
            if (count[i]!=0)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        CheckIfAnagram checkIfAnagram
                = new CheckIfAnagram();
        String s1 = "abaaca";
        String s2 = "aacbaa";
        System.out.println(checkIfAnagram
                .checkIfAnagram1(s1, s2));
        System.out.println("-------------");
        System.out.println(checkIfAnagram
                .checkIfAnagram2(s1, s2));
    }
}
