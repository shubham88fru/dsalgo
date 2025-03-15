package ptrn.topkelements;

import java.util.*;

//@link - https://leetcode.com/problems/design-twitter/description/
//@check - https://www.youtube.com/watch?v=pNichitDD2E&ab_channel=NeetCode
public class DesignTwitter {

    /*
    * I was on the right path, but there were some
    * quirks that I couldn't think of.
    *
    * Following is a soln coded by me but completely
    * based on nc's explanation.
    * */
    int time;
    private Map<Integer, Set<Integer>> followersMap;
    private Map<Integer, List<int[]>> tweetsMap;

    public DesignTwitter() {
        time = 0;
        followersMap = new HashMap<>();
        tweetsMap = new HashMap<>();
    }

    public void postTweet(int userId, int tweetId) {
        if (!tweetsMap.containsKey(userId)) {
            tweetsMap.put(userId, new ArrayList<>());
        }
        tweetsMap.get(userId).add( new int[]{time, tweetId});

        time += 1;
    }

    public List<Integer> getNewsFeed(int userId) {
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a1, a2) -> a2[0] - a1[0]);

        follow(userId, userId);
        for (int followeeId: followersMap.get(userId)) {
            if (tweetsMap.containsKey(followeeId)) { //has made a tweet yet.
                int idx = tweetsMap.get(followeeId).size()-1;
                int[] lastTweet = tweetsMap.get(followeeId).get(idx);
                maxHeap.add(new int[] {lastTweet[0], lastTweet[1], followeeId, idx-1});
            }
        }

        List<Integer> latestTweets = new ArrayList<>();

        //This code is basically doing merge k sorted list.
        while (!maxHeap.isEmpty() && latestTweets.size() < 10) {
            int[] data = maxHeap.remove();
            latestTweets.add(data[1]);

            if (data[3] >= 0) {
                int[] tweet = tweetsMap.get(data[2]).get(data[3]);
                maxHeap.add(new int[] {tweet[0], tweet[1], data[2], data[3]-1});
            }

        }

        return latestTweets;
    }

    public void follow(int followerId, int followeeId) {
        if (!followersMap.containsKey(followerId)) {
            followersMap.put(followerId, new HashSet<>());
        }
        followersMap.get(followerId).add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        if (followersMap.containsKey(followerId)) {
            followersMap.get(followerId).remove(followeeId);
        }
    }
}
