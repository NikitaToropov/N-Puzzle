package utils;

import dto.Coordinate;
import dto.Goal;
import dto.State;

public abstract class AbstractBoardUtil {
    public static final int EMPTY_CELL_VALUE = 0;

    public final Goal goal;

    public AbstractBoardUtil(Goal goal) {
        this.goal = goal;
    }

    /**
     * Метод печать матрицы состояния.
     *
     * @param state
     */
    public static void printState(State state) {
        System.out.println("g-score = " + state.g + "  f-score = " + state.f);
        printMatrix(state.matrix);
    }

    /**
     * Метод печать матрицы состояния.
     *
     * @param matrix
     */
    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.printf("%2d ", matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Метод печати конечного состояния и его hashMap, где
     * key = занчение ячейки
     * value = Coordinate пара координать ячейки значения key в матрице;
     */
    public void printGoal() {
        System.out.println("+++++++++++++++++++ PRING GOAL +++++++++++++++++++");
        printMatrix(goal.matrix);
        System.out.println();
        goal.goalMap.forEach((k, v) -> System.out.println(k + ":" + " i=" + v.i + " j=" + v.j));
        System.out.println();
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++");
    }

    /**
     * Манхеттенское Расстояние ячейки до его финального положения.
     * @param state Текущее состояние
     * @return Количество шагов до целевого положения.
     */
    public int getManhattanDistance(int[][] state, int i, int j) {
        Coordinate current = new Coordinate(i, j, state[i][j]);
        return Math.abs(current.i - goal.goalMap.get(current.val).i) + Math.abs(current.j - goal.goalMap.get(current.val).j);
    }

    /**
     * Манхеттенское Расстояние ячейки до его финального положения.
     */
    public int getManhattanDistance(Coordinate first, Coordinate second) {
        return Math.abs(first.i - second.i) + Math.abs(first.j - second.j);
    }

    /**
     * Метод перемещения ячейки на пустое место.
     */
    protected void swapCellsInMatrix(int[][] matrix, int firstI, int firstJ, int secondI, int secondJ) {
        int tmp = matrix[firstI][firstJ];
        matrix[firstI][firstJ] = matrix[secondI][secondJ];
        matrix[secondI][secondJ] = tmp;
    }

    public static Coordinate getEmptyCell(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == EMPTY_CELL_VALUE) {
                    return new Coordinate(i, j, EMPTY_CELL_VALUE);
                }
            }
        }
        return null;
    }

    public int[][] copyMatrix(int[][] matrix) {
        int [][] newMatrix = new int[matrix.length][matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                newMatrix[i][j] = matrix[i][j];
            }
        }
        return newMatrix;
    }

}
