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
        System.out.println(lineseparator);
        n = 3;
        towerOfHanoi(3, 'A', 'C', 'B');
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
