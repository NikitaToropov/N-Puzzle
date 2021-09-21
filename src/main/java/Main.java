import reading.Reader;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static String INPUT_FILE_PATH = "/Users/nikitatoropov/Desktop/n-puzzle/src/main/resources/example.txt";

    public static void main(String[] args) throws IOException {

        int[][] startMatrix = readFromInput();

////        int[][] startMatrix = Reader.readInput(INPUT_FILE_PATH);
//        Goal goal = GoalGenerator.getGoal(startMatrix.length);
//
////        Heuristic heuristic = new EuclideanHeuristic();
////        Heuristic heuristic = new SimplestHeuristic();
//        Heuristic heuristic = new ManhattanHeuristic();
//        State startState = BoardUtil.getStartState(startMatrix, goal, heuristic);
//
//        BoardUtil.printStartState(startState);
//
//        ResolvingHelper resolvingHelper = new ResolvingHelper(goal, heuristic);
//
//        Report report = startMatrix.length <= 3
//                ? new AStarResolver(goal, startState, resolvingHelper).resolveIt()
//                : new IDAStarResolver(goal, startState, resolvingHelper).resolveIt();
//        report.printResolvingResult();
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
            do {
                System.out.println("Ок, теперь введи параметры пазла");
                System.out.println("(int)[размер пазла] (boolean)[решаемый пазл или нет] (int)[запутанность]");
                String[] params = line.split("\\s");
                try {

                }
            } while ()

        } else {
            while (true) {
                System.out.println("Просто введи абсолютный путь к файлу");
                line = scanner.nextLine().trim();
                try {
                    matrix = Reader.readInput(line);
                    break;
                } catch (IOException e) {
                    System.out.println("Путь неправильный. Попробуй еще раз");
                }
            }
        }

    }
        return null;
}
}