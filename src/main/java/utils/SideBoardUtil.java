package utils;

import dto.Coordinate;
import dto.Goal;
import dto.State;

public class SideBoardUtil extends AbstractBoardUtil {

    private int l;
    private int r;
    private int u;
    private int d;
    public static final int NOT_SOLVED = 0;
    public static final int SOLVED = 1;

    final int[][] solvingMatrix;

    public SideBoardUtil(Goal goal) {
        super(goal);
        this.solvingMatrix = getEmptySolvingMatrix(goal.goalMap.size());
        this.l = 0;
        this.r = goal.matrix.length;
        this.u = 0;
        this.d = goal.matrix.length;
    }

    /**
     * H-schore - для последнего квадрата 3x3
     */
    public int countHScoreForLastSquare(int[][] state, int l, int r, int u, int d) {
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
     * TODO нужно добавить счетчик для последнего квадрата 3x3.
     */
    private int countHScoreForState(int[][] matrix) {
        if (l == r) {
            return verticalHScore(matrix, l, u, d);
        } else if (u == d) {
            return horizontalHScore(matrix, u, l, r);
        } else {
            return countHScoreForLastSquare(matrix, l, r, u, d);
        }
    }

    /**
     * H-score для горизонтальных сторон.
     */
    public int horizontalHScore(int[][] state, int i, int l, int r) {
        int sum = 0;
        for (int j = l; j <= r; j++) {
            sum += getManhattanDistance(state, i, j);
        }
        return sum;
    }

    /**
     * H-score для вертикальных сторон.
     */
    public int verticalHScore(int[][] state, int j, int u, int d) {
        int sum = 0;
        for (int i = u; i <= d; i++) {
            sum += getManhattanDistance(state, i, j);
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

    public void setHorizontalAsSolved(int i, int l, int r) {
        for (int j = l; j <= r; j++) {
            solvingMatrix[i][j] = SOLVED;
        }
    }

    public void setVerticalAsSolved(int j, int u, int d) {
        for (int i = u; i <= d; i++) {
            solvingMatrix[i][j] = SOLVED;
        }
    }
}
