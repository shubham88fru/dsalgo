package basics.recursion;

public class AllSubsetsOfString {

    //My Soln, doesn't work.
    static String printSubsets(String s) {
        int l = s.length();
        if (l<=0) return "";
        System.out.println(s+ "");
        return printSubsets(s.charAt(l-1) + printSubsets(s.substring(0, l - 2)));
    }

    static void printSub(String str, String curr, int index){

        if (index==str.length()) {
            System.out.print(curr + " ");
            return;
        }

        printSub(str, curr, index+1);
        printSub(str, curr+str.charAt(index), index+1);
    }

    public static void main(String[] args) {
        //printSubsets("abc");

        printSub("ABC", "", 0);
        System.out.println();
        printSub("ABCD", "", 0);
        System.out.println();
        printSub("ABCE", "", 0);
    }
}
