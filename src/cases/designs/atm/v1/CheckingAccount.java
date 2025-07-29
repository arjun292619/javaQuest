package cases.designs.atm.v1;

public class CheckingAccount extends Account {
    private long debitCardNumber;

    public String getDebitCardNumber() {
        return String.valueOf(debitCardNumber);
    }
}
