package utils;

import dto.Coordinate;
import dto.Goal;
import dto.State;

import java.util.PriorityQueue;

public class ResolvingHelper {
    public final Goal goal;

    public ResolvingHelper(Goal goal) {
        this.goal = goal;
    }

    /**
     * H-score - сумма Манхеттенских расстояния для всех ячеек состояния.
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
     *
     * @param state Текущее состояние
     * @return Количество шагов до целевого положения.
     */
    public int getManhattanDistance(int[][] state, int i, int j) {
        Coordinate current = new Coordinate(i, j, state[i][j]);
        return Math.abs(current.i - goal.goalMap.get(current.val).i) + Math.abs(current.j - goal.goalMap.get(current.val).j);
    }

    /**
     * Метод раскрытия новых стейтов.
     */
    public PriorityQueue<State> expandTheState(State previousState) {
        PriorityQueue<State> expandedStates = new PriorityQueue<>();
        int i, j;

        if (isCorrectCoordinates(i = previousState.emptyCell.i - 1, j = previousState.emptyCell.j)) {
            expandedStates.add(getNewState(i, j, previousState));
        }
        if (isCorrectCoordinates(i = previousState.emptyCell.i + 1, j = previousState.emptyCell.j)) {
            expandedStates.add(getNewState(i, j, previousState));
        }
        if (isCorrectCoordinates(i = previousState.emptyCell.i, j = previousState.emptyCell.j - 1)) {
            expandedStates.add(getNewState(i, j, previousState));
        }
        if (isCorrectCoordinates(i = previousState.emptyCell.i, j = previousState.emptyCell.j + 1)) {
            expandedStates.add(getNewState(i, j, previousState));
        }
        return expandedStates;
    }

    /**
     * Конструктор DTO нового состояния.
     * берет прош
     */
    private State getNewState(int newI, int newJ, State previousState) {
        int[][] matrix = previousState.copyMatrix();
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
                new Coordinate(newI, newJ, State.EMPTY_CELL_VALUE),
                previousState
        );
    }

    /**
     * Метод перемещения свопа ячеек.
     */
    private void swapCellsInMatrix(int[][] matrix, int firstI, int firstJ, int secondI, int secondJ) {
        int tmp = matrix[firstI][firstJ];
        matrix[firstI][firstJ] = matrix[secondI][secondJ];
        matrix[secondI][secondJ] = tmp;
    }

    /**
     * Метод проверяет, не выходит ли координаты за рамки карты.
     */
    private boolean isCorrectCoordinates(int i, int j) {
        return i >= 0 && i < goal.matrix.length && j >= 0 && j < goal.matrix.length;
    }

    /**
     * Конструктор стартового состояния пазла.
     *
     * @param preParty Предзаполненное стартовое состояние.
     * @return startState
     */
    public State getStartState(State preParty) {
        int[][] matrix = preParty.copyMatrix();

        return new State(
                matrix,
                0,
                countHScoreForState(matrix),
                preParty.emptyCell,
                null
        );
    }
}
