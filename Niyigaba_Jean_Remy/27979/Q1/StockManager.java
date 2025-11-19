import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

class Entity {
    private int id;
    private LocalDate createdDate;
    private LocalDate updatedDate;

    public Entity(int id) {
        if(id <= 0) throw new IllegalArgumentException("ID must be > 0 [ID:27979]");
        this.id = id;
        this.createdDate = LocalDate.now();
        this.updatedDate = LocalDate.now();
    }

    public int getId() { return id; }
    public void update() { this.updatedDate = LocalDate.now(); }
}

class Warehouse extends Entity {
    private String name, location, phone;
    public Warehouse(int id, String name, String location, String phone) {
        super(id);
        if(phone.length() != 10) throw new IllegalArgumentException("Phone must be 10 digits [ID:27979]");
        this.name = name; this.location = location; this.phone = phone;
    }
}

class Category extends Warehouse {
    private String categoryName, categoryCode;
    public Category(int id, String w, String loc, String phone, String categoryName, String categoryCode) {
        super(id, w, loc, phone);
        if(categoryCode.length() < 3 || !categoryCode.matches("[A-Za-z0-9]+"))
            throw new IllegalArgumentException("Invalid category code [ID:27979]");
        this.categoryName = categoryName; this.categoryCode = categoryCode;
    }
}

class Supplier extends Category {
    private String supplierName, supplierEmail, supplierPhone;
    public Supplier(int id, String w, String loc, String phone, String cat, String code,
                    String supplierName, String supplierEmail, String supplierPhone) {
        super(id, w, loc, phone, cat, code);
        if(!supplierEmail.contains("@")) throw new IllegalArgumentException("Invalid email [ID:27979]");
        if(supplierPhone.length() != 10) throw new IllegalArgumentException("Invalid phone [ID:27979]");
        this.supplierName = supplierName; this.supplierEmail = supplierEmail; this.supplierPhone = supplierPhone;
    }
}

class Product extends Supplier {
    private String productName;
    private double unitPrice;
    private int stockLimit;

    public Product(int id, String w, String loc, String phone, String cat, String code,
                   String supName, String supEmail, String supPhone,
                   String productName, double unitPrice, int stockLimit) {
        super(id, w, loc, phone, cat, code, supName, supEmail, supPhone);
        if(unitPrice <= 0) throw new IllegalArgumentException("Unit price must be > 0 [ID:27979]");
        if(stockLimit < 0) throw new IllegalArgumentException("Stock limit must be >= 0 [ID:27979]");
        this.productName = productName; this.unitPrice = unitPrice; this.stockLimit = stockLimit;
    }

    public String getProductName() { return productName; }
    public double getUnitPrice() { return unitPrice; }
}

class StockItem extends Product {
    protected int quantityAvailable, reorderLevel;

    public StockItem(int id, String w, String loc, String phone, String cat, String code,
                     String supName, String supEmail, String supPhone,
                     String prod, double price, int limit, int qty, int reorderLvl) {
        super(id, w, loc, phone, cat, code, supName, supEmail, supPhone, prod, price, limit);
        this.quantityAvailable = Math.max(qty, 0);
        this.reorderLevel = Math.max(reorderLvl, 0);
    }
}

class Purchase extends StockItem {
    public Purchase(int id, String w, String loc, String phone, String cat, String code,
                    String supName, String supEmail, String supPhone,
                    String prod, double price, int limit,
                    int qty, int reorderLvl,
                    int purchasedQty) {
        super(id, w, loc, phone, cat, code, supName, supEmail, supPhone, prod, price, limit, qty, reorderLvl);
        if(purchasedQty <= 0) throw new IllegalArgumentException("Purchased quantity must be > 0 [ID:27979]");
        this.quantityAvailable += purchasedQty;
    }
}

class Sale extends Purchase {
    public Sale(int id, String w, String loc, String phone, String cat, String code,
                String supName, String supEmail, String supPhone,
                String prod, double price, int limit,
                int qty, int reorderLvl,
                int purchasedQty, int soldQty) {
        super(id, w, loc, phone, cat, code, supName, supEmail, supPhone, prod, price, limit, qty, reorderLvl, purchasedQty);
        if(soldQty <= 0) throw new IllegalArgumentException("Sold quantity must be > 0 [ID:27979]");
        if(soldQty > this.quantityAvailable) throw new IllegalArgumentException("Not enough stock [ID:27979]");
        this.quantityAvailable -= soldQty;
    }
}

class Inventory extends Sale {
    protected int totalItems;
    protected double stockValue;

    public Inventory(int id, String w, String loc, String phone, String cat, String code,
                     String supName, String supEmail, String supPhone,
                     String prod, double price, int limit,
                     int qty, int reorderLvl,
                     int purchasedQty, int soldQty) {
        super(id, w, loc, phone, cat, code, supName, supEmail, supPhone, prod, price, limit, qty, reorderLvl, purchasedQty, soldQty);
        this.totalItems = this.quantityAvailable;
        this.stockValue = this.totalItems * price;
    }

    public int getTotalItems() { return totalItems; }
    public double getStockValue() { return stockValue; }
}

final class StockReport extends Inventory {
    public StockReport(int id, String w, String loc, String phone, String cat, String code,
                       String supName, String supEmail, String supPhone,
                       String prod, double price, int limit,
                       int qty, int reorderLvl,
                       int purchasedQty, int soldQty) {
        super(id, w, loc, phone, cat, code, supName, supEmail, supPhone, prod, price, limit, qty, reorderLvl, purchasedQty, soldQty);
    }

    public void generateReport() {
        System.out.println("Remaining Quantity: " + getTotalItems() + " [ID:27979]");
        System.out.println("Total Stock Value: " + getStockValue() + " [ID:27979]");
    }
}

public class StockManager {
    private static Scanner sc = new Scanner(System.in);
    private static ArrayList<StockReport> items = new ArrayList<>();

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n===== STOCK MENU [ID:27979] =====");
            System.out.println("1. Register New Product [ID:27979]");
            System.out.println("2. Purchase Stock [ID:27979]");
            System.out.println("3. Sell Stock [ID:27979]");
            System.out.println("4. View Inventory [ID:27979]");
            System.out.println("5. Generate Report [ID:27979]");
            System.out.println("6. Exit [ID:27979]");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch(choice) {
                case 1 -> registerProduct();
                case 2 -> purchaseStock();
                case 3 -> sellStock();
                case 4 -> viewInventory();
                case 5 -> generateReport();
                case 6 -> System.out.println("Goodbye [ID:27979]");
                default -> System.out.println("Invalid option [ID:27979]");
            }
        } while(choice != 6);
    }

    private static void registerProduct() {
        try {
            sc.nextLine();
            System.out.print("Product ID: "); int id = sc.nextInt(); sc.nextLine();
            System.out.print("Warehouse Name: "); String w = sc.nextLine();
            System.out.print("Location: "); String loc = sc.nextLine();
            System.out.print("Warehouse Phone: "); String phone = sc.nextLine();
            if(!isValidPhone(phone)) { System.out.println("Invalid phone [ID:27979]"); return; }
            System.out.print("Category Name: "); String cat = sc.nextLine();
            System.out.print("Category Code: "); String code = sc.nextLine();
            System.out.print("Supplier Name: "); String supn = sc.nextLine();
            System.out.print("Supplier Email: "); String supEmail = sc.nextLine();
            if(!isValidEmail(supEmail)) { System.out.println("Invalid email [ID:27979]"); return; }
            System.out.print("Supplier Phone: "); String supPhone = sc.nextLine();
            if(!isValidPhone(supPhone)) { System.out.println("Invalid phone [ID:27979]"); return; }
            System.out.print("Product Name: "); String prod = sc.nextLine();
            System.out.print("Unit Price: "); double price = sc.nextDouble();
            System.out.print("Stock Limit: "); int limit = sc.nextInt();
            System.out.print("Initial Quantity: "); int qty = sc.nextInt();
            System.out.print("Reorder Level: "); int reorder = sc.nextInt();

            StockReport sr = new StockReport(id, w, loc, phone, cat, code, supn, supEmail, supPhone, prod, price, limit, qty, reorder, 10, 10);
            items.add(sr);
            System.out.println("Product registered [ID:27979]");
        } catch(Exception e) {
            System.out.println("ERROR: " + e.getMessage() + " [ID:27979]");
        }
    }

    private static void purchaseStock() {
        if(items.isEmpty()) { System.out.println("No products [ID:27979]"); return; }
        System.out.print("Enter Product ID: "); int id = sc.nextInt();
        StockReport sr = find(id);
        if(sr == null) { System.out.println("Not found [ID:27979]"); return; }
        System.out.print("Enter Purchase Quantity: "); int qty = sc.nextInt();
        if(qty <= 0) { System.out.println("Invalid quantity [ID:27979]"); return; }
        sr.quantityAvailable += qty;
        System.out.println("Stock Updated [ID:27979]");
    }

    private static void sellStock() {
        if(items.isEmpty()) { System.out.println("No products [ID:27979]"); return; }
        System.out.print("Enter Product ID: "); int id = sc.nextInt();
        StockReport sr = find(id);
        if(sr == null) { System.out.println("Not found [ID:27979]"); return; }
        System.out.print("Enter Sale Quantity: "); int qty = sc.nextInt();
        if(qty > sr.quantityAvailable) { System.out.println("Not enough stock [ID:27979]"); return; }
        sr.quantityAvailable -= qty;
        System.out.println("Sale Processed [ID:27979]");
    }

    private static void viewInventory() {
        for(StockReport s : items) {
            System.out.println("Product ID: " + s.getId() + " Qty: " + s.quantityAvailable + " [ID:27979]");
        }
    }

    private static void generateReport() {
        for(StockReport s : items) s.generateReport();
    }

    private static StockReport find(int id) {
        return items.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
    }

    private static boolean isValidPhone(String phone) {
        if (phone == null || phone.length() != 10) return false;
        if (!phone.matches("\\d{10}")) return false;
        return phone.startsWith("078") || phone.startsWith("079") || phone.startsWith("072") || phone.startsWith("073");
    }

    private static boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) return false;
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }
}
