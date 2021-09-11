package dto;

import java.util.Map;

/**
 * Класс для хранения решенной карты.
 * Используется для подсчета f-score для каждого стейта.
 */
public class Goal {
    public final Map<Integer, Coordinate> goalMap;
    public final int[][] matrix;

    public Goal(Map<Integer, Coordinate> goalMap, int[][] matrix) {
        this.goalMap = goalMap;
        this.matrix = matrix;
    }
}
