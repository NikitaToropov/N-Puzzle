package utils;

import dto.Coordinate;
import dto.Goal;
import dto.State;

import java.util.PriorityQueue;

public class BoardUtil extends AbstractBoardUtil{
    public  BoardUtil(Goal goal) {
        super(goal);
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

    private boolean isCorrectCoordinates(int i, int j, State state) {
        return i >= 0 && i < state.matrix.length && j >= 0 && j < state.matrix.length;
    }
}
