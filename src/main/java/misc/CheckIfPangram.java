package misc;

//string is pangram if it contains all
//chars from a to z (case doesn't matter)
public class CheckIfPangram {

    //T: O(N), S:O(1)
    boolean checkIfPangram(String s) {
        if (s==null || s.length()<26)
            return Boolean.FALSE;

        boolean[] visited = new boolean[26];

        for (int i=0; i<s.length(); i++) {
            char x = s.charAt(i);
            if (x>='a' && x<='z')
                visited[x-'a'] = true;
            if (x>='X' && x<='Z')
                visited[x-'A'] = true;
        }

        for (int i=0; i<26; i++)
            if (!visited[i])
                return false;
        return true;
    }

    public static void main(String[] args) {
        CheckIfPangram checkIfPangram
                = new CheckIfPangram();
        String st = "The quick brown fox jumps over the lazy dog";
        System.out.println(
                checkIfPangram.checkIfPangram(st));
    }
}
