import java.util.Date;

public class Delivery extends PurchaseOrder {
    private Date deliveryDate;
    private String deliveredBy;
    public Delivery(int id, String orgName, String address, String contactEmail, String deptName, String deptCode,
                    String supplierName, String supplierTIN, String contact, String productName, double unitPrice,
                    int quantity, String poNumber, Date orderDate, Date deliveryDate, String deliveredBy) {
        super(id, orgName, address, contactEmail, deptName, deptCode, supplierName, supplierTIN, contact, productName, unitPrice, quantity, poNumber, orderDate);
        this.deliveryDate = deliveryDate;
        if (deliveredBy == null)
            throw new IllegalArgumentException("Delivered By cannot be null");
        this.deliveredBy = deliveredBy;
    }

    
}
