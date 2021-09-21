package heuristifcs;

import dto.Coordinate;
import dto.Goal;

public class ManhattanHeuristic implements Heuristic {

    /**
     * H-score - сумма Манхеттенских расстояний для всех ячеек состояния.
     */
    @Override
    public int countHScore(int[][] state, Goal goal) {
        int sum = 0;

        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state.length; j++) {
                if (state[i][j] == 0)
                    continue;
                sum += getManhattanDistance(state, i, j, goal);
            }
        }
        return sum;
    }

    /**
     * Манхеттенское Расстояние ячейки до его финального положения.
     *
     * @param state Текущее состояние
     * @return Количество шагов до целевого положения.
     */
    public int getManhattanDistance(int[][] state, int i, int j, Goal goal) {
        Coordinate current = new Coordinate(i, j, state[i][j]);
        return Math.abs(current.i - goal.goalMap.get(current.val).i) + Math.abs(current.j - goal.goalMap.get(current.val).j);
    }
}
