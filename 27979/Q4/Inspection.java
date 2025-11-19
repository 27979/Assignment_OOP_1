import java.util.Date;

public class Inspection extends Delivery {
    private String inspectorName;
    private String status;
    private String remarks;
    public Inspection(int id, String orgName, String address, String contactEmail, String deptName, String deptCode,
                      String supplierName, String supplierTIN, String contact, String productName, double unitPrice,
                      int quantity, String poNumber, Date orderDate, Date deliveryDate, String deliveredBy,
                      String inspectorName, String status, String remarks) {
        super(id, orgName, address, contactEmail, deptName, deptCode, supplierName, supplierTIN, contact, productName, unitPrice, quantity, poNumber, orderDate, deliveryDate, deliveredBy);
        this.inspectorName = inspectorName;
        setStatus(status);
        this.remarks = remarks;
    }
    public void setStatus(String status) {
        if (!status.equals("Passed") && !status.equals("Failed"))
            throw new IllegalArgumentException("Status must be 'Passed' or 'Failed'");
        this.status = status;
    }
}

