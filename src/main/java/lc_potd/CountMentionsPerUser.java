package lc_potd;

import java.util.Comparator;
import java.util.List;

//@link - https://leetcode.com/problems/count-mentions-per-user/
public class CountMentionsPerUser {
    public int[] countMentions(int numberOfUsers, List<List<String>> events) {
        return pass1(numberOfUsers, events);
    }

    /*
        My sol
     */
    private int[] pass1(int nu, List<List<String>> events) {
        int[] ans = new int[nu];

        User[] active = new User[nu];
        User[] offline = new User[nu];

        for (int i=0; i<nu; i++) active[i] = new User(i, 0);

        Comparator<List<String>> cmp = (l1, l2) -> {
            int ts1 = Integer.parseInt(l1.get(1));
            int ts2 = Integer.parseInt(l2.get(1));

            if (ts1 < ts2) return -1;
            else if (ts1 > ts2) return 1;

            /**
             Give priority of offline
             when timestamp is same.
             */
            String e1 = l1.get(0);
            String e2 = l2.get(0);
            if (e1.equals("OFFLINE")) return -1;
            if (e2.equals("OFFLINE")) return 1;

            return 0;
        };

        events.sort(cmp);

        for (List<String> event: events) {
            String e = event.get(0);
            int t = Integer.parseInt(event.get(1));
            String m = event.get(2);
            if (e.equals("MESSAGE")) {
                if (m.equals("ALL")) {
                    for (int i=0; i<nu; i++) ans[i] += 1;
                } else if (m.equals("HERE")) {
                    for (int i=0; i<nu; i++) {
                        if (active[i] != null) ans[i] += 1;

                            //activate the offlines.
                        else if (offline[i] != null && offline[i].activeTime <= t) {
                            offline[i] = null;
                            active[i] = new User(i, 0);
                            ans[i] += 1;
                        }
                    }
                } else {
                    String[] ids = m.split(" ");
                    for (String id: ids) {
                        int i = Integer.parseInt(id.substring(2));
                        ans[i] += 1;
                    }
                }
            } else {
                int id = Integer.parseInt(m);
                active[id] = null;
                offline[id] = new User(id, t+60);
            }
        }

        return ans;
    }
}

class User {
    int id;
    int activeTime;

    public User(int id, int activeTime) {
        this.id = id;
        this.activeTime = activeTime;
    }
}
