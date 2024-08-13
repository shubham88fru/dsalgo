package ptrn.unionfind;

import java.util.*;

//@link - https://leetcode.com/problems/accounts-merge/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6081899459772416
//@copypasta
public class AccountsMerge {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        UnionFind222 uf = new UnionFind222(accounts.size());

        // Create a map for mapping emails to their parent IDs
        Map<String, Integer> emailMapping = new HashMap<>();
        for (int i = 0; i < accounts.size(); ++i) {
            List<String> emails = accounts.get(i);

            // If the email already exists in the map, take union
            for (int j = 1; j < emails.size(); ++j) {
                if (emailMapping.containsKey(emails.get(j))) {

                    // Before we take the union, make sure both the accounts have the same name
                    if (!accounts.get(i).get(0).equals(accounts.get(emailMapping.get(emails.get(j))).get(0))) {
                        return new ArrayList<>(); // Return empty list to indicate an error
                    }
                    uf.union(emailMapping.get(emails.get(j)), i);
                }
                // Add email with its ID to the map
                emailMapping.put(emails.get(j), i);
            }
        }

        // Create a map to store the merged accounts
        Map<Integer, List<String>> mergedAccounts = new HashMap<>();
        for (Map.Entry<String, Integer> entry : emailMapping.entrySet()) {
            int root = uf.find(entry.getValue());
            mergedAccounts.computeIfAbsent(root, k -> new ArrayList<>()).add(entry.getKey());
        }

        // Sort the merged accounts
        List<List<String>> finalMerged = new ArrayList<>();
        for (Map.Entry<Integer, List<String>> entry : mergedAccounts.entrySet()) {
            int parent = entry.getKey();
            List<String> emails = entry.getValue();
            Collections.sort(emails);
            List<String> merged = new ArrayList<>();
            merged.add(accounts.get(parent).get(0));
            merged.addAll(emails);
            finalMerged.add(merged);
        }

        return finalMerged;
    }
}


class UnionFind222 {
    private int[] parents;

    public UnionFind222(int n) {
        parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }
    }

    public int find(int node) {
        if (parents[node] == node) {
            return node;
        }
        return find(parents[node]);
    }

    public void union(int node1, int node2) {
        int rootNode1 = find(node1);
        int rootNode2 = find(node2);
        if (rootNode1 != rootNode2) {
            parents[rootNode2] = rootNode1;
        }
    }
}