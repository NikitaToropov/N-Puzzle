package utils;

import dto.Coordinate;
import dto.State;

import java.util.Map;
import java.util.PriorityQueue;

public class BoardUtil {
    public static final int EMPTY_CELL_VALUE = 0;
    public final Map<Integer, Coordinate> goal;

    public  BoardUtil(Map<Integer, Coordinate> goal) {
        this.goal = goal;
    }

    /**
     * Метод печать матрицы состояния.
     * @param state
     */
    public static void printState(State state) {
        printState(state.matrix);
    }

    /**
     * Метод печать матрицы состояния.
     * @param matrix
     */
    public static void printState(int[][] matrix) {
        System.out.println();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.printf("%2d ", matrix[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * H-schore - сумма Манхеттенских расстояния для всех ячеек состояния.
     */
    public int countHScoreForState(int[][] state) {
        int sum = 0;

        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state.length; j++) {
                if (state[i][j] == 0)
                    continue;
                sum += getManhattanDistance(state, i, j);
            }
        }
        return sum;
    }

    /**
     * Манхеттенское Расстояние ячейки до его финального положения.
     * @param state Текущее состояние
     * @return Количество шагов до целевого положения.
     */
    public int getManhattanDistance(int[][] state, int i, int j) {
        Coordinate current = new Coordinate(i, j, state[i][j]);
        return Math.abs(current.i - goal.get(current.val).i) + Math.abs(current.j - goal.get(current.val).j);
    }

    /**
     * TODO исключить прошлый стейт
     * Метод раскрытия новых стейтов.
     */
    public PriorityQueue<State> expandTheState(State previousState) {
        PriorityQueue<State> expandedStates = new PriorityQueue<>();
        int i;
        int j;

        if (isCorrectCoordinates(
                i = previousState.emptyCell.i - 1,
                j = previousState.emptyCell.j,
                previousState)) {
            expandedStates.add(getNewState(i, j, previousState));
        }
        if (isCorrectCoordinates(
                i = previousState.emptyCell.i + 1,
                j = previousState.emptyCell.j,
                previousState)) {
            expandedStates.add(getNewState(i, j, previousState));

        }
        if (isCorrectCoordinates(
                i = previousState.emptyCell.i,
                j = previousState.emptyCell.j - 1,
                previousState)) {
            expandedStates.add(getNewState(i, j, previousState));
        }
        if (isCorrectCoordinates(
                i = previousState.emptyCell.i,
                j = previousState.emptyCell.j + 1,
                previousState)) {
            expandedStates.add(getNewState(i, j, previousState));
        }
        return expandedStates;
    }

    /**
     * Конструктор DTO нового состояния.
     *
     * @param newI
     * @param newJ
     * @param previousState
     * @return
     */
    private State getNewState(int newI, int newJ, State previousState) {
        int[][] matrix = new int[previousState.matrix.length][previousState.matrix.length];

        for (int i = 0; i < previousState.matrix.length; i++) {
            for (int j = 0; j < previousState.matrix.length; j++) {
                matrix[i][j] = previousState.matrix[i][j];
            }
        }
        swapCellsInMatrix(
                matrix,
                newI,
                newJ,
                previousState.emptyCell.i,
                previousState.emptyCell.j);
        return new State(
                matrix,
                previousState.g + 1,
                countHScoreForState(matrix) + previousState.g + 1,
                new Coordinate(newI, newJ, EMPTY_CELL_VALUE),
                previousState
        );
    }

    /**
     * Метод перемещения ячейки на пустое место.
     * @param matrix
     * @param firstI
     * @param firstJ
     * @param secondI
     * @param secondJ
     */
    private void swapCellsInMatrix(int[][] matrix, int firstI, int firstJ, int secondI, int secondJ) {
        int tmp = matrix[firstI][firstJ];
        matrix[firstI][firstJ] = matrix[secondI][secondJ];
        matrix[secondI][secondJ] = tmp;
    }

    private boolean isCorrectCoordinates(int i, int j, State state) {
        return i >= 0 && i < state.matrix.length && j >= 0 && j < state.matrix.length;
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
}
