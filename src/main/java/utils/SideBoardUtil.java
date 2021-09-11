package utils;

import dto.Coordinate;
import dto.Goal;
import dto.State;

import java.util.PriorityQueue;

public class SideBoardUtil extends AbstractBoardUtil {

    private int l;
    private int r;
    private int u;
    private int d;
    public static final int NOT_SOLVED = 0;
    public static final int SOLVED = 1;

    public final int[][] solvingMatrix;

    public SideBoardUtil(Goal goal) {
        super(goal);
        this.solvingMatrix = getEmptySolvingMatrix(goal.matrix.length);
        this.l = 0;
        this.r = 0;
        this.u = 0;
        this.d = 0;
    }

    /**
     * H-schore - для последнего квадрата 3x3
     */
    public int countHScoreForLastSquare(int[][] state) {
        int sum = 0;

        for (int i = l; i <= r; i++) {
            for (int j = u; j <= d; j++) {
                if (state[i][j] == 0)
                    continue;
                sum += getManhattanDistance(state, i, j);
            }
        }
        return sum;
    }

    private boolean isCorrectCoordinates(int i, int j, State state) {
        return i >= 0
                && i < state.matrix.length
                && j >= 0
                && j < state.matrix.length
                && solvingMatrix[i][j] == NOT_SOLVED;
    }

    /**
     * Конструктор DTO нового состояния.
     */
    private State getNewState(int newI, int newJ, State previousState) {
        int[][] matrix = copyMatrix(previousState.matrix);
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
     * TODO нужно добавить счетчик для последнего квадрата 3x3.
     */
    private int countHScoreForState(int[][] matrix) {
        if (l == r) {
            return verticalHScore(matrix);
        } else if (u == d) {
            return horizontalHScore(matrix);
        } else {
            return -10;
//            return countHScoreForLastSquare(matrix);
        }
    }

    /**
     * H-score для горизонтальных сторон.
     */
    public int horizontalHScore(int[][] state) {
        int sum = 0;
        for (int j = l, i = u; j <= r; j++) {
            sum += getReverseManhattanDistance(state, i, j);
        }
        return sum;
    }

    /**
     * H-score для вертикальных сторон.
     */
    public int verticalHScore(int[][] state) {
        int sum = 0;
        for (int i = u, j = l; i <= d; i++) {
            sum += getReverseManhattanDistance(state, i, j);
        }
        return sum;
    }

    private int[][] getEmptySolvingMatrix(int size) {
        int[][] matrix = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = NOT_SOLVED;
            }
        }
        return matrix;
    }

    public void setLineAsSolved() {
        if (u == d) {
            setHorizontalAsSolved();
        } else if (l == r) {
            setVerticalAsSolved();
        }
    }

    /**
     * Манхеттенское расстояние целевой линии до текущего состояния.
     */
    private int getReverseManhattanDistance(int[][] state, int i, int j) {
        for (int k = 0; k < state.length; k++) {
            for (int m = 0; m < state.length; m++) {
                if (state[k][m] == goal.matrix[i][j]) {
                    return getManhattanDistanceByCoordinates(i, j, k, m);
                }
            }
        }
        return 0;
    }

    public int getManhattanDistanceByCoordinates(int i1, int j1, int i2, int j2) {
        return Math.abs(i1 - i2) + Math.abs(j1 - j2);
    }

    private void setHorizontalAsSolved() {
        for (int j = l, i = u; j <= r; j++) {
            solvingMatrix[i][j] = SOLVED;
        }
    }

    private void setVerticalAsSolved() {
        for (int i = u, j = l; i <= d; i++) {
            solvingMatrix[i][j] = SOLVED;
        }
    }

    public void printResolvingReverse(State finish) {
        System.out.println("+++++++++++++++++++++++++++++++++++++++++");
        System.out.println("+++++++++++ REVERSE RESOLVING +++++++++++");
        while (finish != null) {
            printState(finish);
            finish = finish.parent;
        }
        System.out.println("+++++++++++++++++++ END +++++++++++++++++");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++");
    }

    public void setLimits(int l, int r, int u, int d) {
        this.l = l;
        this.r = r;
        this.u = u;
        this.d = d;
    }

    /**
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
        // down
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

}
