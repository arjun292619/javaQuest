package cases.designs.atm.v1;

public class Screen {
    public String showMessage(String message) {
        return message;
    }

    public String getInput(TransactionType transactionType) {
        return transactionType.toString();
    }
}
