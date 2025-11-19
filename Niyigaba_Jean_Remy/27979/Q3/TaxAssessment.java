public class TaxAssessment extends TaxDeclaration {
    protected double assessedTax;

    public TaxAssessment() {}

    public TaxAssessment(String name, String nid, int age, String companyName, double salary, double declaredIncome) throws TaxDataException {
        super(name, nid, age, companyName, salary, declaredIncome);
        this.assessedTax = 0;
    }

 
    public void assessTax() {
        double income = getDeclaredIncome();
        double tax = 0.0;
        if (income <= 10000) {
            tax = income * 0.10;
        } else if (income <= 50000) {
            tax = 10000 * 0.10 + (income - 10000) * 0.20;
        } else {
            tax = 10000 * 0.10 + (50000 - 10000) * 0.20 + (income - 50000) * 0.30;
        }
        this.assessedTax = tax;
    }

    public double getAssessedTax() { return assessedTax; }
}
