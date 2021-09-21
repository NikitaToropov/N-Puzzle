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
import utils.PuzzleGenerator;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        int[][] startMatrix = readFromInput();

//        int[][] startMatrix = Reader.readInput(INPUT_FILE_PATH);
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

    private static int[][] readFromInput() {
        Scanner scanner = new Scanner(System.in);
        int[][] matrix;
        String line = "";
        do {
            System.out.println("Выбери какой пазл будем решать и введи:");
            System.out.println(" - 'p' если хочешь решить пазл из файла");
            System.out.println(" - 'g' если хочешь сгенерировать пазл");
            line = scanner.nextLine().trim();
        } while (!line.equals("g") && !line.equals("p"));

        if (line.equals("g")) {
            while (true) {
                System.out.println("Ок, теперь введи параметры пазла в одну линию пжлст");
                System.out.println("[размер пазла] [решаемый пазл или нет(boolean)] [запутанность]");
                line = scanner.nextLine();
                try {
                    String[] params = line.split(" ");
                    int size = Integer.parseInt(params[0]);
                    boolean isSolvable = Boolean.parseBoolean(params[1]);
                    int iterations = Integer.parseInt((params[2]));
                    return new PuzzleGenerator(size, isSolvable, iterations).getNewPuzzle();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Ты ввел что-то не правильно. Попробуй снова!");
                }
            }

        } else {
            while (true) {
                System.out.println("Просто введи абсолютный путь к файлу");
                line = scanner.nextLine().trim();
                try {
                    return Reader.readInput(line);
                } catch (IOException e) {
                    System.out.println("Путь неправильный. Попробуй еще раз");
                }
            }
        }
    }
}