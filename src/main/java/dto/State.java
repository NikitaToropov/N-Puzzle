package dto;

import java.util.Arrays;

public class State implements Comparable<State> {
    public final int[][] matrix;
    public final int f; // f-score
    public final int g; // g-score - номер шага
    public final Coordinate emptyCell; // координаты пустой ячейки

    public State(int[][] start, int g, int f, Coordinate emptyCell) {
        this.matrix = start;
        this.g = g;
        this.f = f;
        this.emptyCell = emptyCell;
    }

    @Override
    public int compareTo(State state) {
        return (f - state.f);
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
}
