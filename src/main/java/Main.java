import dto.Goal;
import dto.State;
import reading.Reader;
import resolvers.Resolver;
import utils.BoardUtil;
import utils.ResolvingHelper;
import utils.GoalGenerator;

import java.io.IOException;

public class Main {
        private static String INPUT_FILE_PATH = "src/main/resources/npuzzle-3-1.txt";
//    private static String INPUT_FILE_PATH = "src/main/resources/npuzzle-4-1.txt";

    public static void main(String[] args) throws IOException {
        State startState = new Reader().readInput(INPUT_FILE_PATH);

        BoardUtil.printState(startState);
        if (!BoardUtil.isSolvable(startState.matrix)) {
            System.out.println("THE PUZZLE NOT SOLVABLE");
            return;
        }

        Goal goal = GoalGenerator.getGoal(startState.matrix.length);
        System.out.println();

        ResolvingHelper util = new ResolvingHelper(goal);
        BoardUtil.printGoal(goal);

        new Resolver(goal, startState, util).resolveIt();
    }
}