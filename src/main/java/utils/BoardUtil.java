package utils;

import dto.State;

public class BoardUtil {

    private BoardUtil() {
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
            for (int j = 0; j < n; j++) {
                arr[i * n + j] = matrix[i][j];
            }
        }
        return arr;
    }


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
}
