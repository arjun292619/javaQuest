package cases.designs.atm.v1;

public class CashDepositSlot extends DepositSlot {
    public double receiveDollarBill() {
        return getDepositAmount();
    }
}
