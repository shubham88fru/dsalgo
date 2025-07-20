package lc_potd;

import java.util.*;

//@link - https://leetcode.com/problems/delete-duplicate-folders-in-system/
//@check - https://www.youtube.com/watch?v=Dw10MLHBkuE&ab_channel=codestorywithMIK
public class DeleteDuplicateFoldersInSystem {
    Map<String, Integer> subfolders = new HashMap<>();

    public List<List<String>> deleteDuplicateFolder(List<List<String>> paths) {
        return mikssol(paths);
    }

    private List<List<String>> mikssol(List<List<String>> paths) {
        TrieNode123 root = constructTrie(paths);
        mark(root);
        sweep(root);

        List<List<String>> ans = new ArrayList<>();
        resultant(root, new ArrayList<>() ,ans);

        return ans;
    }

    private TrieNode123 constructTrie(List<List<String>> paths) {
        return construct(paths);
    }

    private void resultant(TrieNode123 root, List<String> curr, List<List<String>> ans) {

        for (Map.Entry<String, TrieNode123> entry: root.children.entrySet()) {
            curr.add(entry.getKey());
            ans.add(new ArrayList<>(curr));
            resultant(entry.getValue(), curr, ans);
            curr.remove(curr.size()-1);
        }
    }

    private void sweep(TrieNode123 root) {
        Iterator<Map.Entry<String, TrieNode123>> iterator = root.children.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, TrieNode123> entry = iterator.next();
            if (entry.getValue().subfolder != null && subfolders.get(entry.getValue().subfolder) > 1) {
                iterator.remove();
            } else sweep(entry.getValue());
        }
    }

    private String mark(TrieNode123 root) {
        if (root.children.size() == 0) return "";
        List<String> subs = new ArrayList<>();
        for (Map.Entry<String, TrieNode123> entry: root.children.entrySet()) {
            subs.add("(" + entry.getKey() + mark(entry.getValue()) + ")");
        }
        Collections.sort(subs);
        root.subfolder = String.join("", subs);
        subfolders.put(root.subfolder, subfolders.getOrDefault(root.subfolder, 0)+1);
        return root.subfolder;
    }

    private TrieNode123 construct(List<List<String>> paths) {
        TrieNode123 root = new TrieNode123("/");
        for (List<String> pathz: paths) {
            TrieNode123 curr = root;
            for (String path: pathz) {
                if (!curr.children.containsKey(path)) {
                    TrieNode123 folder = new TrieNode123(path);
                    curr.children.put(path, folder);
                }
                curr = curr.children.get(path);
            }
        }

        return root;
    }
}

class TrieNode123 {
    String folder;
    String subfolder;
    Map<String, TrieNode123> children = new HashMap<>();
    public TrieNode123(String folder) {
        this.folder = folder;
    }
}