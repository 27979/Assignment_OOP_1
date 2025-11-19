public class TaxCategory extends TaxAuthority {
    private String categoryName;

    public TaxCategory() {}
    public TaxCategory(String authorityName, String categoryName) {
        super(authorityName);
        this.categoryName = categoryName;
    }

    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
}
