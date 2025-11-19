public class Payment extends TaxAssessment {
    protected double amountPaid;

    public Payment() {}

    public Payment(String name, String nid, int age, String companyName, double salary, double declaredIncome) throws TaxDataException {
        super(name, nid, age, companyName, salary, declaredIncome);
        this.amountPaid = 0.0;
    }

    public void makePayment(double amount) throws TaxDataException {
        if (amount < 0) throw new TaxDataException("Payment amount cannot be negative. [ID:27979]");
        this.amountPaid += amount;
    }

    public double getAmountPaid() { return amountPaid; }
}
