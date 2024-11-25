package lc_potd;

import java.util.*;

//@link - https://leetcode.com/problems/sliding-puzzle/description/
//@check - https://www.youtube.com/watch?v=d_DIBUophu8
public class SlidingPuzzle {

    /*
        Coded by me, but approach by Mik.
     */
    public int slidingPuzzle(int[][] board) {
        /*
            Mik's intuition leading to BFS : -
            1. State of the matrix has to be changed.
            2. We have a starting state and have to reach a target state of the matrix.
            3. 1 state change per level, i.e. as we move down, state changes.
            4. We have the find the nearest level (from root), i.e. shortest distance,
            in which we can reach the final state. --> Screams BFS (or a similar shortest path algo.)
        */

        /*
            Also, in the BFS queue, instead of storing each state as a matrix,
            we'll convert the matrix to a string representation that makes
            running the BFS a bit easier.

            |1|2|3|
            -------  ===> "123405"
            |4|0|5|
        */
        return bfs(board);
    }

    private int bfs(int[][] board) {
        /*
            Its easy to find the 4 directionally adjancent nodes from a matrix,
            but here, since we are storing the state as String, we need a way
            to get adjacent indexes of current position of zero. Since the matrix
            is a fixed sized matrix and there can be max six position where zero
            can reside, we'll just precompute and store the ajacent index.
        */
        Map<Integer, List<Integer>> adjacentIndexes = new HashMap<>();
        adjacentIndexes.put(0, Arrays.asList(1, 3));
        adjacentIndexes.put(1, Arrays.asList(0, 2, 4));
        adjacentIndexes.put(2, Arrays.asList(1, 5));
        adjacentIndexes.put(3, Arrays.asList(0, 4));
        adjacentIndexes.put(4, Arrays.asList(1, 3, 5));
        adjacentIndexes.put(5, Arrays.asList(4, 2));

        StringBuffer initialState = new StringBuffer();
        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board[0].length; j++) {
                initialState.append(board[i][j]);
            }
        }

        String targetState = "123450";

        int moves = 0;
        Deque<StringBuffer> q = new ArrayDeque<>();
        q.addLast(initialState);
        Set<String> visited = new HashSet<>();

        while (!q.isEmpty()) {
            int sz = q.size();

            while (sz > 0) {
                String curr = q.removeFirst().toString();
                if (curr.equals(targetState)) return moves;
                visited.add(curr);
                int zeroIndex = curr.indexOf('0');
                List<Integer> neighbours = adjacentIndexes.get(zeroIndex);
                for (int neighbour: neighbours) {
                    StringBuffer swapped = new StringBuffer(curr);
                    swapped.setCharAt(zeroIndex, swapped.charAt(neighbour));
                    swapped.setCharAt(neighbour, '0');
                    if (!visited.contains(swapped.toString())) q.addLast(swapped);
                }
                sz -= 1;
            }
            moves += 1;
        }

        return -1;

    }
}
