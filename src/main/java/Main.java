import dto.Coordinate;
import dto.State;
import reading.Reader;
import resolvers.Resolver;
import utils.BoardUtil;
import utils.GoalMapGenerator;

import java.io.IOException;
import java.util.Map;

public class Main {
    private static String INPUT_FILE_PATH = "src/main/resources/npuzzle-4-1.txt";

    public static void main(String[] args) throws IOException {
        State startState = new Reader().readInput(INPUT_FILE_PATH);
        BoardUtil.printState(startState);

        Map<Integer, Coordinate> goal = GoalMapGenerator.getGoal(startState.matrix.length);
        System.out.println();
        goal.forEach((k, v) -> System.out.println(k + ":" + "i=" + v.i + " j=" + v.j));

        BoardUtil util = new BoardUtil(goal);

//        PriorityQueue<State> expandedStart = util.expandTheState(startState);
//        expandedStart.forEach(BoardUtil::printState);
        new Resolver(goal, startState, util).resolveIt();


    }
}