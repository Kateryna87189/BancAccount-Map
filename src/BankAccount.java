import java.util.Objects;

/*BankAccount {Person owner, String IBAN, double balance).
 */
public class BankAccount {
    private Person owner;
    private String IBAN;
    private  double balance;

    public BankAccount(Person owner, String IBAN, double balance) {
        this.owner = owner;
        this.IBAN = IBAN;
        this.balance = balance;
    }

    public Person getOwner() {
        return owner;
    }

    public String getIBAN() {
        return IBAN;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return String.format("%s %s [%.02f] Euro", owner, IBAN, balance);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BankAccount that = (BankAccount) o;
        return Double.compare(balance, that.balance) == 0 && Objects.equals(owner, that.owner) && Objects.equals(IBAN, that.IBAN);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(owner);
        result = 31 * result + Objects.hashCode(IBAN);
        result = 31 * result + Double.hashCode(balance);
        return result;
    }
}
