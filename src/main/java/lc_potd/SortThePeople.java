package lc_potd;

//@link - https://leetcode.com/problems/sort-the-people/
public class SortThePeople {
    public String[] sortPeople(String[] names, int[] heights) {

        //contant space.
        for (int i=0; i<names.length; i++) {
            int max = Integer.MIN_VALUE;
            int maxi = -1;
            for (int j=i; j<names.length; j++) {
                if (heights[j] > max) {
                    max = heights[j];
                    maxi = j;
                }
            }
            String tempn = names[i];
            names[i] = names[maxi];
            names[maxi] = tempn;

            int temph = heights[i];
            heights[i] = heights[maxi];
            heights[maxi] = temph;
        }

        return names;
    }
}
