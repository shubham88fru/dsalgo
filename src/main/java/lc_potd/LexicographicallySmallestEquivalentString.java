package lc_potd;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/lexicographically-smallest-equivalent-string/
public class LexicographicallySmallestEquivalentString {
    public String smallestEquivalentString(String s1, String s2, String baseStr) {
        // return pass1(s1, s2, baseStr); //- incorrect
        return dsu(s1, s2, baseStr);
    }

    //1. Optimal soln - using DSU.
    //I wrote it - Yay!!
    //Mik showed DFS approach but said DSU is optimal.
    private String dsu(String s1, String s2, String baseStr) {
        UF uf = new UF();
        for (int i=0; i<s1.length(); i++) {
            uf.union(s1.charAt(i)-'a', s2.charAt(i)-'a');
        }

        StringBuffer sb = new StringBuffer();
        for (int i=0; i<baseStr.length(); i++) {
            sb.append((char)(uf.find(baseStr.charAt(i)-'a')+97));
        }

        return sb.toString();
    }

    //2. Brute force - form a graph such that there's
    //an edge between each corresponding pairs in s1 and s2.
    //Once we have the graph, run a dfs on the graph
    //for each char of s3, and in process, note down the
    //min char that can be visited. This min char will be
    //the smallest equivalent char for the current char.


    /*
        First intuition, but doesn't work for all cases.
        This approach basically, doesn't take care of merging.
        And therefore, after failing with this approach, I got
        a strong hint that this problem is based on DSU.

        Failing case -
        s1 = "accbdafgeab"
        s2 = "cigeciahdaf"
        s3 = "fbzzwblekir"
        expected = "aazzwalakar"
        got = "bbzzwblbkar"
    */
    private String pass1(String s1, String s2, String baseStr) {
        int n = s1.length();
        int group = 1;
        int[] groups = new int[26];
        Map<Integer, Integer> smallestForGroup = new HashMap<>();

        for (int i=0; i<n; i++) {
            int ch1 = s1.charAt(i)-'a';
            int ch2 = s2.charAt(i)-'a';

            if (groups[ch1] == 0 && groups[ch2] == 0) {
                groups[ch1] = group;
                groups[ch2] = group;
                group += 1;
            } else if (groups[ch1] != 0 && groups[ch2] != 0) {
                int grp1 = smallestForGroup.get(groups[ch1]);
                int grp2 = smallestForGroup.get(groups[ch2]);
                smallestForGroup.put(grp1, Math.min(grp1, grp2));
                smallestForGroup.put(grp2,  Math.min(grp1, grp2));

            } else if (groups[ch1] != 0) {
                smallestForGroup.put(groups[ch1], Math.min(
                        smallestForGroup.get(groups[ch1]),
                        ch2
                ));
                groups[ch2] = groups[ch1];
            } else {
                smallestForGroup.put(groups[ch2], Math.min(
                        smallestForGroup.get(groups[ch2]),
                        ch1
                ));
                groups[ch1] = groups[ch2];
            }
        }

        StringBuffer sb = new StringBuffer();
        for (int i=0; i<baseStr.length(); i++) {
            int ch = baseStr.charAt(i) - 'a';
            if (groups[ch] != 0) {
                char c = (char)(smallestForGroup.get(groups[ch]) + 97);
                sb.append(c);
            } else sb.append(baseStr.charAt(i));
        }


        return sb.toString();
    }
}

class UF {
    public int[] parent;

    // Constructor
    public UF() {
        parent = new int[26];
        for (int i = 0; i <= 25; i++) {
            parent[i] = i;
        }
    }

    // Function to find which subset a particular element belongs to.
    public int find(int v) {
        if (parent[v] != v) {
            parent[v] = find(parent[v]); //had to use path compression, wasn't working without it.
        }
        return parent[v];
    }

    // Function to join two subsets into a single subset
    public boolean union(int v1, int v2) {

        // Find the root parents of v1 and v2
        int p1 = find(v1);
        int p2 = find(v2);

        if (p1 == p2) {
            return false;
        }

        if (p1 < p2) {
            parent[p2] = p1;
        } else {
            parent[p1] = p2;
        }

        return true;
    }
}
