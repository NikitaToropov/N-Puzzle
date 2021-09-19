package utils;

import dto.Coordinate;
import dto.Goal;
import dto.State;
import heuristifcs.Heuristic;

public class
BoardUtil {

    private BoardUtil() {
    }

    /**
     * Метод ищет координаты нулевой ячейки.
     */
    public static Coordinate getEmptyCell(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == State.EMPTY_CELL_VALUE) {
                    return new Coordinate(i, j, State.EMPTY_CELL_VALUE);
                }
            }
        }
        return null;
    }

    /**
     * Метод проверки пазла на решаемость.
     */
    public static boolean isSolvable(int[][] matrix) {
        int n = matrix.length;
        int inversionCount = countInversions(matrix);

        if (n % 2 == 1) {
            return (inversionCount % 2 == 1);
        } else {
            int pos = findXPosition(matrix);
            if (pos % 2 == 0) {
                if (n != 6) {
                    return (inversionCount % 2 == 1);
                } else {
                    return (inversionCount % 2 == 0);
                }
            } else {
                if (n != 6) {
                    return (inversionCount % 2 == 0);
                } else {
                    return (inversionCount % 2 == 1);
                }
            }
        }
    }

    /**
     * Метод подсчета инверсий.
     */
    private static int countInversions(int[][] matrix) {
        int inversionCount = 0;
        int[] arr = convertMatrixToArray(matrix);

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] > 0 && (arr[i] > arr[j])) {
                    inversionCount++;
                }
            }
        }
        return inversionCount;
    }

    /**
     * Перевод матрицы в одномерный массив.
     */
    private static int[] convertMatrixToArray(int[][] matrix) {
        int n = matrix.length;
        int[] arr = new int[n * n];

        for (int i = 0; i < n; i++) {
            System.arraycopy(matrix[i], 0, arr, i * n, n);
        }
        return arr;
    }


    /**
     * Метод для поиска пустой ячейки считая снизу.
     */
    private static int findXPosition(int[][] puzzle) {
        int n = puzzle.length;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (puzzle[i][j] == State.EMPTY_CELL_VALUE)
                    return n - i;
            }
        }
        return n;
    }

    /**
     * Конструктор стартового состояния пазла.
     *
     * @param preParty Предзаполненное стартовое состояние.
     * @return startState
     */
    public static State getStartState(int[][] preParty, Goal goal, Heuristic heuristic) {
        return new State(
                preParty,
                0,
                heuristic.countHScore(preParty, goal),
                BoardUtil.getEmptyCell(preParty),
                null
        );
    }

    /**
     * Метод печати целевого состояния и содержимого его goalMap.
     */
    public static void printGoal(Goal goal) {
        System.out.println("+++++++++++++++++++ PRINT GOAL +++++++++++++++++++");
        printMatrix(goal.matrix);
        System.out.println();
        goal.goalMap.forEach((k, v) -> System.out.println(k + ":" + " i=" + v.i + " j=" + v.j));
        System.out.println();
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++");
    }

    /**
     * Метод печати состояния.
     */
    public static void printState(State state) {
        System.out.println();
        System.out.println("g = " + state.g + ", f = " + state.f);
        printMatrix(state.matrix);
    }

    /**
     * Метод печати матрицы состояния.
     */
    public static void printMatrix(int[][] matrix) {
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                System.out.printf("%2d ", anInt);
            }
            System.out.println();
        }
    }

    /**
     * Метод проверяет, не выходит ли координаты за рамки карты.
     */
    public static boolean isCorrectCoordinates(int i, int j, int size) {
        return i >= 0 && i < size && j >= 0 && j < size;
    }

    /**
     * Метод свопа ячеек.
     */
    public static void swapCellsInMatrix(int[][] matrix, int firstI, int firstJ, int secondI, int secondJ) {
        int tmp = matrix[firstI][firstJ];
        matrix[firstI][firstJ] = matrix[secondI][secondJ];
        matrix[secondI][secondJ] = tmp;
    }
}
