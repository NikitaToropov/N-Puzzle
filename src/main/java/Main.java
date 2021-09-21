import dto.Goal;
import dto.Report;
import dto.State;
import exceptions.UnsolvablePuzzleException;
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
        if (!BoardUtil.isSolvable(startMatrix)) {
            throw new UnsolvablePuzzleException();
        }

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
            System.out.println("Ок, теперь введи параметры пазла в одну линию пжлст");
            while (true) {
                System.out.println("размер пазла     - не больше 15");
                System.out.println("решаемость пазла - true/false");
                System.out.println("запутанность     - не больше 100 000");
                System.out.println("[размер] [решаемость] [запутанность]");
                line = scanner.nextLine();
                try {
                    String[] params = line.split(" ");
                    int size = Integer.parseInt(params[0]);
                    boolean isSolvable = Boolean.parseBoolean(params[1]);
                    int iterations = Integer.parseInt((params[2]));
                    if (size < 2 || size > 15) {
                        System.out.println("Ты ввел неправильный размер");
                    } else if (iterations < 0 || iterations > 100_000) {
                        System.out.println("Ты ввел неправильный размер");
                    } else {
                        return new PuzzleGenerator(size, isSolvable, iterations).getNewPuzzle();
                    }
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