package swd.trees;

import java.util.*;

//@link - https://practice.geeksforgeeks.org/problems/top-view-of-binary-tree/1
public class TopViewOfBT {

    /** My and SWD's Soln - Doesn't work for all cases. **/
    static ArrayList<Integer> topViewDfs(GfgNode root) {
        ArrayList<Integer> ans1 = new ArrayList<>();
        ArrayList<Integer> ans2 = new ArrayList<>();
        Map<Integer, Integer> mp = new LinkedHashMap<Integer, Integer>();

        traverseForTopViewLeft(root, 0, mp);
        for (Map.Entry<Integer, Integer> entry : mp.entrySet()) {
            if (entry.getKey() <= 0)
                ans1.add(entry.getValue());
            else
                ans2.add(entry.getValue());
        }
        Collections.reverse(ans1);
        ans1.addAll(ans2);
        return ans1;
    }

    private static void traverseForTopViewLeft(GfgNode root, int index, Map<Integer, Integer> breadthMap) {
        if (root == null) return;

        if (!breadthMap.containsKey(index)) {
            breadthMap.put(index, root.data);
        }

        traverseForTopViewLeft(root.left, index - 1, breadthMap);
        traverseForTopViewLeft(root.right, index + 1, breadthMap);
    }


    static ArrayList<Integer> topViewBFS(GfgNode root) {
        ArrayList<Integer> ans1 = new ArrayList<>();
        ArrayList<Integer> ans2 = new ArrayList<>();
        Map<Integer, Integer> mp = new LinkedHashMap<Integer, Integer>();

        traverseForTopViewLeftBFS(root ,mp);
        for (Map.Entry<Integer, Integer> entry: mp.entrySet() ) {
            if (entry.getKey() > 0)
                ans1.add(entry.getValue());
            else
                ans2.add(entry.getValue());
        }

        //coz, during bfs, left nodes
        //will be added from top to bottom,
        //but ques requires them to be printed
        //bottom to up.
        Collections.reverse(ans2);
        ans2.addAll(ans1);
        return ans2;
    }

    private static void traverseForTopViewLeftBFS(GfgNode root, Map<Integer, Integer> breadthMap) {
        if (root == null) return;

        Deque<GfgNodeWrapper> queue = new ArrayDeque<>();
        ArrayList<Integer> ans = new ArrayList<>();

        queue.addLast(new GfgNodeWrapper(root, 0));

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size > 0) {
                GfgNodeWrapper currPair = queue.removeFirst();
                GfgNode currNode = currPair._node;
                int dist = currPair._dist;

                if (!breadthMap.containsKey(dist)) {
                    breadthMap.put(dist, currNode.data);
                }

                if (currNode.left != null)
                    queue.addLast(new GfgNodeWrapper(currNode.left, dist-1));

                if (currNode.right != null)
                    queue.addLast(new GfgNodeWrapper(currNode.right, dist+1));

                size -= 1;
            }
        }
    }
}

class GfgNodeWrapper {
    GfgNode _node;
    int _dist;

    public GfgNodeWrapper(GfgNode _node, int _dist) {
        this._node = _node;
        this._dist = _dist;
    }
}
