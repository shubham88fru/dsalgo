package lc_potd;

import java.util.*;

//@link - https://leetcode.com/problems/maximum-square-area-by-removing-fences-from-a-field/description/
//@check - https://www.youtube.com/watch?v=7H_tzns_G3M
public class MaximumSquareAreaByRemovingFencesFromAField {
    public int maximizeSquareArea(int m, int n, int[] hFences, int[] vFences) {
        // return mikssol(m, n, hFences, vFences);
        // return mikssolOptimized1(m, n, hFences, vFences);
        return mikssolOptimal(m, n, hFences, vFences);
    }

    /**
     Avoid sorting by keeping
     track of absolute diffs.
     */
    private int mikssolOptimal(int m, int n, int[] hFences, int[] vFences) {
        //populate horizontals.
        List<Integer> hBars = new ArrayList<>();
        for (int h: hFences) hBars.add(h);
        hBars.add(1);
        hBars.add(m);
        Set<Integer> hDistances = new HashSet<>();
        for (int i=0; i<hBars.size(); i++) {
            for (int j=i+1; j<hBars.size(); j++) {
                hDistances.add(Math.abs(hBars.get(j)-hBars.get(i)));
            }
        }

        long maxSide = 0;

        //populate verticals.
        List<Integer> vBars = new ArrayList<>();
        for (int v: vFences) vBars.add(v);
        vBars.add(1);
        vBars.add(n);
        Set<Integer> vDistances = new HashSet<>();
        for (int i=0; i<vBars.size(); i++) {
            for (int j=i+1; j<vBars.size(); j++) {
                int side = Math.abs(vBars.get(j)-vBars.get(i));
                if (hDistances.contains(side)) maxSide = Math.max(maxSide, side);
            }
        }

        if (maxSide == 0) return -1;

        return (int)((maxSide*maxSide)%1000000007);
    }

    private int mikssolOptimized1(int m, int n, int[] hFences, int[] vFences) {
        //populate horizontals.
        List<Integer> hBars = new ArrayList<>();
        for (int h: hFences) hBars.add(h);
        hBars.add(1);
        hBars.add(m);
        Collections.sort(hBars);
        Set<Integer> hDistances = new HashSet<>();
        for (int i=0; i<hBars.size(); i++) {
            for (int j=i+1; j<hBars.size(); j++) {
                hDistances.add(hBars.get(j)-hBars.get(i));
            }
        }

        long maxSide = 0;

        //populate verticals.
        List<Integer> vBars = new ArrayList<>();
        for (int v: vFences) vBars.add(v);
        vBars.add(1);
        vBars.add(n);
        Collections.sort(vBars);
        Set<Integer> vDistances = new HashSet<>();
        for (int i=0; i<vBars.size(); i++) {
            for (int j=i+1; j<vBars.size(); j++) {
                int side = vBars.get(j)-vBars.get(i);
                if (hDistances.contains(side)) maxSide = Math.max(maxSide, side);
            }
        }

        if (maxSide == 0) return -1;

        return (int)((maxSide*maxSide)%1000000007);
    }

    /**
     This is basically a brute force soln.
     I couldn't come up with this myself.
     The idea is to basically try each possible
     vertical side and try to find a pairing
     horizontal side.
     */
    private int mikssol(int m, int n, int[] hFences, int[] vFences) {
        //populate horizontals.
        List<Integer> hBars = new ArrayList<>();
        for (int h: hFences) hBars.add(h);
        hBars.add(1);
        hBars.add(m);
        Collections.sort(hBars);
        Set<Integer> hDistances = new HashSet<>();
        for (int i=0; i<hBars.size(); i++) {
            for (int j=i+1; j<hBars.size(); j++) {
                hDistances.add(hBars.get(j)-hBars.get(i));
            }
        }

        //populate verticals.
        List<Integer> vBars = new ArrayList<>();
        for (int v: vFences) vBars.add(v);
        vBars.add(1);
        vBars.add(n);
        Collections.sort(vBars);
        Set<Integer> vDistances = new HashSet<>();
        for (int i=0; i<vBars.size(); i++) {
            for (int j=i+1; j<vBars.size(); j++) {
                vDistances.add(vBars.get(j)-vBars.get(i));
            }
        }

        long maxSide = 0;
        for (int h: hDistances) {
            if (vDistances.contains(h)) maxSide = Math.max(maxSide, h);
        }

        if (maxSide == 0) return -1;

        return (int)((maxSide*maxSide)%1000000007);
    }
}
