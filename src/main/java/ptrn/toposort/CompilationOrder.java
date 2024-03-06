package ptrn.toposort;

import java.util.*;

//@link - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6616762251739136
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6616762251739136
public class CompilationOrder {
    public static List<Character> findCompilationOrder(ArrayList<ArrayList<Character>> dependencies){
        List<Character> sortedOrder = new ArrayList<>();

        /**
         * Using a map representation of graph coz nodes
         * are alphabets.
         */
        HashMap<Character, List<Character>> graph = new HashMap<>();
        HashMap<Character, Integer> inDegree = new HashMap<>();

        for( int x = 0; x < dependencies.size(); x++){
            char parent = dependencies.get(x).get(0);
            char child = dependencies.get(x).get(1);
            graph.put(parent, new ArrayList<>());
            graph.put(child, new ArrayList<>());
            inDegree.put(parent, 0);
            inDegree.put(child, 0);
        }
        if(graph.size() <= 0){
            return sortedOrder;
        }

        //populate the graph.
        for (int dependency = 0; dependency < dependencies.size(); dependency++){
            char parent = dependencies.get(dependency).get(1);
            char child = dependencies.get(dependency).get(0);
            graph.get(parent).add(child);
            inDegree.put(child, inDegree.get(child) + 1);
        }

        //topological sort algorithm.
        Queue<Character> sources = new LinkedList<>();
        for(char key : inDegree.keySet()){
            if(inDegree.get(key) == 0){
                sources.add(key);
            }
        }

        while(!sources.isEmpty()){
            char vertex = sources.poll();

            sortedOrder.add(vertex);
            for(int child = 0; child < graph.get(vertex).size(); child++){
                inDegree.put(graph.get(vertex).get(child), inDegree.get(graph.get(vertex).get(child)) -1);
                if(inDegree.get(graph.get(vertex).get(child)) == 0){
                    sources.add(graph.get(vertex).get(child));
                }
            }
        }

        if(sortedOrder.size() != graph.size()){
            return new ArrayList<>();
        }
        return sortedOrder;
    }
}
