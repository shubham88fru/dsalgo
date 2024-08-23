package ptrn.customds;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class TimeBasedKeyValueStore {
    //My Soln.
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
}
