public class Employee extends Employer {
    private double salary;

    public Employee() {}

    public Employee(String name, String nid, int age, String companyName, double salary) throws TaxDataException {
        super(name, nid, age, companyName);
        setSalary(salary);
    }

    public double getSalary() { return salary; }
    public void setSalary(double salary) throws TaxDataException {
        if (salary < 0) throw new TaxDataException("Salary cannot be negative. [ID:27979]");
        this.salary = salary;
    }
}
