public class Department extends Organization {
    private String deptName;
    private String deptCode;
    public Department(int id, String orgName, String address, String contactEmail, String deptName, String deptCode) {
        super(id, orgName, address, contactEmail);
        this.deptName = deptName;
        setDeptCode(deptCode);
    }
    public void setDeptCode(String deptCode) {
        if (!deptCode.matches("[a-zA-Z0-9]{3,}"))
            throw new IllegalArgumentException("Dept code must be alphanumeric and at least 3 characters long");
        this.deptCode = deptCode;
    }
}

