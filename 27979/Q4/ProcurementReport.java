import java.util.List;
public final class ProcurementReport {
    private String reportDate;
    private String summary;
    public ProcurementReport(String reportDate, String summary) {
        this.reportDate = reportDate;
        this.summary = summary;
    }
    public static double calculateTotal(List<Invoice> invoices) {
        double total = 0;
        for (Invoice invoice : invoices) {
            total += invoice.getInvoiceAmount();
        }
        return total;
    }
}
