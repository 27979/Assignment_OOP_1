public final class TaxRecord extends Payment {
    public TaxRecord() {}

    public TaxRecord(String name, String nid, int age, String companyName, double salary, double declaredIncome) throws TaxDataException {
        super(name, nid, age, companyName, salary, declaredIncome);
        assessTax();
    }

    public double computePayableTax() {
        double payable = getAssessedTax() - getAmountPaid();
        return payable < 0 ? 0 : payable;
    }

    @Override
    public String toString() {
        return String.format("TaxRecord{name='%s', nid='%s', age=%d, company='%s', salary=%.2f, declaredIncome=%.2f, assessedTax=%.2f, amountPaid=%.2f, payable=%.2f}",
            getName(), getNid(), getAge(), getCompanyName(), getSalary(), getDeclaredIncome(), getAssessedTax(), getAmountPaid(), computePayableTax());
    }
}
