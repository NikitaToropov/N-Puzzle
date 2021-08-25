package utils;

import dto.Board;

public class BoardUtil {
//    public BoardUtil(Goal goal) {
//    }

    public static void printState(Board board) {
        printState(board.state);
    }

    public static void printState(int[][] board) {
        System.out.println();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.printf("%2d ", board[i][j]);
            }
            System.out.println();
        }
    }

    public int countHScoreForState(int[][] state) {
        int sum = 0;

        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state.length; j++) {
                if (state[i][j] == 0)
                    continue;
//                sum += getManhattanDistance(state, i, j);
            }
        }
        return sum;
    }

//    private int getManhattanDistance(int[][] state, int i, int j) {
//        int iGoal =
//        return 0;
//    }
}
