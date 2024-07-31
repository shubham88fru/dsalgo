package lc_potd;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/filling-bookcase-shelves/description/
public class FillingBookcaseShelves {
    public int minHeightShelves(int[][] books, int shelfWidth) {
        return solve(books, shelfWidth, 0, 0, 0, new HashMap<>());
    }

    private int solve(int[][] books, int shelfWidth, int curr, int height, int width, Map<String, Integer> memo) {
        if (width > shelfWidth) return 1000002;
        if (curr >= books.length) return height;

        String key = curr + "_" + height + "_" + width;
        if (memo.containsKey(key)) return memo.get(key);

        int currLevel = solve(books, shelfWidth, curr + 1, Math.max(height, books[curr][1]), width + books[curr][0], memo);
        int nextLevel = height + solve(books, shelfWidth, curr + 1, books[curr][1], books[curr][0], memo);

        memo.put(key, Math.min(currLevel, nextLevel));
        return memo.get(key);
    }
}
