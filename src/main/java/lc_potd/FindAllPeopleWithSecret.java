package lc_potd;

import java.util.*;

//@link - https://leetcode.com/problems/find-all-people-with-secret/description/?
//@check - https://www.youtube.com/watch?v=hxEWwnJJKcY
public class FindAllPeopleWithSecret {
    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        // return approach1(n, meetings, firstPerson);
        // return approach2(n, meetings, firstPerson);
        return approach3(n, meetings, firstPerson);
    }

    //@copypasta
    private List<Integer> approach3(int n, int[][] meetings, int firstPerson) {
        // For every person, we store the meeting time and label of the person met.
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] meeting : meetings) {
            int person1 = meeting[0];
            int person2 = meeting[1];
            int time    = meeting[2];
            graph.computeIfAbsent(person2, k -> new ArrayList<>()).add(new int[]{time, person1});
            graph.computeIfAbsent(person1, k -> new ArrayList<>()).add(new int[]{time, person2});
        }

        // Priority Queue for BFS. It will store (time of knowing the secret, person)
        // We will pop the person with the minimum time of knowing the secret.
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.offer(new int[]{0, 0});
        pq.offer(new int[]{0, firstPerson});

        // Visited array to mark if a person is visited or not.
        // We will mark a person as visited after it is dequeued
        // from the queue.
        boolean[] visited = new boolean[n];

        // Do BFS, but pop minimum.
        while (!pq.isEmpty()) {
            int[] timePerson = pq.poll();
            int time = timePerson[0], person = timePerson[1];
            if (visited[person]) {
                continue;
            }
            visited[person] = true;
            for (int[] nextPersonTime : graph.getOrDefault(person, new ArrayList<>())) {
                int t = nextPersonTime[0], nextPerson = nextPersonTime[1];
                if (!visited[nextPerson] && t >= time) {
                    pq.offer(new int[]{t, nextPerson});
                }
            }
        }

        // Since we visited only those people who know the secret
        // we need to return indices of all visited people.
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            if (visited[i]) {
                ans.add(i);
            }
        }
        return ans;
    }

    /**
     This one is straightforward but I couldn't come
     up with this myself.
     Coded by me completely based on mik's explaination.

     Gives TLE again.
     */
    private List<Integer> approach2(int n, int[][] meetings, int firstPerson) {
        int[][] knowSecret = new int[n][2];
        for (int i=0; i<n; i++) {
            knowSecret[i][0] = 0;
            knowSecret[i][1] = Integer.MAX_VALUE;
        }
        knowSecret[0][0] = 1;
        knowSecret[0][1] = 0;
        knowSecret[firstPerson][0] = 1;
        knowSecret[firstPerson][1] = 0;

        //graph
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] meeting: meetings) {
            if (!graph.containsKey(meeting[0])) graph.put(meeting[0], new ArrayList<>());
            if (!graph.containsKey(meeting[1])) graph.put(meeting[1], new ArrayList<>());
            graph.get(meeting[0]).add(new int[]{meeting[1], meeting[2]});
            graph.get(meeting[1]).add(new int[]{meeting[0], meeting[2]});
        }
        if (!graph.containsKey(0)) graph.put(0, new ArrayList<>());
        if (!graph.containsKey(firstPerson)) graph.put(firstPerson, new ArrayList<>());
        graph.get(0).add(new int[]{firstPerson, 0});
        graph.get(firstPerson).add(new int[]{0, 0});

        Deque<int[]> q = new ArrayDeque<>();
        q.addLast(new int[]{0, 0});
        q.addLast(new int[]{firstPerson, 0});
        while (!q.isEmpty()) {
            int[] person = q.removeFirst();
            List<int[]> ngs = graph.get(person[0]);
            for (int[] ng: ngs) {
                if (knowSecret[ng[0]][1] > ng[1] && ng[1] >= person[1]) {
                    knowSecret[ng[0]][0] = 1;
                    knowSecret[ng[0]][1] = Math.min(knowSecret[ng[0]][1], ng[1]);
                    q.addLast(new int[] {ng[0], ng[1]});
                }
            }
        }

        List<Integer> ans = new ArrayList<>();
        for (int i=0; i<n; i++) {
            if (knowSecret[i][0] != 0) ans.add(i);
        }

        return ans;
    }

    /**
     I was on the right track for this approach,
     but could figure out some details, so had
     to take hint from mik.

     Gives TLE though.
     */
    private List<Integer> approach1(int n, int[][] meetings, int firstPerson) {
        int[] knowSecret = new int[n];
        knowSecret[0] = 1;
        knowSecret[firstPerson] = 1;

        Map<Integer, List<int[]>> time = new TreeMap<>();
        for (int[] meeting: meetings) {
            if (!time.containsKey(meeting[2])) time.put(meeting[2], new ArrayList<>());

            time.get(meeting[2]).add(new int[]{meeting[0], meeting[1]});
        }

        for (Map.Entry<Integer, List<int[]>> entry: time.entrySet()) {
            List<int[]> pairs = entry.getValue();
            Deque<Integer> q = new ArrayDeque<>();
            List<List<Integer>> graph = getGraph1(n, pairs, q, knowSecret);

            //bfs
            while (!q.isEmpty()) {
                int person = q.removeFirst();
                List<Integer> ngs = graph.get(person);
                for (int ng: ngs) {
                    if (knowSecret[ng] == 0) {
                        knowSecret[ng] = 1;
                        q.addLast(ng);
                    }
                }
            }
        }

        List<Integer> ans = new ArrayList<>();
        for (int i=0; i<n; i++) {
            if (knowSecret[i] != 0) ans.add(i);
        }

        return ans;
    }

    private List<List<Integer>> getGraph1(int n, List<int[]> pairs, Deque<Integer> q, int[] knowSecret) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i=0; i<n; i++) graph.add(new ArrayList<>());
        Set<Integer> added = new HashSet<>();

        for (int[] pair: pairs) {
            graph.get(pair[0]).add(pair[1]);
            graph.get(pair[1]).add(pair[0]);

            if (knowSecret[pair[0]]==1 && !added.contains(pair[0])) q.addLast(pair[0]);
            if (knowSecret[pair[1]]==1 && !added.contains(pair[1])) q.addLast(pair[1]);
        }

        return graph;
    }
}
