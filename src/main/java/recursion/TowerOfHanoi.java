package recursion;

/*
We have 3 towers A, B, C.
Auxillary tower is temp tower that we can use
to do the arrangement from start tower to final tower.

Rules:
1) Only one disc moves at a time.
2) No longer disc can be above smaller.
3) Only the top disc of a tower can be moved.

To find the no. of moves to move discs from tower A to tower C.
*/
public class TowerOfHanoi {

    static void printTOHMoves(int discs, char startTower, char auxillaryTower,
                              char targetTower) {
        if (discs==1) {
            System.out.println("Move disc 1 from "+ startTower+" to "+ targetTower);
            return;
        }

        //move top n-1 from a to b with c as auxiliary, recursively.
        printTOHMoves(discs-1, startTower, targetTower, auxillaryTower); // a - start, b - final, c - auxiliary.

        System.out.println("Move disc "+ discs+ " from A to C");

        //then move n-1 from b to c with a as auxiliary, recursively.
        printTOHMoves(discs-1, auxillaryTower, startTower, targetTower);// b - start, c - final, a - auxiliary.
    }

    public static void main(String[] args) {
        printTOHMoves(2, 'A', 'B', 'C');
    }
}
