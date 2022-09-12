package misc;

import java.util.Arrays;

//given 2 strings such that one string
//has a char less than the other string.
//Need to find that one extra char.
public class FindOneExtraChar {

    //T: O(nlogn), S: O(N)
    char findExtraChar1(String s1, String s2) {
        char[] a1 = s1.toCharArray();
        Arrays.sort(a1);

        char[] a2 = s2.toCharArray();
        Arrays.sort(a2);

        int n = s1.length();
        for (int i=0; i<n; i++) {
            if (a1[i]!=a2[i])
                return a2[i];
        }

        //if not found till hear,
        //then extra char will be last
        //char.
        return a2[n];
    }

    //T:O(N), S:O(1)
    char findExtraChar2(String s1, String s2) {
        int[] count = new int[26];
        int n = s1.length();
        for (int i=0; i<n; i++) {
            count[s1.charAt(i)-'a']--;
            count[s2.charAt(i)-'a']++;
        }
        count[s2.charAt(n)-'a']++;
        for (int i=0; i<26; i++) {
            if (count[i]==1)
                return (char)(i+'a');
        }
        return 0;
    }

    char findExtraChar3(String s1, String s2) {
        int res=0;
        int n=s1.length();
        for (int i=0; i<n; i++) {
            res = res ^ s1.charAt(i) ^ s2.charAt(i);
        }
        res = res^s2.charAt(n);
        return (char) res;
    }

    public static void main(String[] args) {
        FindOneExtraChar findOneExtraChar
                = new FindOneExtraChar();
        String s1 = "abcd";
        String s2 = "dbeca";
        System.out.println(findOneExtraChar
                .findExtraChar1(s1, s2));
        System.out.println("---------------");
        System.out.println(findOneExtraChar
                .findExtraChar2(s1, s2));
        System.out.println("---------------");
        System.out.println(findOneExtraChar
                .findExtraChar3(s1, s2));
    }
}
