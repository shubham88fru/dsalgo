package swd.graphs;

//@link - https://leetcode.com/problems/find-the-town-judge/description/
public class FindJudgeInTown {
    public int findJudge(int n, int[][] trust) {
        //indegree represents edges coming to the vertex.
        int[] inDegree = new int[n+1];

        //outdegree represents edges going out of the vertex.
        int[] outDegree = new int[n+1];

        //for each vertex (represented by index), find
        //no. of incoming and outgoing edges
        for (int[] itrust: trust) {
            int truster = itrust[0];
            int trustee = itrust[1];

            inDegree[trustee] += 1;
            outDegree[truster] += 1;
        }

        //For the judge is one who shall be trusted by all
        //but himself trusts no one.
        for (int currPerson = 1; currPerson <= n; currPerson += 1) {
            if (inDegree[currPerson] == n-1 && outDegree[currPerson] == 0) return currPerson;
        }

        return -1;
    }
}
