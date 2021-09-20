import dto.Goal;
import dto.State;
import heuristifcs.EuclideanHeuristic;
import heuristifcs.Heuristic;
import heuristifcs.ManhattanHeuristic;
import heuristifcs.SimplestHeuristic;
import reading.Reader;
import resolvers.IDAStarResolver;
import resolvers.Resolver;
import utils.BoardUtil;
import resolvers.ResolvingHelper;
import utils.GoalGenerator;

import java.io.IOException;

public class Main {
    private static String INPUT_FILE_PATH = "/Users/nikitatoropov/Desktop/n-puzzle/src/main/resources/example.txt";

    public static void main(String[] args) throws IOException {
        int[][] startMatrix = Reader.readInput(INPUT_FILE_PATH);
        Goal goal = GoalGenerator.getGoal(startMatrix.length);
        BoardUtil.printGoal(goal);
//        Heuristic heuristic = new ManhattanHeuristic();
//        Heuristic heuristic = new EuclideanHeuristic();
        Heuristic heuristic = new SimplestHeuristic();
        ResolvingHelper resolvingHelper = new ResolvingHelper(goal, heuristic);
        State startState = BoardUtil.getStartState(startMatrix, goal, heuristic);
        BoardUtil.printState(startState);
        System.out.println();
        new Resolver(goal, startState, resolvingHelper).resolveIt();
//        new IDAStarResolver(goal, startState, resolvingHelper).resolveIt();
    }
}