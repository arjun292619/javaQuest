package cases.designs.atm.v1;

public class CheckDepositSlot extends DepositSlot {
    public double getCheckAmount() {
        return getDepositAmount();
    }
}
