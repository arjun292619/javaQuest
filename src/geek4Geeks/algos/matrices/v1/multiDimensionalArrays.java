package geek4Geeks.algos.matrices.v1;

import java.util.Arrays;
import java.util.Random;

public class multiDimensionalArrays {
    public static void main(String[] args) {
        int[][] arr = {{1, 2, 3, 7, 8}, {4, 5, 6}, {9, 10, 11, 12, 13, 14, 15}};
        int[][] multarr1 = new int[3][4];
        int[][] jaggedArr = new int[3][];

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

    }

    private static void printArray(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.printf("%-5d", arr[i][j]);
            }
            System.out.println();
        }
    }
}
