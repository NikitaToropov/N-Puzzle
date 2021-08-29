package utils;

import dto.Coordinate;

import java.util.HashMap;
import java.util.Map;

public class GoalMapGenerator {

    private GoalMapGenerator() {
    }

    public static Map<Integer, Coordinate> getGoal(int n) {
        int[][] matrix = gitSpiralMatrix(n);
        BoardUtil.printState(matrix);
        return createMap(matrix);
    }

    private static int[][] gitSpiralMatrix(int n) {
        int[][] matrix = new int[n][n];
        int m = n, l = 0, t = 0, r = m - 1, b = n - 1;

        int val = 1;
        while (l <= r && t <= b) {
            // right
            for (int i = l; i <= r; i++) {
                matrix[t][i] = val++;
            }
            t++;
            // down
            for (int i = t; i <= b; i++) {
                matrix[i][r] = val++;
            }
            r--;
            // left
            for (int i = r; i >= l; i--) {
                matrix[b][i] = val++;
            }
            b--;
            // up
            for (int i = b; i >= t; i--) {
                matrix[i][l] = val++;
            }
            l++;
        }
        return matrix;
    }

    private static Map<Integer, Coordinate> createMap(int[][] matrix) {
        Map<Integer, Coordinate> goal = new HashMap<>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                goal.put(matrix[i][j], new Coordinate(i, j, matrix[i][j]));
            }
        }
        goal.put(0, goal.get(matrix.length * matrix.length));
        return goal;
    }
}
