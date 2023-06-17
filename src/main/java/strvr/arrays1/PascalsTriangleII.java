package strvr.arrays1;

import java.util.ArrayList;
import java.util.List;

//@link - https://leetcode.com/problems/pascals-triangle-ii/description/
//@strvr - https://takeuforward.org/data-structure/program-to-generate-pascals-triangle/
public class PascalsTriangleII {
    public List<Integer> getRow(int rowIndex) {
        //generate pascals triangle and get the rowIndexth row.
        return pascalsTriangle(rowIndex+1).get(rowIndex);
    }

    private List<List<Integer>> pascalsTriangle(int numRows) {
        List<List<Integer>> pascalsTriangle = new ArrayList<>();

        for (int i=0; i<numRows; i++) {
            //elements in curr row is one more than
            //curr row's index.
            int elementsInCurrRow = i+1;

            //to store individual row's answer.
            List<Integer> currRowsList = new ArrayList<>();

            //for each row
            for (int j=0; j<elementsInCurrRow; j++) {
                //if first or last element, simply add 1.
                if (j==0 || j==elementsInCurrRow-1) {
                    currRowsList.add(1);
                } else {
                    //otherwise, each of the other elements of the row
                    //will be simply a sum of prev row's elements.
                    List<Integer> prevRow = pascalsTriangle.get(i-1);
                    currRowsList.add(prevRow.get(j-1) + prevRow.get(j));
                }
            }

            //once curr row processed, add to pascal triangle.
            pascalsTriangle.add(currRowsList);
        }

        return pascalsTriangle;
    }
}
