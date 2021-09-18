package utils;

import dto.Coordinate;
import dto.State;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Утилитный класс для генерации рандомных карты.
 */
public class PuzzleGenerator {
    private final int size;
    private final boolean isSolvable;
    private final int iterations;
    private final int[][] matrix;
    private Coordinate emptyCell;
    private final HashSet<String> closed;

    public PuzzleGenerator(int size, boolean isSolvable, int iterations) {
        this.size = size;
        this.isSolvable = isSolvable;
        this.iterations = iterations;
        this.matrix = GoalGenerator.getSpiralMatrix(size);
        this.closed = new HashSet<>();
        this.emptyCell = BoardUtil.getEmptyCell(matrix);
    }

    public int[][] getNewPuzzle() {
        if (!isSolvable) {
            swapRandomCells(matrix);
        }
        iterateCellsNTimes(matrix, iterations);
        return matrix;
    }

    /**
     * Метод перестановки N раз.
     *
     * @param matrix     Матрица состояния игрового поля.
     * @param iterations Количество перемещений.
     */
    private void iterateCellsNTimes(int[][] matrix, int iterations) {
        for (int i = 0; i < iterations; i++) {
            closed.add(Arrays.deepToString(matrix));
            Coordinate secondCell = getRandomCellAroundTheGivenOne();
            if (secondCell == null) {
                return;
            }
            swapCells(emptyCell.i, emptyCell.j, secondCell.i, secondCell.j);
            emptyCell = secondCell;
        }
    }

    /**
     * Метод возвращает координаты случайную ячейку вокруг заданной.
     * Ячейки по-диагонали не считаются.
     */
    private Coordinate getRandomCellAroundTheGivenOne() {
        List<Coordinate> newStates = new ArrayList<>();
        int i, j;
        if (BoardUtil.isCorrectCoordinates(i = emptyCell.i - 1, j = emptyCell.j, size)) {
            addIfNotContain(i, j, newStates);
        }
        if (BoardUtil.isCorrectCoordinates(i = emptyCell.i + 1, j = emptyCell.j, size)) {
            addIfNotContain(i, j, newStates);
        }
        if (BoardUtil.isCorrectCoordinates(i = emptyCell.i, j = emptyCell.j - 1, size)) {
            addIfNotContain(i, j, newStates);
        }
        if (BoardUtil.isCorrectCoordinates(i = emptyCell.i, j = emptyCell.j + 1, size)) {
            addIfNotContain(i, j, newStates);
        }
//        return newStates.stream()
//                .findAny()
//                .orElse(null);
        return newStates.get(getRandomInRangeMinMaxInclusive(0, newStates.size() - 1));
    }

    /**
     * Добавляет в коллекцию координаты ячейки которая подходит для передвиженя.
     */
    private void addIfNotContain(int i, int j, List<Coordinate> newStates) {
        swapCells(i, j, emptyCell.i, emptyCell.j);
        if (!closed.contains(Arrays.deepToString(matrix))) {
            newStates.add(new Coordinate(i, j, matrix[i][j]));
        }
        swapCells(i, j, emptyCell.i, emptyCell.j);
    }

    private void swapRandomCells(int[][] matrix) {
        int firstI, firstJ, secondI, secondJ, size = matrix.length;
        do {
            firstI = getRandomInRangeMinMaxInclusive(0, size - 1);
            firstJ = getRandomInRangeMinMaxInclusive(0, size - 1);
        } while (matrix[firstI][firstJ] == State.EMPTY_CELL_VALUE);

        do {
            int direction = getRandomInRangeMinMaxInclusive(1, 4);
            if (direction == 1) {
                secondI = firstI + 1;
                secondJ = firstJ;
            } else if (direction == 2) {
                secondI = firstI;
                secondJ = firstJ + 1;
            } else if (direction == 3) {
                secondI = firstI - 1;
                secondJ = firstJ;
            } else {
                secondI = firstI;
                secondJ = firstJ - 1;
            }
        } while (!BoardUtil.isCorrectCoordinates(secondI, secondJ, size)
                || matrix[secondI][secondJ] == State.EMPTY_CELL_VALUE);
        BoardUtil.swapCellsInMatrix(matrix, firstI, firstJ, secondI, secondJ);
    }

    /**
     * Метод генерации случайных чисел в диапазоне. Включая min и max.
     */
    public int getRandomInRangeMinMaxInclusive(int min, int max) {
        return (int) Math.floor(Math.random() * (max - min + 1) + min);
    }

    private void swapCells(int i1, int j1, int i2, int j2) {
        int tmp = matrix[i1][j1];
        matrix[i1][j1] = matrix[i2][j2];
        matrix[i2][j2] = tmp;
    }
}
