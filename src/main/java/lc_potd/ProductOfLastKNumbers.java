package lc_potd;

import java.util.ArrayList;
import java.util.List;

//@link - https://leetcode.com/problems/product-of-the-last-k-numbers/
//@check - https://www.youtube.com/watch?v=Jvu3hd8A2rg&t=1192s&ab_channel=codestorywithMIK
public class ProductOfLastKNumbers {
    //1) Miks approach. Ideal soln.
    //True O(1) solution.
    static class ProductOfNumbers {
        List<Integer> stream;

        public ProductOfNumbers() {
            stream = new ArrayList<>();
        }

        public void add(int num) {
            int n = stream.size();
            if (num == 0) {
                //If num is zero, then any last K product
                //call that includes this zero will result
                //in a zero, so we simply clear out the stream.
                //and handle k > n in the getProduct API.
                stream.clear();
            } else if (n == 0) {
                stream.add(num);
            } else {
                int prevPdt = stream.get(n-1);
                stream.add(prevPdt*num);
            }
        }

        public int getProduct(int k) {
            int n = stream.size();

            if (k > n) return 0;
            if (k == stream.size()) return stream.get(n-1);

            int prevPdt = stream.get(n-k-1);

            return stream.get(n-1)/prevPdt;
        }
    }


    //2) My soln. Isn't ideal.
    //Problems: First, it uses two lists.
    //Second, in worst case, the get product call can be O(k).
    static class ProductOfNumbers2 {
        List<Integer> nums;
        List<Integer> pp;

        public ProductOfNumbers2() {
            pp = new ArrayList<>();
            nums = new ArrayList<>();
        }

        public void add(int num) {
            if (pp.size() == 0) {
                pp.add(num);
            } else {
                int prev = pp.get(pp.size()-1);
                pp.add(prev*num);
            }
            nums.add(num);

        }

        public int getProduct(int k) {
            if (k == pp.size()) return pp.get(pp.size()-1);
            int prev = pp.get(pp.size()-k-1);
            if (prev == 0) {
                int pdt = 1;
                for (int i=(nums.size()-k); i<nums.size(); i++) {
                    pdt *= nums.get(i);
                }
                return pdt;
            }

            return pp.get(pp.size()-1)/prev;
        }
    }
}
