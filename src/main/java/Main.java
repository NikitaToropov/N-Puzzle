import dto.Goal;
import dto.State;
import reading.Reader;
import resolvers.Resolver;
import utils.BoardUtil;
import utils.ResolvingHelper;
import utils.GoalGenerator;

import java.io.IOException;

public class Main {
//        private static String INPUT_FILE_PATH = "src/main/resources/npuzzle-3-1.txt";
//    private static String INPUT_FILE_PATH = "src/main/resources/npuzzle-4-1.txt";
    private static String INPUT_FILE_PATH = "/Users/nikitatoropov/Desktop/n-puzzle/src/test/resources/maps/invalid/puzzle_gen_unsolved_4x4-15-01.txt";

    public static void main(String[] args) throws IOException {
        State startState = Reader.readInput(INPUT_FILE_PATH);

        BoardUtil.printState(startState);

        Goal goal = GoalGenerator.getGoal(startState.matrix.length);
        System.out.println();

        ResolvingHelper util = new ResolvingHelper(goal);
        BoardUtil.printGoal(goal);

        new Resolver(goal, startState, util).resolveIt();
    }
}