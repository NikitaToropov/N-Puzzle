package dto;

import java.util.Arrays;

public class State implements Comparable<State>{
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
        return -(f - state.f);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return Arrays.equals(matrix, state.matrix);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(matrix);
    }
}
