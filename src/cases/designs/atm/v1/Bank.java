package cases.designs.atm.v1;

import java.util.Random;

public class Bank {
    private String name;
    private int bankCode;

    public String getName() {
        return name;
    }

    public int getBankCode() {
        return bankCode;
    }

    public boolean addATM() {
        System.out.printf("ATM is added to the the %s (%s)", name, bankCode);
        return new Random().nextBoolean();
    }
}
