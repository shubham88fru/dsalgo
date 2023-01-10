package swd.hashmap;

//@link - https://www.lintcode.com/problem/1375/
public class CountOfSubstringWithAtleastKUniqueChars {
    public long atleastkDistinctCharacters(String str, int k) {
        int n = str.length();
        int totalSubstrings = n*(n+1)/2; //total no. of substrings.

        //total substrings minus substrings with atmost `k-1` distinct chars.
        return totalSubstrings - CountOfSubstringWithAtmostKUniqueChars.solution(str, k-1);
    }
}
