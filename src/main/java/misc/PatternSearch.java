package misc;

//Given a string and a pattern,
//need to print all indexes where the
//pattern matches.
public class PatternSearch {

    void findPatternMatch(String s, String pat) {
        int pos = s.indexOf(pat); //returns first occ.
        while (pos>=0) {
            System.out.print(pos+" ");
            pos = s.indexOf(pat, pos+1); //search in pending.
        }
    }

    public static void main(String[] args) {
        PatternSearch patternSearch
                = new PatternSearch();
        String s = "geeks for geeks";
        String s2 = "aaaaa";
        patternSearch.findPatternMatch(s, "geeks");
        System.out.println();
        patternSearch.findPatternMatch(s2, "aaa");
    }
}
