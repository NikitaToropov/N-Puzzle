import dto.Board;
import reading.Reader;
import resolvers.astar.AStarResolver;
import utils.BoardUtil;

import java.io.IOException;

public class Main {
    private static String INPUT_FILE_PATH = "/Users/nikita_toropov/Desktop/N-Puzzle/src/main/resources/npuzzle-4-1.txt";

    public static void main(String[] args) throws IOException {
        Board gameBoard = new Reader().readInput(INPUT_FILE_PATH);
        BoardUtil.printState(gameBoard);
        experimentIt(gameBoard.state);
//        new AStarResolver().resolveIt(gameBoard);
    }

    private static void experimentIt(int[][] start) {
        int[][] goal = new int[start.length][start.length];
        int counter = 1;
        int right = start.length - 1;
        int left = 0;
        int up = 0;
        int down = start.length - 1;
        int i = 0;
        int j = 0;
//        while () {
            for (; j <= right; j++) {
                goal[i][j] = counter++;
            }
            j = right--;
            for (; i <= down; i++) {
                goal[i][j] = counter++;
            }
            i = down--;
            j--;
            for (; j >= left; j--) {
                goal[i][j] = counter++;

            }
            j = left++;
            for (; i >= up; i--) {
                goal[i][j] = counter++;
            }
            i = up++;
//        }

        BoardUtil.printState(goal);
    }
}

