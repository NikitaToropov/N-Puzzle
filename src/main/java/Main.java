import dto.Board;
import dto.Coordinate;
import reading.Reader;
import utils.BoardUtil;
import utils.GoalMapGenerator;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {
    private static String INPUT_FILE_PATH = "/Users/nikita_toropov/Desktop/N-Puzzle/src/main/resources/npuzzle-4-1.txt";

    public static void main(String[] args) throws IOException {
        Board startBoard = new Reader().readInput(INPUT_FILE_PATH);
        BoardUtil.printState(startBoard);

        Map<Integer, Coordinate> goal = GoalMapGenerator.getGoal(startBoard.size);
        System.out.println();
        goal.forEach((k, v) -> System.out.println(k + ":" + "i=" + v.i + " j=" + v.j));

        BoardUtil util = new BoardUtil(goal);

        List<Board> expandedStart = util.expandTheState(startBoard);
        expandedStart.forEach(BoardUtil::printState);
    }
}