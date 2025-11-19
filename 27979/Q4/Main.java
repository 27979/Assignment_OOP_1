
import java.util.*;
public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Organization> organizations = new ArrayList<>();
    private static List<Department> departments = new ArrayList<>();
    private static List<Supplier> suppliers = new ArrayList<>();
    private static List<Product> products = new ArrayList<>();
    private static List<PurchaseOrder> purchaseOrders = new ArrayList<>();
    private static List<Delivery> deliveries = new ArrayList<>();
    private static List<Inspection> inspections = new ArrayList<>();
    private static List<Invoice> invoices = new ArrayList<>();
    private static List<ProcurementReport> reports = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("=== Procurement Management System ===\n");

        boolean running = true;
        while (running) {
            displayMainMenu();
            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    createOrganization();
                    break;
                case 2:
                    createDepartment();
                    break;
                case 3:
                    createSupplier();
                    break;
                case 4:
                    createProduct();
                    break;
                case 5:
                    createPurchaseOrder();
                    break;
                case 6:
                    createDelivery();
                    break;
                case 7:
                    createInspection();
                    break;
                case 8:
                    createInvoice();
                    break;
                case 9:
                    createProcurementReport();
                    break;
                case 10:
                    viewAllRecords();
                    break;
                case 11:
                    calculateTotalInvoices();
                    break;
                case 0:
                    running = false;
                    System.out.println("Thank you for using Procurement Management System!");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.\n");
            }
        }

        scanner.close();
    }

    private static void displayMainMenu() {
        System.out.println("\n========== MAIN MENU ==========");
        System.out.println("1. Create Organization");
        System.out.println("2. Create Department");
        System.out.println("3. Create Supplier");
        System.out.println("4. Create Product");
        System.out.println("5. Create Purchase Order");
        System.out.println("6. Create Delivery");
        System.out.println("7. Create Inspection");
        System.out.println("8. Create Invoice");
        System.out.println("9. Create Procurement Report");
        System.out.println("10. View All Records");
        System.out.println("11. Calculate Total Invoices");
        System.out.println("0. Exit");
        System.out.println("================================");
    }

    private static void createOrganization() {
        System.out.println("\n--- Create Organization ---");
        int id = getIntInput("Enter Organization ID: ");
        scanner.nextLine();
        System.out.print("Enter Organization Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        Organization org = new Organization(id, name, address, email);
        organizations.add(org);
        System.out.println("✓ Organization created successfully: " + name);
    }

    private static void createDepartment() {
        System.out.println("\n--- Create Department ---");
        int id = getIntInput("Enter Department ID: ");
        scanner.nextLine();
        System.out.print("Enter Organization Name: ");
        String orgName = scanner.nextLine();
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Department Name: ");
        String deptName = scanner.nextLine();
        System.out.print("Enter Department Code: ");
        String deptCode = scanner.nextLine();

        Department dept = new Department(id, orgName, address, email, deptName, deptCode);
        departments.add(dept);
        System.out.println("✓ Department created successfully: " + deptName);
    }

    private static void createSupplier() {
        System.out.println("\n--- Create Supplier ---");
        int id = getIntInput("Enter Supplier ID: ");
        scanner.nextLine();
        System.out.print("Enter Organization Name: ");
        String orgName = scanner.nextLine();
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Department Name: ");
        String deptName = scanner.nextLine();
        System.out.print("Enter Department Code: ");
        String deptCode = scanner.nextLine();
        System.out.print("Enter Supplier Name: ");
        String supplierName = scanner.nextLine();
        System.out.print("Enter Supplier TIN (9 digits): ");
        String tin = scanner.nextLine();
        System.out.print("Enter Contact Number: ");
        String contact = scanner.nextLine();

        Supplier supplier = new Supplier(id, orgName, address, email, deptName, deptCode, supplierName, tin, contact);
        suppliers.add(supplier);
        System.out.println("✓ Supplier created successfully: " + supplierName);
    }

    private static void createProduct() {
        System.out.println("\n--- Create Product ---");
        int id = getIntInput("Enter Product ID: ");
        scanner.nextLine();
        System.out.print("Enter Organization Name: ");
        String orgName = scanner.nextLine();
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Department Name: ");
        String deptName = scanner.nextLine();
        System.out.print("Enter Department Code: ");
        String deptCode = scanner.nextLine();
        System.out.print("Enter Supplier Name: ");
        String supplierName = scanner.nextLine();
        System.out.print("Enter Supplier TIN: ");
        String tin = scanner.nextLine();
        System.out.print("Enter Contact: ");
        String contact = scanner.nextLine();
        System.out.print("Enter Product Name: ");
        String productName = scanner.nextLine();
        double unitPrice = getDoubleInput("Enter Unit Price: ");
        int quantity = getIntInput("Enter Quantity: ");
        scanner.nextLine();

        Product product = new Product(id, orgName, address, email, deptName, deptCode,
                                      supplierName, tin, contact, productName, unitPrice, quantity);
        products.add(product);
        System.out.println("✓ Product created successfully: " + productName);
    }

    private static void createPurchaseOrder() {
        System.out.println("\n--- Create Purchase Order ---");
        int id = getIntInput("Enter Purchase Order ID: ");
        scanner.nextLine();
        System.out.print("Enter Organization Name: ");
        String orgName = scanner.nextLine();
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Department Name: ");
        String deptName = scanner.nextLine();
        System.out.print("Enter Department Code: ");
        String deptCode = scanner.nextLine();
        System.out.print("Enter Supplier Name: ");
        String supplierName = scanner.nextLine();
        System.out.print("Enter Supplier TIN: ");
        String tin = scanner.nextLine();
        System.out.print("Enter Contact: ");
        String contact = scanner.nextLine();
        System.out.print("Enter Product Name: ");
        String productName = scanner.nextLine();
        double unitPrice = getDoubleInput("Enter Unit Price: ");
        int quantity = getIntInput("Enter Quantity: ");
        scanner.nextLine();
        System.out.print("Enter PO Number: ");
        String poNumber = scanner.nextLine();
        Date orderDate = new Date();

        PurchaseOrder po = new PurchaseOrder(id, orgName, address, email, deptName, deptCode,
                                             supplierName, tin, contact, productName, unitPrice, quantity,
                                             poNumber, orderDate);
        purchaseOrders.add(po);
        System.out.println("✓ Purchase Order created successfully: " + poNumber);
    }

    private static void createDelivery() {
        System.out.println("\n--- Create Delivery ---");
        int id = getIntInput("Enter Delivery ID: ");
        scanner.nextLine();
        System.out.print("Enter Organization Name: ");
        String orgName = scanner.nextLine();
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Department Name: ");
        String deptName = scanner.nextLine();
        System.out.print("Enter Department Code: ");
        String deptCode = scanner.nextLine();
        System.out.print("Enter Supplier Name: ");
        String supplierName = scanner.nextLine();
        System.out.print("Enter Supplier TIN: ");
        String tin = scanner.nextLine();
        System.out.print("Enter Contact: ");
        String contact = scanner.nextLine();
        System.out.print("Enter Product Name: ");
        String productName = scanner.nextLine();
        double unitPrice = getDoubleInput("Enter Unit Price: ");
        int quantity = getIntInput("Enter Quantity: ");
        scanner.nextLine();
        System.out.print("Enter PO Number: ");
        String poNumber = scanner.nextLine();
        Date orderDate = new Date();
        Date deliveryDate = new Date();
        System.out.print("Enter Delivered By: ");
        String deliveredBy = scanner.nextLine();

        Delivery delivery = new Delivery(id, orgName, address, email, deptName, deptCode,
                                         supplierName, tin, contact, productName, unitPrice, quantity,
                                         poNumber, orderDate, deliveryDate, deliveredBy);
        deliveries.add(delivery);
        System.out.println("✓ Delivery created successfully for PO: " + poNumber);
    }

    private static void createInspection() {
        System.out.println("\n--- Create Inspection ---");
        int id = getIntInput("Enter Inspection ID: ");
        scanner.nextLine();
        System.out.print("Enter Organization Name: ");
        String orgName = scanner.nextLine();
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Department Name: ");
        String deptName = scanner.nextLine();
        System.out.print("Enter Department Code: ");
        String deptCode = scanner.nextLine();
        System.out.print("Enter Supplier Name: ");
        String supplierName = scanner.nextLine();
        System.out.print("Enter Supplier TIN: ");
        String tin = scanner.nextLine();
        System.out.print("Enter Contact: ");
        String contact = scanner.nextLine();
        System.out.print("Enter Product Name: ");
        String productName = scanner.nextLine();
        double unitPrice = getDoubleInput("Enter Unit Price: ");
        int quantity = getIntInput("Enter Quantity: ");
        scanner.nextLine();
        System.out.print("Enter PO Number: ");
        String poNumber = scanner.nextLine();
        Date orderDate = new Date();
        Date deliveryDate = new Date();
        System.out.print("Enter Delivered By: ");
        String deliveredBy = scanner.nextLine();
        System.out.print("Enter Inspector Name: ");
        String inspectorName = scanner.nextLine();
        System.out.print("Enter Inspection Status (Passed/Failed): ");
        String status = scanner.nextLine();
        System.out.print("Enter Inspection Remarks: ");
        String remarks = scanner.nextLine();

        Inspection inspection = new Inspection(id, orgName, address, email, deptName, deptCode,
                                              supplierName, tin, contact, productName, unitPrice, quantity,
                                              poNumber, orderDate, deliveryDate, deliveredBy,
                                              inspectorName, status, remarks);
        inspections.add(inspection);
        System.out.println("✓ Inspection created successfully - Status: " + status);
    }

    private static void createInvoice() {
        System.out.println("\n--- Create Invoice ---");
        int id = getIntInput("Enter Invoice ID: ");
        scanner.nextLine();
        System.out.print("Enter Organization Name: ");
        String orgName = scanner.nextLine();
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Department Name: ");
        String deptName = scanner.nextLine();
        System.out.print("Enter Department Code: ");
        String deptCode = scanner.nextLine();
        System.out.print("Enter Supplier Name: ");
        String supplierName = scanner.nextLine();
        System.out.print("Enter Supplier TIN: ");
        String tin = scanner.nextLine();
        System.out.print("Enter Contact: ");
        String contact = scanner.nextLine();
        System.out.print("Enter Product Name: ");
        String productName = scanner.nextLine();
        double unitPrice = getDoubleInput("Enter Unit Price: ");
        int quantity = getIntInput("Enter Quantity: ");
        scanner.nextLine();
        System.out.print("Enter PO Number: ");
        String poNumber = scanner.nextLine();
        Date orderDate = new Date();
        Date deliveryDate = new Date();
        System.out.print("Enter Delivered By: ");
        String deliveredBy = scanner.nextLine();
        System.out.print("Enter Inspector Name: ");
        String inspectorName = scanner.nextLine();
        System.out.print("Enter Inspection Status (Passed/Failed): ");
        String status = scanner.nextLine();
        System.out.print("Enter Inspection Remarks: ");
        String remarks = scanner.nextLine();
        System.out.print("Enter Invoice Number: ");
        String invoiceNo = scanner.nextLine();
        double invoiceAmount = getDoubleInput("Enter Invoice Amount: ");
        scanner.nextLine();

        Invoice invoice = new Invoice(id, orgName, address, email, deptName, deptCode,
                                      supplierName, tin, contact, productName, unitPrice, quantity,
                                      poNumber, orderDate, deliveryDate, deliveredBy,
                                      inspectorName, status, remarks, invoiceNo, invoiceAmount);
        invoices.add(invoice);
        System.out.println("✓ Invoice created successfully: " + invoiceNo);
    }

    private static void createProcurementReport() {
        System.out.println("\n--- Create Procurement Report ---");
        System.out.print("Enter Report Date (e.g., 2024-01-15): ");
        String reportDate = scanner.nextLine();
        System.out.print("Enter Report Summary: ");
        String summary = scanner.nextLine();

        ProcurementReport report = new ProcurementReport(reportDate, summary);
        reports.add(report);
        System.out.println("✓ Procurement Report created successfully");
    }

    private static void viewAllRecords() {
        System.out.println("\n========== ALL RECORDS ==========");
        System.out.println("Organizations: " + organizations.size());
        System.out.println("Departments: " + departments.size());
        System.out.println("Suppliers: " + suppliers.size());
        System.out.println("Products: " + products.size());
        System.out.println("Purchase Orders: " + purchaseOrders.size());
        System.out.println("Deliveries: " + deliveries.size());
        System.out.println("Inspections: " + inspections.size());
        System.out.println("Invoices: " + invoices.size());
        System.out.println("Reports: " + reports.size());
        System.out.println("=================================");
    }

    private static void calculateTotalInvoices() {
        if (invoices.isEmpty()) {
            System.out.println("\nNo invoices found!");
            return;
        }

        double total = ProcurementReport.calculateTotal(invoices);
        System.out.println("\n=================================");
        System.out.println("Total Number of Invoices: " + invoices.size());
        System.out.println("Total Invoice Amount: $" + total);
        System.out.println("=================================");
    }

    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input! Please enter a number: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static double getDoubleInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.print("Invalid input! Please enter a number: ");
            scanner.next();
        }
        return scanner.nextDouble();
    }
}
