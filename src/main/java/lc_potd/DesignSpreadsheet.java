package lc_potd;

//@link - https://leetcode.com/problems/design-spreadsheet/description
public class DesignSpreadsheet {
    int[][] spreadsheet;

    public DesignSpreadsheet(int rows) {
        spreadsheet = new int[rows][26];
    }

    public void setCell(String cell, int value) {
        int[] c = parseCell(cell);
        this.spreadsheet[c[0]][c[1]] = value;
    }

    public void resetCell(String cell) {
        int[] c = parseCell(cell);
        this.spreadsheet[c[0]][c[1]] = 0;
    }

    public int getValue(String formula) {
        return parseFormula(formula);
    }

    private int[] parseCell(String cell) {
        return new int[]{
                Integer.parseInt(cell.substring(1)) - 1,
                (int)cell.charAt(0) - 65
        };
    }

    private int parseFormula(String formula) {
        String[] f = formula.substring(1).split("\\+");

        int a = 0;
        if (Character.isDigit(f[0].charAt(0))) {
            a = Integer.parseInt(f[0]);
        } else {
            int[] c = parseCell(f[0]);
            a = this.spreadsheet[c[0]][c[1]];
        }



        int b = 0;
        if (Character.isDigit(f[1].charAt(0))) {
            b = Integer.parseInt(f[1]);
        } else {
            int[] c = parseCell(f[1]);
            b = this.spreadsheet[c[0]][c[1]];
        }

        return a + b;
    }
}
