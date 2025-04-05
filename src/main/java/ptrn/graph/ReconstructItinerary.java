package ptrn.graph;

import java.util.*;

//@link - https://leetcode.com/problems/reconstruct-itinerary/description/
//@check - https://www.youtube.com/watch?v=6zq1BBMAam0&ab_channel=codestorywithMIK
//       - https://www.youtube.com/watch?v=ZyB_gQ8vqGA&t=905s&ab_channel=NeetCode
public class ReconstructItinerary {
    public List<String> findItinerary(List<List<String>> tickets) {
        return mikssol(tickets);
    }

    /*
        2. Suboptimal - Gives TLE.

        Following approach would have worked previously,
        but later LC added more test cases and this approach now
        gives TLE.

        People are posting some solution using minHeap so if this
        is a HF question for some company, check that approach as well.

        Following is based on mik and nc's explanation.
        I was on the right track but couldn't figure out few details
        and I think its still challenging for me to visualize complex
        inputs for this question.

        This problem is a good example of deleting neighbours technique
        to contain the rercursive dfs.
    */
    private List<String> mikssol(List<List<String>> tickets) {
        Map<String, List<String>> graph = new HashMap<>();
        Comparator<List<String>> cmp1 = (c1, c2) -> c1.get(0).compareTo(c2.get(0));
        Comparator<List<String>> cmp2 = (c1, c2) -> c1.get(1).compareTo(c2.get(1));
        Collections.sort(tickets,  cmp1.thenComparing(cmp2));
        for (List<String> ticket: tickets) {
            String source = ticket.get(0);
            String dest = ticket.get(1);

            graph.putIfAbsent(source, new ArrayList<>());
            graph.get(source).add(dest);
        }

        // for(Map.Entry<String,List<String>> edges:graph.entrySet()){
        //     Collections.sort(edges.getValue());
        // }

        List<String> itinerary = new ArrayList<>();
        List<String> path = new ArrayList<>();
        path.add("JFK");
        dfs("JFK", graph, tickets.size(), path, itinerary);
        return itinerary;
    }

    private boolean dfs(String from, Map<String, List<String>> graph, int n, List<String> currPath, List<String> itinerary) {

        if (currPath.size() == (n + 1)) {
            itinerary.addAll(new ArrayList(currPath));
            return true;
        }

        if (!graph.containsKey(from)) return false;
        if (from.equals("#")) return false;

        List<String> ngs = graph.get(from);
        for (int i=0; i<ngs.size(); i++) {
            //remove curr neighbour so we don't get in cycle by
            //trying to visit the same node again.
            String ng = ngs.get(i);
            // graph.get(from).remove(ng);
            graph.get(from).set(i, "#");
            currPath.add(ng);
            if (dfs(ng, graph, n, currPath, itinerary)) {
                return true;
            }
            currPath.remove(currPath.size()-1);
            // graph.get(from).add(i, ng); //needs to be added at the right location as per sort.
            graph.get(from).set(i, ng);
        }

        return false;
    }
}
