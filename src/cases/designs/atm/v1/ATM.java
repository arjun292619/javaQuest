package cases.designs.atm.v1;


public class ATM {
    private long atmId;
    private Address location;

    private KeyPad keyPad;
    private Screen screen;
    private Printer printer;
    private DepositSlot depositSlot;

    public boolean authenticateUser() {
        return false;
    }

//    public boolean makeTransaction(Customer, Transaction transaction) {
//
//    }
}
