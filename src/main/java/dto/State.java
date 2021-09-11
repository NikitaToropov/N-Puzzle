package dto;

import java.util.Arrays;

/**
 * Класс для хранения параметров состояния.
 */
public class State implements Comparable<State> {

    public final static int EMPTY_CELL_VALUE = 0;
    public final int[][] matrix; // состояние игрового поля
    public final int f; // f-score
    public final int g; // g-score - номер шага
    public final Coordinate emptyCell; // координаты пустой ячейки
    public final State parent; // прошлое (родительское состояние)

    public State(int[][] start, int g, int f, Coordinate emptyCell, State parent) {
        this.matrix = start;
        this.g = g;
        this.f = f;
        this.emptyCell = emptyCell;
        this.parent = parent;
    }

    @Override
    public int compareTo(State state) {
        return f - state.f;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        for (int i = 0; i < matrix.length; i++) {
            if (!Arrays.equals(matrix[i], state.matrix[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hashCode = 0;
        for (int[] line : matrix) {
            hashCode += Arrays.hashCode(line);
        }
        return hashCode;
    }

    /**
     * Копия матрицы текущего состояния.
     */
    public int[][] copyMatrix() {
        return Arrays.stream(matrix)
                .map(int[]::clone)
                .toArray(int[][]::new);
    }
}
