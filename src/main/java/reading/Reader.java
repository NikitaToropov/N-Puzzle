package reading;

import dto.State;
import exceptions.WrongFormatException;
import utils.AbstractBoardUtil;
import utils.BoardUtil;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Reader {


    public static final String COMMENT_CHAR = "#";
    public static final String COMMENTED_LINE_REGEX = "^\\s*" + COMMENT_CHAR;
    public static final Pattern COMMENTED_LINE_PATTERN = Pattern.compile(COMMENTED_LINE_REGEX);

    public static final String CLEAR_LINE_REGEX = "(\\s*\\d\\s*)+"; // without comments
    public static final Pattern CLEAR_LINE_PATTERN = Pattern.compile(CLEAR_LINE_REGEX);
    public static final int MAXIMUM_VALUE = 999999999;


    public State readInput(String path) throws IOException {
        List<List<Integer>> lines = readFileLineByLine(path);
        return crateGameBoard(lines);
    }

    private State crateGameBoard(List<List<Integer>> lines) {
        int size = lines.get(0).get(0);
        int[][] start = getStart(lines, size);
        return new State(start, 0, MAXIMUM_VALUE, AbstractBoardUtil.getEmptyCell(start), null);
    }

    private int[] getFinish(int len) {
        int[] content = new int[len];
        for (int i = 0; i < len - 1; i++) {
            content[i] = i + 1;
        }
        content[len - 1] = 0;
        return content;
    }

    // TODO Перед этим методом надо выполнить валидацию.
    // Убедиться что формат ввода правильный, иначе возможны исключения.
    private int[][] getStart(List<List<Integer>> lines, int size) {
        int[][] content = new int[size][size];
        for (int i = 1; i <= size; i++) {
            for (int j = 0; j < size; j++) {
                content[i - 1][j] = lines.get(i).get(j);
            }
        }
        return content;
    }

    private List<List<Integer>> readFileLineByLine(String path) throws IOException {
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
        return lines;
    }

    private List<Integer> parseCleanLine(String line) {
        String[] numbers = line.split("\\s+");
        return Arrays.stream(numbers).map(Integer::parseInt).collect(Collectors.toList());
    }
}
