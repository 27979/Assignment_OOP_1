public class TaxDeclaration extends Employee {
    private double declaredIncome;

    public TaxDeclaration() {}

    public TaxDeclaration(String name, String nid, int age, String companyName, double salary, double declaredIncome) throws TaxDataException {
        super(name, nid, age, companyName, salary);
        setDeclaredIncome(declaredIncome);
    }

    public double getDeclaredIncome() { return declaredIncome; }
    public void setDeclaredIncome(double declaredIncome) throws TaxDataException {
        if (declaredIncome < 0) throw new TaxDataException("Declared income cannot be negative. [ID:27979]");
        this.declaredIncome = declaredIncome;
    }
}
