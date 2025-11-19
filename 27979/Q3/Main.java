
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<TaxRecord> records = new ArrayList<>();
    private static int nextId = 1;

    public static void main(String[] args) {
        System.out.println("Tax Administration System (Menu-driven) [ID:27979]");
        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> createTaxRecord();
                case "2" -> declareIncome();
                case "3" -> assessTax();
                case "4" -> makePayment();
                case "5" -> viewRecords();
                case "6" -> running = false;
                default -> System.out.println("Invalid option. Choose 1-6. [ID:27979]");
            }
        }
        System.out.println("Exiting. Goodbye! [ID:27979]");
    }

    private static void printMenu() {
        System.out.println("1. Create Taxpayer Record [ID:27979]");
        System.out.println("2. Declare Income [ID:27979]");
        System.out.println("3. Assess Tax [ID:27979]");
        System.out.println("4. Make Payment [ID:27979]");
        System.out.println("5. View All Records [ID:27979]");
        System.out.println("6. Exit [ID:27979]");
        System.out.print("Choose: ");
    }

    private static void createTaxRecord() {
        try {
            System.out.print("Name: ");
            String name = scanner.nextLine().trim();
            while (name.isEmpty()) {
                System.out.print("Name cannot be empty. Enter again: ");
                name = scanner.nextLine().trim();
            }

            System.out.print("NID (10 digits): ");
            String nid = scanner.nextLine().trim();
            while (!nid.matches("\\d{10}") || findByNid(nid) != null) {
                System.out.print("Invalid or duplicate NID. Enter again: ");
                nid = scanner.nextLine().trim();
            }

            System.out.print("Age: ");
            int age = getPositiveInt("Enter valid Age (1-120): ", 1, 120);

            System.out.print("Company (or '-' if none): ");
            String company = scanner.nextLine().trim();
            if (company.equals("-") || company.isEmpty()) company = "N/A";

            System.out.print("Monthly Salary: ");
            double salary = getPositiveDouble("Enter valid salary (>0): ");

            System.out.print("Declared Annual Income: ");
            double declared = getPositiveDouble("Enter valid declared income (>0): ");

            TaxRecord tr = new TaxRecord(name, nid, age, company, salary, declared);
            tr.id = nextId++;
            records.add(tr);
            System.out.println("Record created: " + tr + " [ID:27979]");

        } catch (TaxDataException tde) {
            System.out.println("Validation error: " + tde.getMessage() + " [ID:27979]");
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage() + " [ID:27979]");
        }
    }

    private static TaxRecord findByNid(String nid) {
        for (TaxRecord r : records)
            if (r.getNid().equals(nid))
                return r;
        return null;
    }

    private static void declareIncome() {
        System.out.print("Enter NID to update declared income: ");
        String nid = scanner.nextLine().trim();
        TaxRecord r = findByNid(nid);
        if (r == null) {
            System.out.println("Record not found. [ID:27979]");
            return;
        }
        try {
            System.out.print("New declared annual income: ");
            double declared = getPositiveDouble("Enter valid declared income (>0): ");
            r.setDeclaredIncome(declared);
            r.assessTax();
            System.out.println("Updated: " + r + " [ID:27979]");
        } catch (TaxDataException tde) {
            System.out.println("Validation error: " + tde.getMessage() + " [ID:27979]");
        }
    }

    private static void assessTax() {
        System.out.print("Enter NID to assess tax: ");
        String nid = scanner.nextLine().trim();
        TaxRecord r = findByNid(nid);
        if (r == null) {
            System.out.println("Record not found. [ID:27979]");
            return;
        }
        r.assessTax();
        System.out.println("Assessed: assessedTax= "
                + String.format("%.2f", r.getAssessedTax())
                + ", payable= " + String.format("%.2f", r.computePayableTax())
                + " [ID:27979]");
    }

    private static void makePayment() {
        System.out.print("Enter NID to make payment: ");
        String nid = scanner.nextLine().trim();
        TaxRecord r = findByNid(nid);
        if (r == null) {
            System.out.println("Record not found. [ID:27979]");
            return;
        }
        try {
            System.out.print("Payment amount: ");
            double amt = getPositiveDouble("Enter valid payment (>0): ");
            r.makePayment(amt);
            System.out.println("Payment accepted. Remaining payable: "
                    + String.format("%.2f", r.computePayableTax())
                    + " [ID:27979]");
        } catch (TaxDataException tde) {
            System.out.println("Payment error: " + tde.getMessage() + " [ID:27979]");
        }
    }

    private static void viewRecords() {
        if (records.isEmpty()) {
            System.out.println("No records yet. [ID:27979]");
            return;
        }
        System.out.println("All Tax Records [ID:27979]");
        for (TaxRecord r : records) {
            System.out.println(r + " [ID:27979]");
        }
    }

    private static int getPositiveInt(String prompt, int min, int max) {
        while (true) {
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                if (value >= min && value <= max) return value;
                System.out.print(prompt);
            } catch (NumberFormatException e) {
                System.out.print(prompt);
            }
        }
    }

    private static double getPositiveDouble(String prompt) {
        while (true) {
            try {
                double value = Double.parseDouble(scanner.nextLine().trim());
                if (value > 0) return value;
                System.out.print(prompt);
            } catch (NumberFormatException e) {
                System.out.print(prompt);
            }
        }
    }
}
