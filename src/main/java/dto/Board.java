package dto;

public class Board {
    public final int[][] state;
    public final int g; // номер шага
    public final int h; // сумма шагов до цели
    public final int size;

    public Board(int[][] start, int size, int g, int h) {
        this.state = start;
        this.size = size;
        this.g = g;
        this.h = h;
    }
}
