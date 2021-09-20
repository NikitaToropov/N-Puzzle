import dto.Goal;
import dto.Report;
import dto.State;
import heuristifcs.Heuristic;
import heuristifcs.ManhattanHeuristic;
import reading.Reader;
import resolvers.AStarResolver;
import resolvers.IDAStarResolver;
import resolvers.ResolvingHelper;
import utils.BoardUtil;
import utils.GoalGenerator;

import java.io.IOException;

public class Main {
    private static String INPUT_FILE_PATH = "/Users/nikitatoropov/Desktop/n-puzzle/src/main/resources/example.txt";

    public static void main(String[] args) throws IOException {
        int[][] startMatrix = Reader.readInput(INPUT_FILE_PATH);
        Goal goal = GoalGenerator.getGoal(startMatrix.length);

//        Heuristic heuristic = new EuclideanHeuristic();
//        Heuristic heuristic = new SimplestHeuristic();
        Heuristic heuristic = new ManhattanHeuristic();
        State startState = BoardUtil.getStartState(startMatrix, goal, heuristic);

        BoardUtil.printStartState(startState);

        ResolvingHelper resolvingHelper = new ResolvingHelper(goal, heuristic);

        Report report = startMatrix.length <= 3
                ? new AStarResolver(goal, startState, resolvingHelper).resolveIt()
                : new IDAStarResolver(goal, startState, resolvingHelper).resolveIt();
        report.printResolvingResult();
    }
}