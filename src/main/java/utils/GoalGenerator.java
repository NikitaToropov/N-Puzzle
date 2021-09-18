package utils;

import dto.Coordinate;
import dto.Goal;
import dto.State;

import java.util.HashMap;
import java.util.Map;

/**
 * Утилитный класс для генерации решенного пазла с заданой длиной стороны.
 */
public class GoalGenerator {

    private GoalGenerator() {
    }

    public static Goal getGoal(int n) {
        int[][] matrix = getSpiralMatrix(n);
        return createGoal(matrix);
    }

    /**
     * Метод генерирует матрицу заполненную по-спирали,
     * где последнее/центральное значение - 0(ZERO).
     *
     * @param n Длина стороны матрицы.
     * @return Матрица int[n][n].
     */
    public static int[][] getSpiralMatrix(int n) {
        int[][] matrix = new int[n][n];
        int biggestVal = n * n;
        int l = 0, t = 0, r = n - 1, b = n - 1;

        int val = 1;
        while (l <= r && t <= b) {
            // right
            for (int j = l; j <= r; j++) {
                matrix[t][j] = biggestVal == val++
                        ? State.EMPTY_CELL_VALUE
                        : val - 1;
            }
            t++;
            // down
            for (int i = t; i <= b; i++) {
                matrix[i][r] = biggestVal == val++
                        ? State.EMPTY_CELL_VALUE
                        : val - 1;
            }
            r--;
            // left
            for (int j = r; j >= l; j--) {
                matrix[b][j] = biggestVal == val++
                        ? State.EMPTY_CELL_VALUE
                        : val - 1;
            }
            b--;
            // up
            for (int i = b; i >= t; i--) {
                matrix[i][l] = biggestVal == val++
                        ? State.EMPTY_CELL_VALUE
                        : val - 1;
            }
            l++;
        }
        return matrix;
    }

    /**
     * Метод генерирует Map для быстрого доступа к координатим ячеек по их значению.
     */
    private static Goal createGoal(int[][] matrix) {
        Map<Integer, Coordinate> goalMap = new HashMap<>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                goalMap.put(matrix[i][j], new Coordinate(i, j, matrix[i][j]));
            }
        }
        return new Goal(goalMap, matrix);
    }
}
