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
        int[][] sortedMat = {{10, 20, 30, 40}, {15, 25, 35, 45}, {27, 29, 37, 48}, {32, 33, 39, 50}};

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
        System.out.println();
        System.out.println(ls);

        System.out.println("Transpose of square arrays");
        transposeMatrix(sqArr);
        printArray(sqArr);
        System.out.println("Rotate by 90 degrees");
//        rotateBy90(sqArr);
        rotateBy90(sqArr, "naive");
        printArray(sqArr);

        System.out.println(ls);
        System.out.println("Print the spiral Traversal Path");
        printArray(sqArr);
        printSpiral(sqArr);

        System.out.println();
        System.out.println(ls);
        System.out.println("Search and find");
        printArray(sortedMat);
        System.out.println(isPresent(sortedMat, 29));
        searchInSorted(sortedMat, 29);
        System.out.println();
        System.out.println(ls);
    }

    private static void swap(int x, int y) {
        int temp = x;
        x = y;
        y = temp;
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

    private static void transposeMatrix(int[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = i + 1; j < mat[i].length; j++) {
                int temp = mat[i][j];
                mat[i][j] = mat[j][i];
                mat[j][i] = temp;
            }
        }
    }

    private static void rotateBy90(int[][] mat) {
        transposeMatrix(mat);
        for (int i = 0; i < mat.length; i++) {
            int low = 0, high = mat.length - 1;
            while (low < high) {
                int temp = mat[low][i];
                mat[low][i] = mat[high][i];
                mat[high][i] = temp;
                low++;
                high--;
            }
        }
    }

    private static void rotateBy90(int[][] mat, String type) {
        int[][] temp = new int[mat.length][mat.length];
        for (int r = 0; r < mat.length; r++) {
            for (int c = 0; c < mat[r].length; c++) {
                temp[mat.length - 1 - c][r] = mat[r][c];
            }
        }

        for (int r = 0; r < mat.length; r++) {
            for (int c = 0; c < mat[r].length; c++) {
                mat[r][c] = temp[r][c];
            }
        }
    }

    private static void printSpiral(int[][] mat) {
        int top = 0, right = mat.length - 1, bottom = mat.length - 1, left = 0;
        while (top <= bottom && left <= right) {
            for (int i = left; i <= right; i++) {
                System.out.printf("%-5d", mat[top][i]);
            }
            top++;
            for (int i = top; i <= bottom; i++) {
                System.out.printf("%-5d", mat[i][right]);
            }
            right--;
            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    System.out.printf("%-5d", mat[bottom][i]);
                }
                bottom--;
            }
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    System.out.printf("%-5d", mat[i][left]);
                }
                left++;
            }
        }
    }

    private static boolean isPresent(int[][] mat, int x) {
        boolean flag = false;
        for (int r = 0; r < mat.length; r++) {
            for (int c = 0; c < mat[r].length; c++) {
                if (mat[r][c] == x) {
                    flag = true;
                    return flag;
                }
            }
        }
        return flag;
    }

    private static void searchInSorted(int[][] mat, int x) {
        int r = 0, c = mat[0].length - 1;
        while (r < mat.length && c >= 0) {
            if (mat[r][c] == x) {
                System.out.printf("%d is present at location [%d,%d]", x, r, c);
                return;
            } else if (x > mat[r][c]) {
                r++;
            } else {
                c--;
            }
        }
        System.out.printf("%d is not present in the matrix", x);
    }
}
