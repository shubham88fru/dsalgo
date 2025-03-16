package lc_potd;

//@link - https://leetcode.com/problems/minimum-time-to-repair-cars/description/
public class MinimumTimeToRepairCars {
    public long repairCars(int[] ranks, int cars) {
        return pass1(ranks, cars);
    }

    /*
        First intuition after reading the question
        was dp. Like each mechanic has a choice of
        how many cars they'll repair. But that seemed
        a very bad approach coz num of cars was very large.

        Then with the example given by LC, realized that
        for each distribution, the minimum time will basically
        be the time take taken by the slowest mechanic, i.e.
        the maximum time.

        T: O(n) + O(log(maxRank*cars*cars)*n)

        Mik also had the same intuition and approach.
    */
    private long pass1(int[] ranks, int cars) {
        int maxRank = Integer.MIN_VALUE;
        for (int rank: ranks) {
            maxRank = Math.max(maxRank, rank);
        }

        long l = 1l;
        long r = (1l*maxRank*cars*cars); //multiply 1l to convert to long.

        long minTime = 0l;
        while (l <= r) {
            long mid = l + (r-l)/2;
            if (isPossible(ranks, cars, mid)) {
                minTime = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return minTime;
    }

    private boolean isPossible(int[] ranks, int cars, long mid) {
        int carsRepaired = 0;
        for (int rank: ranks) {
            carsRepaired += (Math.sqrt(mid/rank)); //num of cars curr mechanic can fix in mid time.

            if (cars <= carsRepaired) return true;
        }

        return (carsRepaired) >= cars;
    }
}
