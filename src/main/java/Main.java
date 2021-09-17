import dto.Goal;
import dto.State;
import reading.Reader;
import resolvers.Resolver;
import utils.BoardUtil;
import utils.ResolvingHelper;
import utils.GoalGenerator;

import java.io.IOException;

public class Main {
    private static String INPUT_FILE_PATH = "/Users/nikitatoropov/Desktop/n-puzzle/src/main/resources/example.txt";

    public static void main(String[] args) throws IOException {
        State preParty = Reader.readInput(INPUT_FILE_PATH);
        Goal goal = GoalGenerator.getGoal(preParty.matrix.length);
        BoardUtil.printGoal(goal);
        ResolvingHelper resolvingHelper = new ResolvingHelper(goal);
        State startState = resolvingHelper.getStartState(preParty);
        BoardUtil.printState(startState);
        System.out.println();
        new Resolver(goal, startState, resolvingHelper).resolveIt();
    }
}