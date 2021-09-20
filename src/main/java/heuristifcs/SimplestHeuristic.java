package heuristifcs;

import dto.Goal;

public class SimplestHeuristic implements Heuristic {
    /**
     * H-score - сумма ячеек, которые стоят не на своем месте.
     */
    @Override
    public int countHScore(int[][] state, Goal goal) {
        int sum = 0;

        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state.length; j++) {
                if (state[i][j] == 0)
                    continue;
                sum++;
            }
        }
        return sum;
    }
}
