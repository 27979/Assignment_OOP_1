public class TaxAuthority extends Entity {
    private String authorityName;

    public TaxAuthority() {}
    public TaxAuthority(String authorityName) {
        this.authorityName = authorityName;
    }

    public String getAuthorityName() { return authorityName; }
    public void setAuthorityName(String authorityName) { this.authorityName = authorityName; }
}
