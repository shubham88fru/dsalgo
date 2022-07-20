package basics.searching;

// if x is perfect square, return root
// else return floor of square root.
public class SquareRoot {

    //Naive soln
    //T: O(el^1/2)
    int squareRootOfNaive(int el) {
        int i =1;
        while (i*i<=el) i++;
        return i-1;
    }


    //Using binary search.
    //T:O(logn) which is better that naive complexity
    int squareRootOf(int el) {
        int low = 1, high = el, ans = -1;
        while (low<=high) {
            int mid = (low+high)/2;
            int mSq = mid*mid;
            if(mSq== el) return mid;
            else if(mSq>el){
                high = mid  - 1;
            } else {
                low = mid + 1;
                ans = mid;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        SquareRoot sqrt = new SquareRoot();
        System.out.println(sqrt.squareRootOfNaive(4));
        System.out.println(sqrt.squareRootOfNaive(16));
        System.out.println(sqrt.squareRootOfNaive(18));

        System.out.println("-----------------------");
        System.out.println(sqrt.squareRootOf(4));
        System.out.println(sqrt.squareRootOf(16));
        System.out.println(sqrt.squareRootOf(18));
    }
}
