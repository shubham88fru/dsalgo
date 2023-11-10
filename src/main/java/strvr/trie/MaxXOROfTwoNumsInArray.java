package strvr.trie;

//@link - https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/
//@strvr - https://takeuforward.org/data-structure/maximum-xor-of-two-numbers-in-an-array/
public class MaxXOROfTwoNumsInArray {
    public int findMaximumXOR(int[] nums) {
        /*
            Per strvr the intuition for this problem
            if given a num we can figure out how to find the max
            xor of the num with a given set of nums, then the problem is solved.
        */
        Trie2 trie = new Trie2();
        //store all nums of arr1 to trie.
        for (int num: nums) trie.insert(num);

        int maxi = 0;
        //Find max xor of each element of nums with others in the array
        //and return the max. Note that the condition on order of i and j
        //in the question seems meaningless since a^b is same as b^a.
        for (int num: nums) maxi = Math.max(maxi, trie.getMax(num));

        return maxi;
    }
}


class Node {
    Node links[] = new Node[2]; //0 and 1.

    public Node() {

    }

    boolean containsKey(int ind) {
        return (links[ind] != null);
    }

    Node get(int ind) {
        return links[ind];
    }

    void put(int ind, Node node) {
        links[ind] = node;
    }
}

class Trie2 {
    private static Node root;

    Trie2() {
        root = new Node();
    }

    public static void insert(int num) {
        Node node = root;
        for (int i=31; i>=0; i--) {
            int bit = (num >> i) & 1;
            if (!node.containsKey(bit)) node.put(bit, new Node());
            node = node.get(bit);
        }
    }

    //Given a num, this function returns the max
    //possible xor of the num with other elements
    //in the trie.
    public int getMax(int num) {
        Node node = root;
        int maxNum = 0;
        for (int i=31; i>=0; i--) {
            int bit = (num >> i) & 1;
            //opposite of bit (coz if nums's curr bit is 0, we need 1,
            //or if num's bit is 1, we need 0, so that xor is 1.)
            //Our aim is that as we keep moving along the bits of num from left to right,
            //we should trie to get as many set bits in maxNum as possible (the more left in binary num, higher the value)
            //The only way to get a set bit in xor is to xor it opposite bit.
            if (node.containsKey(1-bit)) {
                maxNum = maxNum | (1<<i); //append 1 at ith bit position in the maxNum.
                node = node.get(1-bit); //move to opposite bit.
            } else {
                node = node.get(bit); //else no choice, ith bit in maxNum remains zero and move to next bit.
            }
        }

        return maxNum;
    }
}