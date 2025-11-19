public class Product extends Supplier {
    private String productName;
    private double unitPrice;
    private int quantity;
    public Product(int id, String orgName, String address, String contactEmail, String deptName, String deptCode,
                   String supplierName, String supplierTIN, String contact, String productName, double unitPrice, int quantity) {
        super(id, orgName, address, contactEmail, deptName, deptCode, supplierName, supplierTIN, contact);
        this.productName = productName;
        setUnitPrice(unitPrice);
        setQuantity(quantity);
    }
    public void setUnitPrice(double unitPrice) {
        if (unitPrice <= 0)
            throw new IllegalArgumentException("Unit price must be greater than 0");
        this.unitPrice = unitPrice;
    }
    public void setQuantity(int quantity) {
        if (quantity < 0)
            throw new IllegalArgumentException("Quantity cannot be negative");
        this.quantity = quantity;
    }
    public double getUnitPrice() {
        return unitPrice;
    }
    public int getQuantity() {
        return quantity;
    }
}

