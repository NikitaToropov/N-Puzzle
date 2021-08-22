package dto;

public class GameBoard {
    private final int[] finish;
    private final int[] start;
    private final int size;

    public GameBoard(int[] finish, int[] start, int size) {
        this.finish = finish;
        this.start = start;
        this.size = size;
    }

    public int[] getFinish() {
        return finish;
    }

    public int[] getStart() {
        return start;
    }

    public int getSize() {
        return size;
    }
}
