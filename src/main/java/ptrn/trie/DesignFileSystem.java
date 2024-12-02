package ptrn.trie;

import java.util.HashMap;
import java.util.Map;

//@link - https://www.lintcode.com/problem/3677/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6225786731495424
public class DesignFileSystem {
    TrieNodeFileSystem root;
    public DesignFileSystem() {
        this.root = new TrieNodeFileSystem( "/", -1);
    }
    /**
     * @param path: the path to be created
     * @param val: path associated value
     * @return: the result of create
     */
    public boolean createPath(String path, int val) {
        if(insertToTrie(path, val)) {
            return true;
        }

        return false;
    }

    /**
     * @param path: the path of retrieve
     * @return: path associated value
     */
    public int get(String path) {
        return searchInTrie(path);
    }

    private boolean insertToTrie(String path, int val) {
        String[] folders = path.split("/");
        TrieNodeFileSystem curr = root;
        for (int i=1; i<folders.length; i++) {
            Map<String, TrieNodeFileSystem> children = curr.children;
            if (i==folders.length-1) {
                if (children.containsKey(folders[i])) return false;
                TrieNodeFileSystem fld = new TrieNodeFileSystem(folders[i], val);
                children.put(folders[i], fld);
                return true;
            } else {
                if (!children.containsKey(folders[i])) return false;
                curr = children.get(folders[i]);
            }
        }

        return true;
    }

    private int searchInTrie(String path) {
        String[] folders = path.split("/");
        TrieNodeFileSystem curr = root;
        for (int i=1; i<folders.length; i++) {
            Map<String, TrieNodeFileSystem> children = curr.children;
            if (!children.containsKey(folders[i])) return -1;
            curr = children.get(folders[i]);
        }

        return curr.val;
    }
}

class TrieNodeFileSystem {
    Map<String, TrieNodeFileSystem> children = new HashMap<>();
    String folderName;
    int val;

    public TrieNodeFileSystem(String folderName, int val) {
        this.folderName = folderName;
        this.val = val;
    }
}
