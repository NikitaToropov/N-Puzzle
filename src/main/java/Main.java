import dto.Goal;
import dto.State;
import reading.Reader;
import resolvers.Resolver;
import utils.ResolvingUtil;
import utils.GoalGenerator;

import java.io.IOException;

public class Main {
        private static String INPUT_FILE_PATH = "src/main/resources/npuzzle-3-1.txt";
//    private static String INPUT_FILE_PATH = "src/main/resources/npuzzle-4-1.txt";

    public static void main(String[] args) throws IOException {
        State startState = new Reader().readInput(INPUT_FILE_PATH);
        ResolvingUtil.printState(startState);

        Goal goal = GoalGenerator.getGoal(startState.matrix.length);
        System.out.println();

        ResolvingUtil util = new ResolvingUtil(goal);
        util.printGoal();

        new Resolver(goal, startState, util).resolveIt();
    }
}