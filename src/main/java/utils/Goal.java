package utils;

public class Goal {

    public Goal(int[][] start) {
//        00 01 02 12 22 21 20 10 11
        int[][] goal = new int[start.length][start.length];
        int counter = 1;

        for (int i = 0, iLimit = start.length; i < iLimit; i++) {
            for (int j = 0, jLimit = start.length; j < jLimit; j++) {
                goal[i][j] = counter++;
            }
        }
    }
}
