package Q6;

import java.util.*;

public class PayrollSystem {

    static Scanner sc = new Scanner(System.in);
    static List<Payslip> payslips = new ArrayList<>();

    static class Entity {
        private int id;
        private String createdDate, updatedDate;
        public Entity(int id, String createdDate, String updatedDate) {
            if (id <= 0 || createdDate == null || updatedDate == null)
                throw new IllegalArgumentException("Invalid Entity values");
            this.id = id; this.createdDate = createdDate; this.updatedDate = updatedDate;
        }
        public int getId() { return id; }
    }

    static class Organization extends Entity {
        private String orgName, orgCode, rssbNumber, contactEmail;
        public Organization(int id, String created, String updated, String name, String code, String rssb, String email) {
            super(id, created, updated);
            if (code.length() < 3 || !rssb.matches("\\d{8}") || !email.contains("@"))
                throw new IllegalArgumentException("Invalid Organization Values");
            this.orgName = name; this.orgCode = code; this.rssbNumber = rssb; this.contactEmail = email;
        }
    }

    static class Department extends Organization {
        private String deptName, deptCode, managerName;
        public Department(int id, String created, String updated, String name, String code, String rssb, String email,
                          String dName, String dCode, String mgr) {
            super(id, created, updated, name, code, rssb, email);
            if (dCode.length() < 3 || dName.isEmpty() || mgr.isEmpty())
                throw new IllegalArgumentException("Invalid Department Values");
            this.deptName = dName; this.deptCode = dCode; this.managerName = mgr;
        }
    }

    static class Employee extends Department {
        private int employeeID;
        private String fullName, position;
        private double baseSalary;
        private boolean rssbRegistered;
        public Employee(int id, String created, String updated, String name, String code, String rssb, String email,
                        String dName, String dCode, String mgr, int eID, String fName, String pos, double salary, boolean reg) {
            super(id, created, updated, name, code, rssb, email, dName, dCode, mgr);
            if (eID < 1000 || salary <= 0) throw new IllegalArgumentException("Invalid Employee Data");
            this.employeeID = eID; this.fullName = fName; this.position = pos; this.baseSalary = salary; this.rssbRegistered = reg;
        }
        public double getBaseSalary() { return baseSalary; }
        public String getFullName() { return fullName; }
        public int getEmployeeID() { return employeeID; }
        public String getPosition() { return position; }
        public boolean isRssbRegistered() { return rssbRegistered; }
    }

    static class PayrollPeriod extends Employee {
        private int month, year;
        private String startDate, endDate;
        public PayrollPeriod(int id, String created, String updated, String name, String code, String rssb, String email,
                             String dName, String dCode, String mgr, int eID, String fName, String pos, double salary,
                             boolean reg, int m, int y, String start, String end) {
            super(id, created, updated, name, code, rssb, email, dName, dCode, mgr, eID, fName, pos, salary, reg);
            if (m < 1 || m > 12 || y < 2000) throw new IllegalArgumentException("Invalid Payroll Period");
            this.month = m; this.year = y; this.startDate = start; this.endDate = end;
        }
    }

    static class SalaryStructure extends PayrollPeriod {
        protected double basicPay, transportAllowance, housingAllowance;
        public SalaryStructure(int id, String created, String updated, String name, String code, String rssb, String email,
                               String dName, String dCode, String mgr, int eID, String fName, String pos, double salary,
                               boolean reg, int m, int y, String start, String end, double basic, double trans, double house) {
            super(id, created, updated, name, code, rssb, email, dName, dCode, mgr, eID, fName, pos, salary, reg, m, y, start, end);
            if (basic < 0 || trans < 0 || house < 0) throw new IllegalArgumentException("Invalid Salary Structure Values");
            this.basicPay = basic; this.transportAllowance = trans; this.housingAllowance = house;
        }
    }

    static class Deduction extends SalaryStructure {
        protected double rssbContribution, payeTax, loanDeduction;
        public Deduction(int id, String created, String updated, String name, String code, String rssb, String email,
                         String dName, String dCode, String mgr, int eID, String fName, String pos, double salary,
                         boolean reg, int m, int y, String start, String end, double basic, double trans, double house,
                         double loan) {
            super(id, created, updated, name, code, rssb, email, dName, dCode, mgr, eID, fName, pos, salary, reg, m, y, start, end, basic, trans, house);
            this.rssbContribution = basic * 0.05; this.loanDeduction = loan; this.payeTax = basic * 0.20;
        }
    }

    static class Allowance extends Deduction {
        protected double overtimeHours, overtimeRate, bonus;
        public Allowance(int id, String created, String updated, String name, String code, String rssb, String email,
                         String dName, String dCode, String mgr, int eID, String fName, String pos, double salary,
                         boolean reg, int m, int y, String start, String end, double basic, double trans, double house,
                         double loan, double hours, double rate, double bonus) {
            super(id, created, updated, name, code, rssb, email, dName, dCode, mgr, eID, fName, pos, salary, reg, m, y, start, end, basic, trans, house, loan);
            this.overtimeHours = hours; this.overtimeRate = rate; this.bonus = bonus;
        }
    }

    static class Payroll extends Allowance {
        protected double grossSalary, totalDeductions, netSalary;
        public Payroll(int id, String created, String updated, String name, String code, String rssb, String email,
                       String dName, String dCode, String mgr, int eID, String fName, String pos, double salary,
                       boolean reg, int m, int y, String start, String end, double basic, double trans, double house,
                       double loan, double hours, double rate, double bonus) {
            super(id, created, updated, name, code, rssb, email, dName, dCode, mgr, eID, fName, pos, salary, reg, m, y, start, end, basic, trans, house, loan, hours, rate, bonus);
            this.grossSalary = basic + trans + house + (hours * rate) + bonus;
            this.totalDeductions = rssbContribution + payeTax + loanDeduction;
            this.netSalary = grossSalary - totalDeductions;
        }
    }

    static final class Payslip extends Payroll {
        private String payslipNumber, issueDate;
        public Payslip(int id, String created, String updated, String name, String code, String rssb, String email,
                       String dName, String dCode, String mgr, int eID, String fName, String pos, double salary,
                       boolean reg, int m, int y, String start, String end, double basic, double trans, double house,
                       double loan, double hours, double rate, double bonus, String slipNo, String issue) {
            super(id, created, updated, name, code, rssb, email, dName, dCode, mgr, eID, fName, pos, salary, reg, m, y, start, end, basic, trans, house, loan, hours, rate, bonus);
            this.payslipNumber = slipNo; this.issueDate = issue;
        }

        public void generatePayslip() {
            System.out.println("\n========== PAYSLIP [ID:27979] ==========");
            System.out.println("Payslip No : " + payslipNumber);
            System.out.println("Employee ID: " + getEmployeeID());
            System.out.println("Full Name  : " + getFullName());
            System.out.println("Position   : " + getPosition());
            System.out.println("RSSB Registered: " + (isRssbRegistered() ? "Yes" : "No"));
            System.out.println("Base Salary: " + basicPay);
            System.out.println("Gross Salary: " + grossSalary);
            System.out.println("Total Deductions: " + totalDeductions);
            System.out.println("NET PAY: " + netSalary);
            System.out.println("Issue Date: " + issueDate);
            System.out.println("=============================\n");
        }
    }

    // --- Utility Input Methods ---
    private static double getDouble(String prompt) {
        double val;
        while (true) {
            System.out.print(prompt);
            try {
                val = sc.nextDouble();
                if (val < 0) throw new InputMismatchException();
                sc.nextLine(); // consume newline
                return val;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, enter a positive number.");
                sc.nextLine();
            }
        }
    }

    private static String getString(String prompt) {
        String val;
        while (true) {
            System.out.print(prompt);
            val = sc.nextLine().trim();
            if (!val.isEmpty()) return val;
            System.out.println("Input cannot be empty!");
        }
    }

    // --- Main Functions ---
    private static void createPayslip() {
        String name = getString("Employee Name: ");
        double basic = getDouble("Basic Pay: ");
        double trans = getDouble("Transport Allowance: ");
        double house = getDouble("Housing Allowance: ");
        double loan = getDouble("Loan Deduction: ");
        double hours = getDouble("Overtime Hours: ");
        double rate = getDouble("Overtime Rate: ");
        double bonus = getDouble("Bonus: ");
        String slip = getString("Payslip Number: ");
        String issue = getString("Issue Date: ");

        Payslip p = new Payslip(1, "2025-01-01", "2025-01-01",
                "RSSB Org", "ORG01", "12345678", "info@rssb.com",
                "HR Dept", "HR01", "Manager X",
                1001, name, "Officer", basic, true, 1, 2025,
                "2025-01-01", "2025-01-31",
                basic, trans, house, loan, hours, rate, bonus, slip, issue);

        payslips.add(p);
        p.generatePayslip();
    }

    private static void viewPayslips() {
        if (payslips.isEmpty()) System.out.println("No payslips found! [ID:27979]");
        else for (Payslip p : payslips) p.generatePayslip();
    }

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n===== PAYROLL MENU [ID:27979] =====");
            System.out.println("1. Create Payslip");
            System.out.println("2. View All Payslips");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            while (!sc.hasNextInt()) {
                System.out.println("Invalid input! Enter a number [ID:27979]");
                sc.nextLine();
            }
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> createPayslip();
                case 2 -> viewPayslips();
                case 3 -> System.out.println("Exiting... [ID:27979]");
                default -> System.out.println("Invalid choice! [ID:27979]");
            }
        } while (choice != 3);
    }
}
