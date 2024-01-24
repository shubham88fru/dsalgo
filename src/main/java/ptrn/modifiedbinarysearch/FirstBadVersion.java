package ptrn.modifiedbinarysearch;

//@link - https://leetcode.com/problems/first-bad-version/description/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/5246990712832000
public class FirstBadVersion extends VersionControl {
    public int firstBadVersion(int n) {
        return binarySearch(n);
    }

    private int binarySearch(int n) {
        int start = 1;
        int end = n;

        int firstBad = -1;

        //do binary search (to minimize api calls)
        while (start <= end) {
            int mid = (start + (end-start)/2);

            //since all versions after a bad version are bad too,
            //if we find a bad version, it is possible that versions before it
            //could be bad as well, so we move to left half and do binary search to see
            //if we can find an earlier bad version.
            //Otherwise, if curr element is not bad version, then all elements before
            //it can't be bad as well, so we move to right half and do binary serach.
            if (isBadVersion(mid)) {
                firstBad = mid; //record as a potential answer and..
                end = mid - 1; //..continue checking for a earlier version.
            } else {
                start = mid + 1; //left half can't be bad, move to right half.
            }
        }

        return firstBad;
    }
}

//dummy. Actual in leetcode.
class VersionControl {
    public boolean isBadVersion(int el) {
        return true;
    }
}
