package map_reading;

import java.io.*;
import java.lang.reflect.Array;
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


    public int[][] readInput(String path) throws IOException {
        List<List<Integer>> lines = readFileLineByLine(path);
        for (List<Integer> line:
             lines) {
            for (Integer i :
                    line) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
        return null;
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


