package swd.recursionbacktracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//@link - https://practice.geeksforgeeks.org/problems/rat-in-a-maze-problem/1
public class RatInAMaze {
    public ArrayList<String> findPath(int[][] m, int n) {
        Map<String, Integer> map = new HashMap<String, Integer>();

        //since we're already on first position, mark it visited.
        map.put("0-0", 1);
        return pathString(m, n, "", map, 0, 0);
    }

    private ArrayList<String> pathString(int[][] m, int n, String path, Map<String, Integer> visited, int i, int j) {

        //If index out of bounds or if 0 - it's an invalid position. Can't move.
        if (i < 0 || i >= n || j < 0 || j >= n || m[i][j] == 0) return new ArrayList<>();

        //if on last position, we've found an answer.
        if (i == n-1 && j == n-1) {
            ArrayList<String> ans = new ArrayList<>();
            ans.add(path);
            return ans;
        }

        //visit up, down, left, right if not visited.
        //once done, backtrack.
        String location;
        int nextRow, nextCol;

        nextRow = i+1;
        nextCol = j;
        location = nextRow + "-" + nextCol;
        ArrayList<String> down = new ArrayList<>();
        if (!visited.containsKey(location)) {
            visited.put(location, 1);
            down = pathString(m, n, path+"D", visited, nextRow, nextCol);
            visited.remove(location);
        }

        nextRow = i;
        nextCol = j-1;
        location = nextRow + "-" + nextCol;
        ArrayList<String> left = new ArrayList<>();
        if (!visited.containsKey(location)) {
            visited.put(location, 1);
            left = pathString(m, n, path+"L", visited, nextRow, nextCol);
            visited.remove(location);
        }

        nextRow = i;
        nextCol = j+1;
        location = nextRow + "-" + nextCol;
        ArrayList<String> right = new ArrayList<>();
        if (!visited.containsKey(location)) {
            visited.put(location, 1);
            right = pathString(m, n, path+"R", visited, nextRow, nextCol);
            visited.remove(location);
        }


        nextRow = i-1;
        nextCol = j;
        location = nextRow + "-" + nextCol;
        ArrayList<String> up = new ArrayList<>();
        if (!visited.containsKey(location)) {
            visited.put(location, 1);
            up = pathString(m, n, path+"U", visited, nextRow, nextCol);
            visited.remove(location);
        }

        //Merge all answers and return.
        ArrayList<String> finalAns = new ArrayList<>();
        if (up.size() != 0) finalAns.addAll(up);
        if (down.size() != 0) finalAns.addAll(down);
        if (left.size() != 0) finalAns.addAll(left);
        if (right.size() != 0) finalAns.addAll(right);

        return finalAns;

    }
}
