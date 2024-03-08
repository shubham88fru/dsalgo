package ptrn.toposort;

import java.util.*;

//@link - https://www.geeksforgeeks.org/problems/alien-dictionary/1
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6539352361664512
public class AlienDictionary {
    public String findOrder(String [] dict, int N, int K) {
        Map<Character, List<Character>> graph = new HashMap<>();
        Map<Character, Integer> indegrees = new HashMap<>();

        //Initialize indegree for each character
        //in all of the words.
        for (String word: dict) {
            for (char ch: word.toCharArray()) {
                indegrees.put(ch, 0);
            }
        }

        //Then for every pair of words,
        //check the lexiography and for the graph.
        for (int i=0; i<dict.length-1; i++) {
            String word1 = dict[i]; //curr
            String word2 = dict[i+1]; //next

            int j=0;
            while (j < word1.length() && j < word2.length()) {
                char c1 = word1.charAt(j);
                char c2 = word2.charAt(j);

                //If letters at j aren't same,
                //c1[j] is a dependency for c2[j] (coz, ATQ the words
                //are lexiographically sorted.)
                if (c1 != c2) {
                    graph.putIfAbsent(c1, new ArrayList<>());

                    //Ensure that c2 is not already added as c1's
                    //neighbour.
                    boolean alreadyExists = false;
                    for (int k=0; k<graph.get(c1).size(); k++) {
                        if (graph.get(c1).get(k) == c2) {
                            alreadyExists = true;
                            break;
                        }
                    }
                    //add c2 as c1's neighbour.
                    if (!alreadyExists) {
                        graph.get(c1).add(c2);
                        indegrees.put(c2, indegrees.get(c2)+1);
                    }
                    break;
                }
                j += 1;
            }

            //if word2 is a prefix of word1, then no answer is possible.
            if (j >= word1.length() || j >= word2.length()) {
                if (word2.length() < word1.length()) return "";
            }
        }

        //Standard topo sort.
        StringBuffer ans = new StringBuffer();
        Deque<Character> q = new ArrayDeque<>();
        for (char ch: indegrees.keySet()) {
            if (indegrees.get(ch) == 0) q.addLast(ch);
        }

        int visitedCount = 0;
        while (!q.isEmpty()) {
            char ch = q.removeFirst();
            visitedCount += 1;
            ans.append(ch);

            if (!graph.containsKey(ch)) continue;

            List<Character> neighbours = graph.get(ch);
            for (char neighbour: neighbours) {
                indegrees.put(neighbour, indegrees.get(neighbour)-1);
                if (indegrees.get(neighbour) == 0) q.addLast(neighbour);
            }
        }

        //If wasn't able to visit all the nodes of the graph
        //means there's a cycle.
        if (visitedCount < indegrees.size()) return "";

        return ans.toString();

    }
}
