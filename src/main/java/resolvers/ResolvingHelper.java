package resolvers;

import dto.Coordinate;
import dto.Goal;
import dto.State;
import heuristifcs.Heuristic;
import utils.BoardUtil;

import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class ResolvingHelper {
    public final Goal goal;
    public final Heuristic heuristic;

    public ResolvingHelper(Goal goal, Heuristic heuristic) {
        this.goal = goal;
        this.heuristic = heuristic;
    }
    
    /**
     * Метод раскрытия новых стейтов.
     */
    public PriorityQueue<State> expandTheState(State previousState) {
        PriorityQueue<State> expandedStates = new PriorityQueue<>();
        int i, j, size = previousState.matrix.length;

        if (BoardUtil.isCorrectCoordinates(i = previousState.emptyCell.i - 1, j = previousState.emptyCell.j, size)) {
            expandedStates.add(getNewState(i, j, previousState));
        }
        if (BoardUtil.isCorrectCoordinates(i = previousState.emptyCell.i + 1, j = previousState.emptyCell.j, size)) {
            expandedStates.add(getNewState(i, j, previousState));
        }
        if (BoardUtil.isCorrectCoordinates(i = previousState.emptyCell.i, j = previousState.emptyCell.j - 1, size)) {
            expandedStates.add(getNewState(i, j, previousState));
        }
        if (BoardUtil.isCorrectCoordinates(i = previousState.emptyCell.i, j = previousState.emptyCell.j + 1, size)) {
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
        BoardUtil.swapCellsInMatrix(
                matrix,
                newI,
                newJ,
                previousState.emptyCell.i,
                previousState.emptyCell.j);
        return new State(
                matrix,
                previousState.g + 1,
                heuristic.countHScore(matrix, goal) + previousState.g + 1,
                new Coordinate(newI, newJ, State.EMPTY_CELL_VALUE),
                previousState
        );
    }

    private boolean isGranny(State state) {
        return state.parent != null
                && state.parent.parent != null
                && state.parent.parent.equals(state);
    }
}
