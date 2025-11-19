import java.util.Date;

public class PurchaseOrder extends Product {
    private String poNumber;
    private Date orderDate;
    private double totalAmount;
    public PurchaseOrder(int id, String orgName, String address, String contactEmail, String deptName, String deptCode,
                         String supplierName, String supplierTIN, String contact, String productName, double unitPrice,
                         int quantity, String poNumber, Date orderDate) {
        super(id, orgName, address, contactEmail, deptName, deptCode, supplierName, supplierTIN, contact, productName, unitPrice, quantity);
        this.poNumber = poNumber;
        this.orderDate = orderDate;
        this.totalAmount = calculateTotal();
    }
    public double calculateTotal() {
        return getUnitPrice() * getQuantity();
    }
}
