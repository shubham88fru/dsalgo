package strvr.dp2;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/palindrome-partitioning-ii/description/
//@strvr - https://takeuforward.org/dynamic-programming/matrix-chain-multiplication-dp-48/
public class PalindromePartitioningII {
    /**MY SOL - Gives TLE (Perhaps because I'm using substring, which is a costly affair)**/
    // public int minCut(String s) {
    //     Map<String, Integer> memo = new HashMap<>();
    //     return minCutsPalindrome(s, memo);
    // }

    // private int minCutsPalindrome(String s, Map<String, Integer> memo) {
    //     //if (s.length() == 0) return 0;
    //     if (isPalindrome(s)) return 0;

    //     int ans = 10000001;
    //     String key = s;
    //     if (memo.containsKey(key)) return memo.get(key);
    //     for (int i=0; i<s.length(); i++) {
    //         String leftHalf = s.substring(0, i+1);
    //         String rest = s.substring(i+1, s.length());
    //         boolean leftIsPal = isPalindrome(leftHalf);
    //         if (leftIsPal) {
    //             int minCuts = 1 + minCutsPalindrome(rest, memo);
    //             ans = Math.min(ans, minCuts);
    //         }
    //     }

    //     memo.put(key, ans);
    //     return memo.get(key);
    // }

    /**SWD SOL (Not using substring)**/
    public int minCut(String s) {
        Map<String, Integer> memo = new HashMap<>();
        return minCutsPalindrome(s, 0, s.length()-1, memo);
    }

    private int minCutsPalindrome(String s, int start, int end, Map<String, Integer> memo) {
        if (isPalindrome(s, start, end)) return 0;

        int ans = 10000001;
        String key = start + "-" + end;
        if (memo.containsKey(key)) return memo.get(key);
        for (int i=start; i<end; i++) {
            if (isPalindrome(s, start, i)) {
                int minCuts = 1 + minCutsPalindrome(s, i+1, end, memo);
                ans = Math.min(ans, minCuts);
            }
        }

        memo.put(key, ans);
        return memo.get(key);
    }

    private boolean isPalindrome(String s) {
        StringBuilder sbuf = new StringBuilder(s);
        return s.equals(sbuf.reverse().toString());
    }

    private boolean isPalindrome(String s, int start, int end) {
        while (start<=end) {
            if (s.charAt(start)!= s.charAt(end)) return false;
            start++;
            end--;
        }
        return true;
    }

    public static void main(String[] args) {
        PalindromePartitioningII palindromePartitioningII = new PalindromePartitioningII();
        String test = "fiefhgdcdcgfeibggchibffahiededbbegegdfibdbfdadfbdbceaadeceeefiheibahgececggaehbdcgebaigfacifhdbecbebfhiefchaaheiichgdbheacfbhfiaffaecicbegdgeiaiccghggdfggbebdaefcagihbdhhigdgbghbahhhdagbdaefeccfiaifffcfehfcdiiieibadcedibbedgfegibefagfccahfcbegdfdhhdgfhgbchiaieehdgdabhidhfeecgfiibediiafacagigbhchcdhbaigdcedggehhgdhedaebchcafcdehcffdiagcafcgiidhdhedgaaegdchibhdaegdfdaiiidcihifbfidechicighbcbgibadbabieaafgeagfhebfaheaeeibagdfhadifafghbfihehgcgggffgbfccgafigieadfehieafaehaggeeaaaehggffccddchibegfhdfafhadgeieggiigacbfgcagigbhbhefcadafhafdiegahbhccidbeeagcgebehheebfaechceefdiafgeddhdfcadfdafbhiifigcbddahbabbeedidhaieagheihhgffbfbiacgdaifbedaegbhigghfeiahcdieghhdabdggfcgbafgibiifdeefcbegcfcdihaeacihgdchihdadifeifdgecbchgdgdcifedacfddhhbcagaicbebbiadgbddcbagbafeadhddaeebdgdebafabghcabdhdgieiahggddigefddccfccibifgbfcdccghgceigdfdbghdihechfabhbacifgbiiiihcgifhdbhfcaiefhccibebcahidachfabicbdabibiachahggffiibbgchbidfbbhfcicfafgcagaaadbacddfiigdiiffhbbehaaacidggfbhgeaghigihggfcdcidbfccahhgaffiibbhidhdacacdfebedbiacaidaachegffaiiegeabfdgdcgdacfcfhdcbfiaaifgfaciacfghagceaaebhhibbieehhcbiggabefbeigcbhbcidbfhfcgdddgdffghidbbbfbdhcgabaagddcebaechbbiegeiggbabdhgghciheabdibefdfghbfbfebidhicdhbeghebeddgfdfhefebiiebdchifbcbahaddhbfafbbcebiigadhgcfbebgbebhfddgdeehhgdegaeedfadegfeihcgeefbbagbbacbgggciehdhiggcgaaicceeaefgcehfhfdciaghcbbgdihbhecfbgffefhgiefgeiggcebgaacefidghdfdhiabgibchdicdehahbibeddegfciaeaffgbefbbeihbafbagagedgbdadfdggfeaebaidchgdbcifhahgfdcehbahhdggcdggceiabhhafghegfdiegbcadgaecdcdddfhicabdfhbdiiceiegiedecdifhbhhfhgdbhibbdgafhgdcheefdhifgddchadbdggiidhbhegbdfdidhhfbehibiaacdfbiagcbheabaaebfeaeafbgigiefeaeheabifgcfibiddadicheahgbfhbhddaheghddceedigddhchecaghdegigbegcbfgbggdgbbigegffhcfcbbebdchffhddbfhhfgegggibhafiebcfgeaeehgdgbccbfghagfdbdfcbcigbigaccecfehcffahiafgabfcaefbghccieehhhiighcfeabffggfchfdgcfhadgidabdceediefdccceidcfbfiiaidechhbhdccccaigeegcaicabbifigcghcefaafaefd";
        System.out.println(palindromePartitioningII.minCutsPalindrome(test, 0, test.length()-1, new HashMap<String, Integer>()));
    }
}
