package ptrn.customds;

import java.util.*;

//@link - https://leetcode.com/problems/time-based-key-value-store/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/6646301929504768
public class TimeBasedKeyValueStore {
    //My Soln - using treemap.
    static class TimeMap {

        private TreeMap<Integer, Map<String, String>> tmmap;
        public TimeMap() {
            tmmap = new TreeMap<>();
        }

        public void set(String key, String value, int timestamp) {
            if (!tmmap.containsKey(timestamp)) {
                tmmap.put(timestamp, new HashMap<>());
            }

            tmmap.get(timestamp).put(key, value);
        }

        public String get(String k, int timestamp) {
            for (int key: tmmap.descendingKeySet()) {
                if (key <= timestamp && tmmap.get(key).containsKey(k)) return tmmap.get(key).get(k);
            }

            return "";
        }
    }

    //Edctv soln. Using binary tree.
    /**
     * Set Value(): This first checks if the key already exists in the dictionary.
     * If the key exists and the provided value is different from the last stored value for that key,
     * it appends the new value and timestamp to respective lists. If the key is not present,
     * it creates a new entry in both the value and timestamp dictionaries.
     *
     * Get Value(): This checks if the key exists in the dictionary.
     * If it does, it uses binary search to find the index of the rightmost occurrence
     * of the given timestamp in the timestamps list. If an index is found, it returns the corresponding value.
     * Otherwise, it returns an empty string.
     */
    static class TimeMap2 {
        HashMap<String, List<String>> valuesDict;
        HashMap<String, List<Integer>> timestampDict;
        public TimeMap2() {
            valuesDict = new HashMap<String, List<String>> ();
            timestampDict = new HashMap<String, List<Integer>> ();
        }

        // Set TimeStamp data variables
        public void setValue(String key, String value, int timestamp) {
            // Check if the given key already exists in the values dictionary.
            if (valuesDict.containsKey(key)) {
                // Check if the given value of the key already exists in the values dictionary.
                if (value != valuesDict.get(key).get(valuesDict.get(key).size() - 1)) {
                    // Store values for the given key in the values dictionary.
                    valuesDict.get(key).add(value);
                    // Store timestamp for the given key in the timestamp dictionary.
                    timestampDict.get(key).add(timestamp);
                }
            } else {
                // Store value and key for the given key in the values dictionary.
                valuesDict.put(key, new ArrayList<String>());
                valuesDict.get(key).add(value);
                timestampDict.put(key, new ArrayList<Integer> ());
                // Store timestamp for the given key in the timestamp dictionary.
                timestampDict.get(key).add(timestamp);
            }
        }

        // Find the index of right most occurrence of the given timestamp using binary search
        public int searchIndex(int n, String key, int timeStamp) {

            int left = 0;
            int right = n;
            int mid = 0;
            while (left<right) {
                // It will return the mid of the current dictionary.
                mid = left + (right - left) / 2;
                // Increase index value if required index is less than the
                // current index otherwise decrease it.
                if (!(timestampDict.get(key).get(mid) > timeStamp)) left = mid + 1;
                else right = mid;
            }
            return left - 1;
        }

        // Get the value for the given key and timestamp
        public String getValue(String key, int timeStamp) {
            int index = 0;
            // Check if the given key is present in the values dictionary.
            if (!valuesDict.containsKey(key)) {
                // Return empty string if item does not exist.
                return "";
            } else {
                // Find the right most occurrence of the given timestamp.
                index = searchIndex(timestampDict.get(key).size(), key, timeStamp) ;
            }

            // If the timestamp exist in the timestamp dictionary, return the
            // value with that timestamp.
            if (index > -1) {
                return valuesDict.get(key).get(index);
            }
            // Return empty string if the timestamp does not exist.
            return "";
        }
    }
}
