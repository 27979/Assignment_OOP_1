import java.util.Date;

public class Invoice extends Inspection {
    private String invoiceNo;
    private double invoiceAmount;
    public Invoice(int id, String orgName, String address, String contactEmail, String deptName, String deptCode,
                   String supplierName, String supplierTIN, String contact, String productName, double unitPrice,
                   int quantity, String poNumber, Date orderDate, Date deliveryDate, String deliveredBy,
                   String inspectorName, String status, String remarks, String invoiceNo, double invoiceAmount) {
        super(id, orgName, address, contactEmail, deptName, deptCode, supplierName, supplierTIN, contact, productName, unitPrice, quantity, poNumber, orderDate, deliveryDate, deliveredBy, inspectorName, status, remarks);
        this.invoiceNo = invoiceNo;
        setInvoiceAmount(invoiceAmount);
    }
    public void setInvoiceAmount(double invoiceAmount) {
        if (invoiceAmount <= 0)
            throw new IllegalArgumentException("Invoice amount must be greater than 0");
        this.invoiceAmount = invoiceAmount;
    }
    public double getInvoiceAmount() {
        return invoiceAmount;
    }
    public String getInvoiceNo() {
        return invoiceNo;
    }
    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    
}
