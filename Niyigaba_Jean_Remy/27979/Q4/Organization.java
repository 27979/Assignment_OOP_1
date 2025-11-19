public class Organization extends Entity {
    private String orgName;
    private String address;
    private String contactEmail;
    public Organization(int id, String orgName, String address, String contactEmail) {
        super(id);
        this.orgName = orgName;
        this.address = address;
        setContactEmail(contactEmail);
    }
    public void setContactEmail(String contactEmail) {
        if (!contactEmail.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"))
            throw new IllegalArgumentException("Invalid email format");
        this.contactEmail = contactEmail;
    }
    public String getOrgName() {
        return orgName;
    }
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getContactEmail() {
        return contactEmail;
    }


}
