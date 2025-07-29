package cases.designs.atm.v1;

import java.util.InputMismatchException;
import java.util.Scanner;

class KeyPad {
    private static Scanner scanner = new Scanner(System.in);
    private int userInput;

    public void getInput() {
        try {
            System.out.println("Type the PIN: ");
            userInput = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        }
    }
}
