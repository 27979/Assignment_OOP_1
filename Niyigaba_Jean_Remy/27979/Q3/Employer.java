public class Employer extends Taxpayer {
    private String companyName;

    public Employer() {}

    public Employer(String name, String nid, int age, String companyName) throws TaxDataException {
        super(name, nid, age);
        this.companyName = companyName;
    }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }
}
