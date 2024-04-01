package ptrn.graph;

import java.util.*;

//@link - https://leetcode.com/problems/clone-graph/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6612701683646464
public class CloneGraph {
    public Node cloneGraph(Node node) {
        return bfs(node);
        // return dfs(node, new HashMap<>());
    }

    //1) my bfs solution. Idea is to create two queues,
    //one for og graph and another for cloned graph.
    //Run bfs on og graph and keep updating the cloned graph in process.
    private Node bfs(Node node) {
        if (node == null) return null;
        Set<Integer> visited = new HashSet<>();
        Deque<Node> q1 = new ArrayDeque<>(); //for bfs of og graph.

        Deque<Node> q2 = new ArrayDeque<>(); //for bfs of cloned graph.
        Map<Integer, Node> newNode = new HashMap<>(); //keep track of cloned nodes.

        q1.addLast(node);
        Node cpy = new Node(node.val); //clone node.
        q2.addLast(cpy);
        newNode.put(node.val, cpy);

        while (!q1.isEmpty()) {
            Node curr = q1.removeFirst();
            Node curr_cpy = q2.removeFirst();
            if (visited.contains(curr.val)) continue;
            visited.add(curr.val);

            List<Node> neighbors = curr.neighbors;
            for (Node neighbor: neighbors) {
                Node neighbor_cpy = null;
                //if the neighbor node is already cloned, don't create a new copy.
                if (newNode.containsKey(neighbor.val)) neighbor_cpy = newNode.get(neighbor.val);
                else  {
                    //otherwise, clone it and record it.
                    neighbor_cpy = new Node(neighbor.val);
                    newNode.put(neighbor.val, neighbor_cpy);
                }
                //add the cloned neigbor to current cloned node's neighbor list
                curr_cpy.neighbors.add(neighbor_cpy);
                q1.addLast(neighbor);
                q2.addLast(neighbor_cpy);
            }
        }

        return cpy;
    }

    //2) DFS solution. edtcv.
    private Node dfs(Node root, Map<Node, Node> visitedNodes) {
        if (root == null) return null;
        Node clonedNode = new Node(root.val);
        visitedNodes.put(root, clonedNode); //og node against its clone.
        for (Node neighbor: root.neighbors) {
            Node x = visitedNodes.get(neighbor);
            if (x == null) { //if not already cloned..
                //clone it.
                clonedNode.neighbors.add(dfs(neighbor, visitedNodes));
            } else {
                clonedNode.neighbors.add(x);
            }
        }

        return clonedNode;
    }
}

class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
