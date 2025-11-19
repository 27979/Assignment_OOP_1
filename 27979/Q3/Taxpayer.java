public class Taxpayer extends TaxCategory {
    private String name;
    private String nid;
    private int age;

    public Taxpayer() {}

    public Taxpayer(String name, String nid, int age) throws TaxDataException {
        validate(name, nid, age);
        this.name = name;
        this.nid = nid;
        this.age = age;
    }

    private void validate(String name, String nid, int age) throws TaxDataException {
        if (name == null || name.trim().isEmpty()) throw new TaxDataException("Name cannot be empty. [ID:27979]");
        if (nid == null || nid.trim().length() < 3) throw new TaxDataException("NID must be at least 3 characters. [ID:27979]");
        if (age < 18) throw new TaxDataException("Taxpayer must be at least 18 years old. [ID:27979]");
    }

    public String getName() { return name; }
    public String getNid() { return nid; }
    public int getAge() { return age; }

    public void setName(String name) throws TaxDataException { if (name==null||name.trim().isEmpty()) throw new TaxDataException("Name cannot be empty  [ID:27979]"); this.name = name; }
    public void setNid(String nid) throws TaxDataException { if (nid==null||nid.trim().length()<3) throw new TaxDataException("NID must be at least 3 characters [ID:27979]"); this.nid = nid; }
    public void setAge(int age) throws TaxDataException { if (age<18) throw new TaxDataException("Taxpayer must be at least 18 years old [ID:27979]"); this.age = age; }
}
