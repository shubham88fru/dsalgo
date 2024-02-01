package ptrn.greedy;

//@link - https://leetcode.com/problems/gas-station/description/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/5687429291048960
public class GasStation {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        //return canCompleteCircuitSuboptimal(gas, cost);
        return canCompleteCircuitOptimal(gas, cost);
    }

    //Edctv soln. This solution doesn't simulate the loop after choosing
    //the start index and so, I'm not 100% how it works.
    //T: O(N)
    private int canCompleteCircuitOptimal(int[] gas, int[] cost) {
        int gasSum = 0;
        int costSum = 0;

        //Find the gas station with max gas.
        for (int i = 0; i<gas.length; i++) {
            gasSum += gas[i];
            costSum += cost[i];
        }

        //optimization: if total available gas is less
        //than the total cost of gas, there's no way we
        //can ever reach to the same point again.
        if (gasSum < costSum) return -1;

        int totalGas = 0;
        int startIndex = 0;
        for (int i=0; i<gas.length; i++) {
            totalGas += (gas[i] - cost[i]);
            if (totalGas < 0) {
                totalGas = 0;
                startIndex = i+1;
            }
        }

        return startIndex;
    }

    //This solution first finds the station with max gas and starts
    //a try from that index.
    //T: O(N^2)
    private int canCompleteCircuitSuboptimal(int[] gas, int[] cost) {
        int maxGasIndex = 0;

        int gasSum = 0;
        int costSum = 0;

        //Find the gas station with max gas.
        for (int i = 0; i<gas.length; i++) {
            gasSum += gas[i];
            costSum += cost[i];
            if (gas[i]>gas[maxGasIndex]) maxGasIndex = i;
        }

        //optimization: if total available gas is less
        //than the total cost of gas, there's no way we
        //can ever reach to the same point again.
        if (gasSum < costSum) return -1;

        //Start trying from the station which has max gas,
        //since it has max chance of success.
        int tries = 0;
        for (int i=maxGasIndex; tries<gas.length; tries++) {
            if (cost[i] <= gas[i]) {
                int j = i;
                int moves=0;
                int totalGas = 0;
                //From each startpoint ensure that
                //we can travel to each other gas station and
                //come back.
                while (moves != gas.length) {
                    //if at any gas station, total gas (remaining plus refuel) is
                    //less than the cost needed to move to next - means can't  start from
                    //current start index. Hence, break and try next start point.
                    if ((totalGas+gas[j]) < cost[j]) break;
                    totalGas += (gas[j] - cost[j]);
                    j = (j+1)%gas.length; //control rollover.
                    moves++;
                }
                if (moves == gas.length) return i;
            }

            i = (i+1)%gas.length; //control rollover.
        }
        return -1;
    }
}
