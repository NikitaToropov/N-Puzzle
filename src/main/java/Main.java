import dto.Goal;
import dto.State;
import reading.Reader;
import resolvers.SideResolver;
import utils.BoardUtil;
import utils.GoalMapGenerator;
import utils.SideBoardUtil;

import java.io.IOException;

public class Main {
//        private static String INPUT_FILE_PATH = "src/main/resources/npuzzle-3-1.txt";
    private static String INPUT_FILE_PATH = "src/main/resources/npuzzle-4-1.txt";

    public static void main(String[] args) throws IOException {
        State startState = new Reader().readInput(INPUT_FILE_PATH);
        BoardUtil.printState(startState);

        Goal goal = GoalMapGenerator.getGoal(startState.matrix.length);
        System.out.println();

//        BoardUtil util = new BoardUtil(goal);
//        util.printGoal();

//        new Resolver(goal, startState, util).resolveIt();

        SideBoardUtil util = new SideBoardUtil(goal);
        util.printGoal();
        new SideResolver(goal,startState, util).resolveIt();
    }
}