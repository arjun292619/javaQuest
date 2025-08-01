package geek4Geeks.algos.matrices.v1;

import java.util.Arrays;
import java.util.Random;

public class multiDimensionalArrays {
    public static void main(String[] args) {
        String ls = "-".repeat(50);
        int[][] arr = {{1, 2, 3, 7, 8}, {4, 5, 6}, {9, 10, 11, 12, 13, 14, 15}};
        int[][] rowArr = {{1, 2, 3, 4}};
        int[][] colArr = {{1}, {2}, {3}, {4}};
        int[][] multarr1 = new int[3][4];
        int[][] jaggedArr = new int[3][];
        int[][] sqArr = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};

        for (int i = 0; i < multarr1.length; i++) {
            for (int j = 0; j < multarr1[i].length; j++) {
                multarr1[i][j] = new Random().nextInt(0, 26);
            }
        }

        for (int i = 0; i < jaggedArr.length; i++) {
            jaggedArr[i] = new int[i + 3];
            for (int j = 0; j < jaggedArr[i].length; j++) {
                Arrays.fill(jaggedArr[i], new Random().nextInt(0, 26));
            }
        }

//        for (int i = 0; i < arr.length; i++) {
//            for (int j = 0; j < arr[i].length; j++) {
//                System.out.printf("%-5d", arr[i][j]);
//            }
//        }
        printArray(arr);
        System.out.println();
        printArray(multarr1);
        System.out.println("Print the jagged Array");
        printArray(jaggedArr);

//        print snake case pattern
        System.out.println("Print Boundary Traversal");
        printSnakePattern(sqArr);
        printSnakePattern(arr);
        System.out.println(ls);

//        print boundary traversal

        printArray(sqArr);
        System.out.println("Print Boundary Traversal");
        printBoundary(sqArr);
        System.out.println();
        printBoundary(rowArr);
        System.out.println();
        printBoundary(colArr);

    }


    private static void printArray(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.printf("%-5d", arr[i][j]);
            }
            System.out.println();
        }
    }

    private static void printSnakePattern(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < arr[i].length; j++) {
                    System.out.printf("%-5d", arr[i][j]);
                }
            } else {
                for (int j = arr[i].length - 1; j >= 0; j--) {
                    System.out.printf("%-5d", arr[i][j]);
                }
            }
            System.out.println();
        }
    }

    private static void printBoundary(int[][] arr) {
        if (arr.length == 1) {
            for (int i = 0; i < arr[0].length; i++) {
                System.out.printf("%-5d", arr[0][i]);
            }
        } else if (arr[0].length == 1) {
            for (int i = 0; i < arr.length; i++) {
                System.out.printf("%-5d", arr[i][0]);
            }
        } else {
            for (int i = 0; i < arr.length; i++) {
                System.out.printf("%-5d", arr[0][i]);
            }

            for (int i = 1; i < arr[0].length; i++) {
                System.out.printf("%-5d", arr[i][arr.length - 1]);
            }

            for (int i = arr.length - 2; i >= 0; i--) {
                System.out.printf("%-5d", arr[arr.length - 1][i]);
            }
            for (int i = arr.length - 2; i >= 1; i--) {
                System.out.printf("%-5d", arr[i][0]);
            }
        }

    }
}
