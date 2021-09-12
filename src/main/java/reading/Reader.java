package reading;

import dto.State;
import exceptions.UnsolvablePuzzleException;
import exceptions.WrongCellException;
import exceptions.WrongFormatException;
import utils.BoardUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Reader {

    public static final String COMMENT_CHAR = "#";
    public static final String COMMENTED_LINE_REGEX = "^\\s*" + COMMENT_CHAR;
    public static final Pattern COMMENTED_LINE_PATTERN = Pattern.compile(COMMENTED_LINE_REGEX);

    public static final String CLEAR_LINE_REGEX = "(\\s*\\d\\s*)+"; // without comments
    public static final Pattern CLEAR_LINE_PATTERN = Pattern.compile(CLEAR_LINE_REGEX);
    public static final int MAXIMUM_VALUE = Integer.MAX_VALUE;


    public static State readInput(String path) throws IOException {
        List<List<Integer>> lines = readFileLineByLine(path);
        return crateGameBoard(lines);
    }

    private static State crateGameBoard(List<List<Integer>> lines) {
        int size = lines.remove(0).get(0);
        int[][] start = getStartMatrix(lines, size);
        checkCellValues(start);
        if (!BoardUtil.isSolvable(start)) {
            throw new UnsolvablePuzzleException();
        }
        return new State(start, 0, MAXIMUM_VALUE, BoardUtil.getEmptyCell(start), null);
    }

    /**
     * Проверка на корректность значений ячеек.
     *
     * @param matrix Поле пазла.
     */
    private static void checkCellValues(int[][] matrix) {
        int max_value = matrix.length * matrix.length;
        boolean[] isBeenMet = new boolean[max_value];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                int cellValue = matrix[i][j];
                if (cellValue < 0 || cellValue >= max_value || isBeenMet[cellValue]) {
                    throw new WrongCellException();
                } else {
                    isBeenMet[cellValue] = true;
                }
            }
        }
    }


    /**
     * Переводит список списков в матрицу интов.
     *
     * @param lines Список списков интов, первая лини содержит только размер стороны поля.
     * @param size  Размер стороны поля.
     * @return Матрица текущего состояния поля.
     */
    private static int[][] getStartMatrix(List<List<Integer>> lines, int size) {
        checkLinesLen(lines, size);

        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = lines.get(i).get(j);
            }
        }
        return matrix;
    }

    private static void checkLinesLen(List<List<Integer>> lines, int size) {
        lines.forEach(l -> {
            if (l.size() != size) throw new WrongFormatException();
        });
    }

    /**
     * Метод читает входные параметры по линиям.
     * Если находит посторинние комментарии
     * или комментарии "обрезающие" карту выбрасывает исключение форматирования входного файла.
     */
    private static List<List<Integer>> readFileLineByLine(String path) throws IOException {
        List<List<Integer>> lines = new ArrayList<>();
        File file = new File(path);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while ((line = br.readLine()) != null) {
            if (COMMENTED_LINE_PATTERN.matcher(line).find())
                continue;
            Matcher matcher = CLEAR_LINE_PATTERN.matcher(line);
            if (matcher.find())
                lines.add(parseCleanLine(matcher.group()));
            else
                throw new WrongFormatException();
        }
        if (lines.isEmpty()) {
            throw new WrongFormatException();
        }
        return lines;
    }

    /**
     * Отделяет "чистый ввод" от коментариев.
     */
    private static List<Integer> parseCleanLine(String line) {
        String[] numbers = line.split("\\s+");
        return Arrays.stream(numbers).map(Integer::parseInt).collect(Collectors.toList());
    }
}
