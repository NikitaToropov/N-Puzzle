package heuristifcs;

import dto.Goal;

/**
 * Интерфейс для разных эвристик.
 */
public interface Heuristic {
    /**
     * Метод расчета H-score для всех ячеек карты.
     * @param matrix Текущее состояние поля.ы
     * @param goal DTO целевого состояния.
     */
    int countHScore(int[][] matrix, Goal goal);
}
