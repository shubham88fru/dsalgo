package lc_potd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//@link - https://leetcode.com/problems/24-game/description/
//@check - https://www.youtube.com/watch?v=mmu3_8n0fVM&ab_channel=codestorywithMIK
public class TwentyFourGame {
    public boolean judgePoint24(int[] cards) {
        return mikssol(cards);
    }

    /**
     Coded by me based
     on mik's explanation.
     */
    private boolean mikssol(int[] cards) {
        double eps = 0.01;
        List<Double> doubles = Arrays.stream(cards).mapToObj(card -> (double)card).toList();
        return backtracking(doubles, eps);
    }

    private boolean backtracking(List<Double> cards, double eps) {
        if (cards.size()==1 && Math.abs(cards.get(0)-24) <= eps) return true;
        else if (cards.size() == 1) return false;

        for (int i=0; i<cards.size(); i++) {
            for (int j=0; j<cards.size(); j++) {
                if (i==j) continue;

                List<Double> updatedCards = new ArrayList<>();
                for (int k=0; k<cards.size(); k++) {
                    if (k != i && k != j) updatedCards.add(cards.get(k));
                }

                double[] ops = {cards.get(i)+cards.get(j), cards.get(i)-cards.get(j),
                        cards.get(i)*cards.get(j), cards.get(i)/cards.get(j)};
                for (int l=0; l<4; l++) {
                    updatedCards.add(ops[l]);
                    if (backtracking(updatedCards, eps)) return true;
                    updatedCards.remove(updatedCards.size()-1);
                }

            }
        }

        return false;
    }
}
