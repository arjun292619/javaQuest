package geek4Geeks.algos.recursion;

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
}
