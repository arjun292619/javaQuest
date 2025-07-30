package cases.designs.atm.v1;

public class Printer {
    public boolean printReceipt(Transaction transaction) {
        if (transaction.getStatus()) {
            System.out.println("Transaction details");
            return true;
        }
        return false;
    }
}
