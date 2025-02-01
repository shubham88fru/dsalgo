package ptrn.trie;

import java.util.*;

//@link - https://leetcode.com/problems/design-in-memory-file-system/description/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/5066313048522752
public class DesignInMemoryFileSystem {

    /*
    * My Soln.
    * */
    TrieNodeFileSystem223 root;
    public DesignInMemoryFileSystem() {
        // Replace this placeholder return statement with your code
        this.root = new TrieNodeFileSystem223( "/", "", 0);
    }
    public List<String> ls(String path) {
        // Replace this placeholder return statement with your code
        // List < String > files = new ArrayList < > ();
        // return files;
        return searchInTrie(path);
    }

    public void mkdir(String path) {
        // Replace this placeholder return statement with your code
        createDir(path);
    }

    public void addContentToFile(String path, String content) {
        // Replace this placeholder return statement with your code
        String[] folders = path.split("/");
        TrieNodeFileSystem223 curr = root;
        for (int i=1; i<folders.length; i++) {
            TreeMap<String, TrieNodeFileSystem223> children = curr.children;
            if (i==folders.length-1) {
                if (!children.containsKey(folders[i])) {
                    TrieNodeFileSystem223 fld = new TrieNodeFileSystem223(folders[i], content, 1);
                    children.put(folders[i], fld);
                } else {
                    children.get(folders[i]).content += content;
                }
            } else if (children.containsKey(folders[i])) {
                curr = children.get(folders[i]);
            } else {
                TrieNodeFileSystem223 fld = new TrieNodeFileSystem223(folders[i], "", 0);
                children.put(folders[i], fld);
                curr = children.get(folders[i]);
            }
        }
    }

    public String readContentFromFile(String path) {
        // Replace this placeholder return statement with your code
        String[] folders = path.split("/");
        TrieNodeFileSystem223 curr = root;
        for (int i=1; i<folders.length; i++) {
            TreeMap<String, TrieNodeFileSystem223> children = curr.children;
            if (i==folders.length-1) {
                // if (children.size() == 0) return Arrays.asList(folders[i]);
                // return new ArrayList<>(children.keySet());
                return children.get(folders[i]).content;
            }

            // if (!children.containsKey(folders[i])) return Collections.emptyList();
            curr = children.get(folders[i]);
        }
        return "";
    }

    private void createDir(String path) {
        String[] folders = path.split("/");
        TrieNodeFileSystem223 curr = root;
        for (int i=1; i<folders.length; i++) {
            TreeMap<String, TrieNodeFileSystem223> children = curr.children;
            if (children.containsKey(folders[i])) {
                curr = children.get(folders[i]);
            } else {
                TrieNodeFileSystem223 fld = new TrieNodeFileSystem223(folders[i], "", 0);
                children.put(folders[i], fld);
                curr = children.get(folders[i]);
            }
            // if (i==folders.length-1) {
            //     if (children.containsKey(folders[i])) return false;
            //     TrieNodeFileSystem223 fld = new TrieNodeFileSystem223(folders[i], val);
            //     children.put(folders[i], fld);
            //     return true;
            // } else {
            //     if (!children.containsKey(folders[i])) return false;
            //     curr = children.get(folders[i]);
            // }
        }

        // return true;
    }

    private List<String> searchInTrie(String path) {
        if (path.equals("/")) {
            return new ArrayList<>(root.children.keySet());
        }
        String[] folders = path.split("/");
        TrieNodeFileSystem223 curr = root;
        for (int i=1; i<folders.length; i++) {
            TreeMap<String, TrieNodeFileSystem223> children = curr.children;
            if (!children.containsKey(folders[i])) return Collections.emptyList();
            if (i==folders.length-1) {
                if (children.get(folders[i]).type == 1) return Arrays.asList(folders[i]);
                children = children.get(folders[i]).children;
                // if (children.size() == 0) return Arrays.asList(folders[i]);
                return new ArrayList<>(children.keySet());
            }


            curr = children.get(folders[i]);
        }

        return null;
        // return curr.val;
    }
}

class TrieNodeFileSystem223 {
    TreeMap<String, TrieNodeFileSystem223> children = new TreeMap<>();
    String folderName;
    String content;
    int type; //0-folder, 1-file.

    public TrieNodeFileSystem223(String folderName, String content, int type) {
        this.folderName = folderName;
        this.content = content;
        this.type = type;
    }
}
