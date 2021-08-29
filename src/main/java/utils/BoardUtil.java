package utils;

import dto.Board;
import dto.Coordinate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BoardUtil {
    public static final int EMPTY_CELL_VALUE = 0;
    public final Map<Integer, Coordinate> goal;

    public BoardUtil(Map<Integer, Coordinate> goal) {
        this.goal = goal;
    }

    public static void printState(Board board) {
        printState(board.state);
    }

    public static void printState(int[][] board) {
        System.out.println();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.printf("%2d ", board[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * H-schore - сумма Манхеттенских расстояния для всех ячеек состояния/
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

    public int getManhattanDistance(int[][] state, int i, int j) {
        Coordinate current = new Coordinate(i, j, state[i][j]);
        return Math.abs(current.i - goal.get(current.val).i) + Math.abs(current.j - goal.get(current.val).j);
    }

    /**
     * TODO исключить прошлый стейт
     * TODO может придется хранить в priorityQue
     */
    public List<Board> expandTheState(Board previousState) {
        List<Board> expandedStates = new ArrayList<>();

        if (isCorrectCoordinates(previousState.emptyCell.i - 1, previousState.emptyCell.j - 1, previousState)) {
            expandedStates.add(getNewState(previousState.emptyCell.i - 1, previousState.emptyCell.j - 1, previousState));
        }
        if (isCorrectCoordinates(previousState.emptyCell.i + 1, previousState.emptyCell.i + 1, previousState)) {
            expandedStates.add(getNewState(previousState.emptyCell.i + 1, previousState.emptyCell.j + 1, previousState));

        }
        if (isCorrectCoordinates(previousState.emptyCell.i - 1, previousState.emptyCell.i + 1, previousState)) {
            expandedStates.add(getNewState(previousState.emptyCell.i - 1, previousState.emptyCell.j + 1, previousState));

        }
        if (isCorrectCoordinates(previousState.emptyCell.i + 1, previousState.emptyCell.i - 1, previousState)) {
            expandedStates.add(getNewState(previousState.emptyCell.i + 1, previousState.emptyCell.j - 1, previousState));

        }
        return expandedStates;
    }

    private Board getNewState(int newI, int newJ, Board previousState) {
        int[][] matrix = new int[previousState.size][previousState.size];

        for (int i = 0; i < previousState.size; i++) {
            for (int j = 0; j < previousState.size; j++) {
                matrix[i][j] = previousState.state[i][j];
            }
        }
        swapCellsInMatrix(
                matrix,
                newI,
                newJ,
                previousState.emptyCell.i,
                previousState.emptyCell.j);
        return new Board(
                matrix,
                previousState.size,
                previousState.g + 1,
                countHScoreForState(matrix) + previousState.g + 1,
                new Coordinate(newI, newJ, EMPTY_CELL_VALUE)
        );
    }

    private void swapCellsInMatrix(int[][] matrix, int firstI, int firstJ, int secondI, int secondJ) {
        int tmp = matrix[firstI][firstJ];
        matrix[firstI][firstJ] = matrix[secondI][secondJ];
        matrix[secondI][secondJ] = tmp;
    }

    private boolean isCorrectCoordinates(int i, int j, Board board) {
        return i >= 0 && i < board.size && j >= 0 && j < board.size;
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
