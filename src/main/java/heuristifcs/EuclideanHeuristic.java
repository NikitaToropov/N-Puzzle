package heuristifcs;

import dto.Coordinate;
import dto.Goal;

public class EuclideanHeuristic implements Heuristic {
    /**
     * H-score - сумма Эвклидовых расстояний для всех ячеек состояния.
     */
    @Override
    public int countHScore(int[][] state, Goal goal) {
        int sum = 0;

        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state.length; j++) {
                if (state[i][j] == 0)
                    continue;
                sum += getEuclideanDistance(state, i, j, goal);
            }
        }
        return sum;
    }

    /**
     * Эвклидова расстояние ячейки до его финального положения.
     */
    public int getEuclideanDistance(int[][] state, int i, int j, Goal goal) {
        Coordinate current = new Coordinate(i, j, state[i][j]);

        return (int) Math.round(Math.sqrt(Math.pow(Math.abs(current.i - goal.goalMap.get(current.val).i), 2) + Math.pow(Math.abs(current.j - goal.goalMap.get(current.val).j), 2)));
//        return (int) Math.round(Math.pow(Math.abs(current.i - goal.goalMap.get(current.val).i), 2) + Math.pow(Math.abs(current.j - goal.goalMap.get(current.val).j), 2)); // это магия, находит решение быстро но не самое оптимальное
    }
}
