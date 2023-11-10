package strvr.trie;

import java.util.Arrays;

//@link - https://leetcode.com/problems/maximum-xor-with-an-element-from-array/
//@strvr - https://takeuforward.org/trie/maximum-xor-queries-trie/
public class MaxXORWithAnElementOfArray {
    public int[] maximizeXor(int[] nums, int[][] queries) {
        int qlen = queries.length;
        int numlen = nums.length;

        //since we'll be sorting the queries by the limit, it will
        //distort the order in which the query came. So, just keeping
        //track of the index so that it answers can be returned in the
        //order of orginal queries.
        QueryIdx[] queriesWithIndex = new QueryIdx[qlen];
        for (int i=0; i < qlen; i++) {
            queriesWithIndex[i] = new QueryIdx(queries[i], i);
        }

        //sort queries by limit
        Arrays.sort(queriesWithIndex, (qi1, qi2) -> qi1.query[1]-qi2.query[1]);

        //sort nums array.
        Arrays.sort(nums);

        Trie2 trie = new Trie2();

        int[] ans = new int[qlen];
        int arrIdx = 0;

        for (int i=0; i<qlen; i++) {
            int x = queriesWithIndex[i].query[0];
            int m = queriesWithIndex[i].query[1];

            //insert into trie only if the current num
            //is less than limit. Since nums is sorted
            //as soon as we encounte a num which is more
            //than current querie's limit, we'll break, no
            //element ahead can thus be smaller.
            while (arrIdx < numlen && nums[arrIdx] <= m) {
                trie.insert(nums[arrIdx]);
                arrIdx += 1;
            }

            //if atleast one element was smaller. Otherwise, -1 ATQ.
            if (arrIdx == 0) ans[queriesWithIndex[i].idx] = -1;
            else ans[queriesWithIndex[i].idx] = trie.getMax(x);
        }

        return ans;
    }
}

class QueryIdx {
    int[] query;
    int idx;
    public QueryIdx(int[] query, int idx) {
        this.query = query;
        this.idx = idx;
    }
}