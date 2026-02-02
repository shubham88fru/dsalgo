package lc_potd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@link - https://leetcode.com/problems/time-based-key-value-store/?

/**
 * Not sure why in the V1 of this problem's soln,
 * I decided to use two maps.
 * @see ptrn.customds.TimeBasedKeyValueStore
 */
public class TimeBasedKeyValueStoreV2 {
    final Map<String, List<Version>> mp = new HashMap<>();

    public TimeBasedKeyValueStoreV2() {}

    public void set(String key, String value, int timestamp) {
        if (!mp.containsKey(key)) {
            mp.put(key, new ArrayList<>());
        }
        mp.get(key).add(new Version(timestamp, value));
    }

    public String get(String key, int timestamp) {
        if (!mp.containsKey(key)) return "";

        return binarySearch(key, timestamp);
    }

    private String binarySearch(String key, int ts) {
        List<Version> versions = mp.get(key);
        int l=0, r=versions.size()-1;
        int i = -1;
        while (l <= r) {
            int mid = l + (r-l)/2;
            if (versions.get(mid).ts <= ts) {
                i = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        if (i == -1) return "";

        return versions.get(i).value;
    }
}

class Version {
    int ts;
    String value;

    public Version(int ts, String value) {
        this.ts = ts;
        this.value = value;
    }
}