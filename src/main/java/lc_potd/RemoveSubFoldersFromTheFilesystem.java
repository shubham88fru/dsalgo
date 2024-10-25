package lc_potd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@link - https://leetcode.com/problems/remove-sub-folders-from-the-filesystem/description/
public class RemoveSubFoldersFromTheFilesystem {
    public List<String> removeSubfolders(String[] folder) {
        return mysol(folder);
    }

    private List<String> mysol(String[] folder) {
        TrieNode34 root = new TrieNode34();
        List<String> ans = new ArrayList<>();
        for (String path: folder) {
            TrieNode34 curr = root;
            int i = 0;
            while (i<path.length()) {
                int j = i+1;
                while ((j < path.length()) && (path.charAt(j) != '/')) {
                    j += 1;
                }

                String fold = path.substring(i, j);
                TrieNode34 currentFolder = curr.node.get(fold);
                if (!curr.node.containsKey(fold)) {
                    curr.node.put(fold, new TrieNode34());
                    currentFolder = curr.node.get(fold);
                } else if (currentFolder.isEnd) break;

                if (j == path.length()) {
                    currentFolder.isEnd = true;
                    currentFolder.fold = path; //store the entire path.
                }

                curr = currentFolder;
                i = j;
            }
        }

        dfs(root, ans);

        return ans;
    }

    private void dfs(TrieNode34 root, List<String> ans) {
        if (root.isEnd) {
            ans.add(root.fold);
            return;
        }

        for (Map.Entry<String, TrieNode34> entry: root.node.entrySet()) {
            dfs(entry.getValue(), ans);
        }
    }
}

class TrieNode34 {

    Map<String, TrieNode34> node = new HashMap<>();
    boolean isEnd;
    String fold;

    public TrieNode34() {}

}
