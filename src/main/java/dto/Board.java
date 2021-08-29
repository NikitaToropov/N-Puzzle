package dto;

public class Board {
    public final int[][] state;
    public final int f; // f-score
    public final int g; // g-score - номер шага
    public final Coordinate emptyCell; // координаты пустой ячейкиw
    public final int size;

    public Board(int[][] start, int size, int g, int f, Coordinate emptyCell) {
        this.state = start;
        this.size = size;
        this.g = g;
        this.f = f;
        this.emptyCell = emptyCell;
    }
}
