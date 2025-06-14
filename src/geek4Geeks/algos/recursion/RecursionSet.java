package geek4Geeks.algos.recursion;

import java.util.ArrayList;
import java.util.List;

public class RecursionSet {
    public static void main(String[] args) {
        String lineseparator = "-".repeat(30);
        System.out.println(lineseparator);
//        Print n to 1 using recursion
        int n = 5;
        printN2One(n);
        System.out.println();
        System.out.println(lineseparator);
        printOne2N(n);
        System.out.println();
        System.out.println(lineseparator);
        n = 3;
        towerOfHanoi(3, 'A', 'C', 'B');
        System.out.println(lineseparator);
        List<String> subset = new ArrayList<>();
        subsetsRecursive("ABC", 0, "", subset);
        System.out.println(subset);
    }

    public static void printN2One(int n) {
        if (n < 1) return;
        System.out.print(n + " ");
        printN2One(n - 1);
    }

    public static void printOne2N(int n) {
        if (n < 1) return;
        printOne2N(n - 1);
        System.out.print(n + " ");
    }

    public static void subsetsRecursive(String s, int index, String curr, List<String> subsets) {
        if (index == s.length()) {
            subsets.add(curr);
            return;
        }
        subsetsRecursive(s, index + 1, curr, subsets);
        subsetsRecursive(s, index + 1, curr + s.charAt(index), subsets);
    }

    public static void towerOfHanoi(int n, char start, char end, char aux) {
        if (n == 0) return;
        towerOfHanoi(n - 1, start, aux, end);
        System.out.println("moving disk" + n + " from " + start + " to " + end);
        towerOfHanoi(n - 1, aux, end, start);
    }

    public static int joshephus(int n, int k) {
        return 0;
    }
}
