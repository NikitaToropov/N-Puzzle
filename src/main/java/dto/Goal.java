package dto;

import java.util.Map;

public class Goal {
    public final Map<Integer, Coordinate> goalMap;
    public final int[][] matrix;

    public Goal(Map<Integer, Coordinate> goalMap, int[][] matrix) {
        this.goalMap = goalMap;
        this.matrix = matrix;
    }
}
