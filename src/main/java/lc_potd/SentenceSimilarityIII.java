package lc_potd;

//@link - https://leetcode.com/problems/sentence-similarity-iii/description/
//@check - https://www.youtube.com/watch?v=J9KwcuukMZE&t=1162s
public class SentenceSimilarityIII {
    /*
     Coded by me but approach shamelessly copied/inspired by mik.
     */
    public boolean areSentencesSimilar(String sentence1, String sentence2) {
        return miksol(sentence1, sentence2);
    }

    private boolean miksol(String sentence1, String sentence2) {
        int n1 = sentence1.length();
        int n2 = sentence2.length();

        if (
                (n1 == n2) &&
                        !sentence1.equals(sentence2)
        ) return false;

        if (n2 > n1) {
            String temp = sentence1;
            sentence1 = sentence2;
            sentence2 = temp;
        }

        String[] s1 = sentence1.split(" "); //larger string, at this point.
        String[] s2 = sentence2.split(" "); //smaller string, at this point.
        n1 = s1.length;
        n2 = s2.length;

        int i =0;
        int j = 0;
        //try matching words in s2 from beginning of s1 as long a possible.
        while (j < n2) {
            if (s2[j].equals(s1[i])) {
                i += 1;
                j += 1;
            } else break;
        }

        //when mismatch found from the start,
        //match words in s2 from end of s1.
        int jj = j;
        j = n2-1;
        i = n1-1;
        while (j >= jj) {
            if (!s2[j].equals(s1[i])) return false;
            j -= 1;
            i -= 1;
        }

        //if all words in s2 found from either beginning
        //of s1 or end of s1, return true.
        return true;

    }
}
