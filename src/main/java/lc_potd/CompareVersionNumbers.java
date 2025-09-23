package lc_potd;

//@link - https://leetcode.com/problems/compare-version-numbers/submissions/1248271216/?
public class CompareVersionNumbers {
    public int compareVersion(String version1, String version2) {
        return revise(version1, version2);
    }

    private int approach2(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");

        int p1 = 0;
        int p2 = 0;
        while (p1 < v1.length && p2 < v2.length) {
            int v1p1 = Integer.parseInt(v1[p1]);
            int v2p2 = Integer.parseInt(v2[p2]);

            if (v1p1 > v2p2) return 1;
            else if (v2p2 > v1p1) return -1;

            p1 += 1;
            p2 += 1;
        }

        while (p1 < v1.length) {
            if (Integer.parseInt(v1[p1]) > 0) return 1;
            p1 += 1;
        }

        while (p2 < v2.length) {
            if (Integer.parseInt(v2[p2]) > 0) return -1;
            p2 += 1;
        }

        return 0;
    }

    private int revise(String version1, String version2) {
        int n1 = version1.length();
        int n2 = version2.length();

        int p1 = 0;
        int p2 = 0;
        while (p1 < n1 && p2 < n2) {
            StringBuilder sb1 = new StringBuilder();
            while (p1 < n1 && (version1.charAt(p1) != '.')) {
                sb1.append(version1.charAt(p1));
                p1 += 1;
            }

            StringBuilder sb2 = new StringBuilder();
            while (p2 < n2 && (version2.charAt(p2) != '.')) {
                sb2.append(version2.charAt(p2));
                p2 += 1;
            }

            int i1 = Integer.parseInt(sb1.toString());
            int i2 = Integer.parseInt(sb2.toString());
            if (i1 < i2) return -1;
            if (i1 > i2) return 1;
            p1 += 1;
            p2 += 1;

        }

        if (p1 == n1 && p2 == n2) return 0;
        while (p1 < n1) {
            StringBuilder sb1 = new StringBuilder();
            while (p1 < n1 && (version1.charAt(p1) != '.')) {
                sb1.append(version1.charAt(p1));
                p1 += 1;
            }
            int i1 = Integer.parseInt(sb1.toString());
            if (i1 > 0) return 1;
            p1 += 1;
        }

        while (p2 < n2) {
            StringBuilder sb2 = new StringBuilder();
            while (p2 < n2 && (version2.charAt(p2) != '.')) {
                sb2.append(version2.charAt(p2));
                p2 += 1;
            }
            int i2 = Integer.parseInt(sb2.toString());
            if (i2 > 0) return -1;
            p2 += 1;
        }

        return 0;
    }
}
