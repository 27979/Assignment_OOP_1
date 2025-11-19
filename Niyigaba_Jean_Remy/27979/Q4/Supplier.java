public class Supplier extends Department {
    private String supplierName;
    private String supplierTIN;
    private String contact;
    public Supplier(int id, String orgName, String address, String contactEmail, String deptName, String deptCode,
                    String supplierName, String supplierTIN, String contact) {
        super(id, orgName, address, contactEmail, deptName, deptCode);
        this.supplierName = supplierName;
        setSupplierTIN(supplierTIN);
        this.contact = contact; 
    }
    public void setSupplierTIN(String supplierTIN) {
        if (!supplierTIN.matches("\\d{9}"))
            throw new IllegalArgumentException("TIN must be 9 digits");
        this.supplierTIN = supplierTIN;
    }
}

