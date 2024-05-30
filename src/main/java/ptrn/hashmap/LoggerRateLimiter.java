package ptrn.hashmap;

import java.util.HashMap;
import java.util.Map;

//@link - https://leetcode.com/problems/logger-rate-limiter/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/5981434499301376
public class LoggerRateLimiter {
    private Map<String, Integer> window;
    private int timeLimit;
    public LoggerRateLimiter(int timeLimit) {
        this.window = new HashMap<>();
        this.timeLimit = timeLimit;
    }

    public boolean messageRequestDecision(int timestamp, String request) {
        if (!window.containsKey(request)) {
            window.put(request, timestamp);
            return true;
        }

        int lastTime = window.get(request);
        if (timestamp - lastTime < timeLimit) return false;
        window.put(request, timestamp);
        return true;
    }
}
