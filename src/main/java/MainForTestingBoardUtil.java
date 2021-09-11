import utils.BoardUtil;

public class MainForTestingBoardUtil {
    public static void main(String[] args) {

//        int[][] puzzle =
//                {
//                        {1, 2},
//                        {0, 3},
//                };
        int[][] puzzle = // реальная карта из resources
                {
                        {3, 2, 6},
                        {1, 4, 0},
                        {8, 7, 5},
                };
//        int[][] puzzle =
//        {
//            {12, 1, 10, 2},
//            {7, 11, 4, 14},
//            {5, 0, 9, 15}, // Value 0 is used for empty space
//            {8, 13, 6, 3},
//        } ;
//       System.out.println("TestS");
// /*
//        int[][] puzzle = {{1, 2, 3},{4, 0, 5},{8, 6, 7}};
//       int[][] puzzle = {
//               {1, 2, 3},
//               {8, 0, 4},
//               {7, 6, 5}
//       };
// int[][] puzzle = {
// {1, 2, 3, 4},
// {12, 13, 14, 5},
// {11, 0, 15, 6},
// {10, 9, 8, 7},
// };
//
// int[][] puzzle = {
// { 1,  2,  3,  4,  5},
// {16, 17, 18, 19,  6},
// {15, 24, 0, 20,  7},
// {14, 23, 22, 21,  8},
// {13, 12, 11, 10,  9},
// };
//        int[][] puzzle = {
//                {1, 2, 3, 4, 5, 6},
//                {20, 21, 22, 23, 24, 7},
//                {19, 32, 33, 34, 25, 8},
//                {18, 31, 0, 35, 26, 9},
//                {17, 30, 29, 28, 27, 10},
//                {16, 15, 14, 13, 12, 11},
//        };
        if (BoardUtil.isSolvable(puzzle)) {
            System.out.println("Solvable");
        } else {
            System.out.println("Not Solvable");
        }
    }
}
