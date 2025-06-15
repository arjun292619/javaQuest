package geek4Geeks.algos.arrays;

import java.util.Arrays;

public class ArraySet {
    public static void main(String[] args) {
        String lineseparator = "-".repeat(30);
        System.out.println(lineseparator);
        int[] arr = {20, 10, 20, 4, 100};
        System.out.println("max element in arr " + Arrays.toString(arr) + ": " + maxElement(arr));
    }

    public int[] reverseArray(int[] numbers) {
        return null;
    }

    public int[] reverseSubArray(int[] numbers, int start, int end) {
        return null;
    }

    public static int maxElement(int[] numbers) {
        int max = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] > max) {
                max = numbers[i];
            }
        }
        return max;
    }

    public int secondMaxElement(int[] numbers) {
        return 0;
    }
}
