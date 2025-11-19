import java.util.*;

class Entity {
    protected int id;
    protected Date createdDate;
    protected Date updatedDate;

    public Entity(int id) {
        if (id <= 0) throw new IllegalArgumentException("ID must be greater than zero");
        this.id = id;
        this.createdDate = new Date();
        this.updatedDate = new Date();
    }
}


class Airport extends Entity {
    private String airportName;
    private String code;
    private String location;

    public Airport(int id, String airportName, String code, String location) {
        super(id);
        if (!code.matches("[A-Z]{3}"))
            throw new IllegalArgumentException("Airport code must be 3 uppercase letters");
        this.airportName = airportName;
        this.code = code;
        this.location = location;
    }
}


class Airline extends Airport {
    private String airlineName;
    private String airlineCode;
    private String contactEmail;

    public Airline(int id, String airportName, String code, String location,
                   String airlineName, String airlineCode, String contactEmail) {
        super(id, airportName, code, location);
        if (!airlineCode.matches("[A-Za-z]{2,4}"))
            throw new IllegalArgumentException("Airline code must be 2-4 letters");
        if (!contactEmail.contains("@"))
            throw new IllegalArgumentException("Invalid email");
        this.airlineName = airlineName;
        this.airlineCode = airlineCode;
        this.contactEmail = contactEmail;
    }
}

// Flight class
class Flight extends Airline {
    private String flightNumber;
    private String departure;
    private String destination;
    private double baseFare;

    public Flight(int id, String airportName, String airportCode, String location,
                  String airlineName, String airlineCode, String email,
                  String flightNumber, String departure, String destination, double baseFare) {
        super(id, airportName, airportCode, location, airlineName, airlineCode, email);
        if (baseFare <= 0) throw new IllegalArgumentException("Fare must be greater than 0");
        this.flightNumber = flightNumber;
        this.departure = departure;
        this.destination = destination;
        this.baseFare = baseFare;
    }

    public double getBaseFare() { return baseFare; }
    public String getFlightNumber() { return flightNumber; }
    public String getDeparture() { return departure; }
    public String getDestination() { return destination; }
}


class Pilot extends Flight {
    private String pilotName;
    private String licenseNumber;
    private int experienceYears;

    public Pilot(int id, String airportName, String airportCode, String location,
                 String airlineName, String airlineCode, String email,
                 String flightNumber, String departure, String destination, double baseFare,
                 String pilotName, String licenseNumber, int experienceYears) {

        super(id, airportName, airportCode, location, airlineName, airlineCode, email,
                flightNumber, departure, destination, baseFare);
        if (experienceYears < 2)
            throw new IllegalArgumentException("Experience must be at least 2 years");
        this.pilotName = pilotName;
        this.licenseNumber = licenseNumber;
        this.experienceYears = experienceYears;
    }
}

class CabinCrew extends Pilot {
    private String crewName;
    private String role;
    private String shift;

    public CabinCrew(int id, String airportName, String airportCode, String location,
                     String airlineName, String airlineCode, String email,
                     String flightNumber, String departure, String destination, double baseFare,
                     String pilotName, String licenseNumber, int experienceYears,
                     String crewName, String role, String shift) {

        super(id, airportName, airportCode, location, airlineName, airlineCode, email,
                flightNumber, departure, destination, baseFare,
                pilotName, licenseNumber, experienceYears);
        if (!shift.equalsIgnoreCase("Day") && !shift.equalsIgnoreCase("Night"))
            throw new IllegalArgumentException("Shift must be Day or Night");
        this.crewName = crewName;
        this.role = role;
        this.shift = shift;
    }
}


class Passenger extends CabinCrew {
    protected String passengerName;
    protected int age;
    protected String gender;
    protected String contact;

    public Passenger(int id, String airportName, String airportCode, String location,
                     String airlineName, String airlineCode, String email,
                     String flightNumber, String departure, String destination, double baseFare,
                     String pilotName, String licenseNumber, int experienceYears,
                     String crewName, String role, String shift,
                     String passengerName, int age, String gender, String contact) {

        super(id, airportName, airportCode, location, airlineName, airlineCode, email,
                flightNumber, departure, destination, baseFare,
                pilotName, licenseNumber, experienceYears,
                crewName, role, shift);

        if (age <= 0) throw new IllegalArgumentException("Age must be greater than 0");
        if (!gender.matches("(?i)Male|Female|Other"))
            throw new IllegalArgumentException("Gender must be Male, Female or Other");
        this.passengerName = passengerName;
        this.age = age;
        this.gender = gender;
        this.contact = contact;
    }
}

class Booking extends Passenger {
    protected String bookingDate;
    protected String seatNumber;
    protected String travelClass;

    public Booking(int id, String airportName, String airportCode, String location,
                   String airlineName, String airlineCode, String email,
                   String flightNumber, String departure, String destination, double baseFare,
                   String pilotName, String licenseNumber, int experienceYears,
                   String crewName, String role, String shift,
                   String passengerName, int age, String gender, String contact,
                   String bookingDate, String seatNumber, String travelClass) {

        super(id, airportName, airportCode, location, airlineName, airlineCode, email,
                flightNumber, departure, destination, baseFare,
                pilotName, licenseNumber, experienceYears,
                crewName, role, shift,
                passengerName, age, gender, contact);

        if (!travelClass.matches("(?i)Economy|Business|First"))
            throw new IllegalArgumentException("Invalid travel class");
        this.bookingDate = bookingDate;
        this.seatNumber = seatNumber;
        this.travelClass = travelClass;
    }

    public String getTravelClass() { return travelClass; }
}


class Payment extends Booking {
    protected String paymentDate;
    protected String paymentMethod;
    protected double amountPaid;

    public Payment(int id, String airportName, String airportCode, String location,
                   String airlineName, String airlineCode, String email,
                   String flightNumber, String departure, String destination, double baseFare,
                   String pilotName, String licenseNumber, int experienceYears,
                   String crewName, String role, String shift,
                   String passengerName, int age, String gender, String contact,
                   String bookingDate, String seatNumber, String travelClass,
                   String paymentDate, String paymentMethod, double amountPaid) {

        super(id, airportName, airportCode, location, airlineName, airlineCode, email,
                flightNumber, departure, destination, baseFare,
                pilotName, licenseNumber, experienceYears,
                crewName, role, shift,
                passengerName, age, gender, contact,
                bookingDate, seatNumber, travelClass);

        if (amountPaid <= 0)
            throw new IllegalArgumentException("Payment must be greater than 0");

        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.amountPaid = amountPaid;
    }

    public double getAmountPaid() { return amountPaid; }
}


final class Ticket extends Payment {
    private String ticketNumber;
    private String issueDate;

    public Ticket(int id, String airportName, String airportCode, String location,
                  String airlineName, String airlineCode, String email,
                  String flightNumber, String departure, String destination, double baseFare,
                  String pilotName, String licenseNumber, int experienceYears,
                  String crewName, String role, String shift,
                  String passengerName, int age, String gender, String contact,
                  String bookingDate, String seatNumber, String travelClass,
                  String paymentDate, String paymentMethod, double amountPaid,
                  String ticketNumber, String issueDate) {

        super(id, airportName, airportCode, location, airlineName, airlineCode, email,
                flightNumber, departure, destination, baseFare,
                pilotName, licenseNumber, experienceYears,
                crewName, role, shift,
                passengerName, age, gender, contact,
                bookingDate, seatNumber, travelClass,
                paymentDate, paymentMethod, amountPaid);

        this.ticketNumber = ticketNumber;
        this.issueDate = issueDate;
    }

    public double calculateFare(double baseFare, String travelClass) {
        double taxes = baseFare * 0.15;
        double discount = travelClass.equalsIgnoreCase("First") ? -50 :
                travelClass.equalsIgnoreCase("Business") ? -25 : 0;
        return baseFare + taxes + discount;
    }

    public void displayTicket(double finalFare) {
        System.out.println("Ticket Number: " + ticketNumber + " |27979");
        System.out.println("Issued On: " + issueDate + " |27979");
        System.out.println("Flight Number: " + getFlightNumber() + " |27979");
        System.out.println("Route: " + getDeparture() + " to " + getDestination() + " |27979");
        System.out.println("Base Fare: $" + getBaseFare() + " |27979");
        System.out.println("Passenger Name: " + passengerName + " |27979");
        System.out.println("Age: " + age + " |27979");
        System.out.println("Gender: " + gender + " |27979");
        System.out.println("Contact: " + contact + " |27979");
        System.out.println("Booking Date: " + bookingDate + " |27979");
        System.out.println("Seat Number: " + seatNumber + " |27979");
        System.out.println("Travel Class: " + travelClass + " |27979");
        System.out.println("Payment Date: " + paymentDate + " |27979");
        System.out.println("Payment Method: " + paymentMethod + " |27979");
        System.out.println("Amount Paid: $" + amountPaid + " |27979");
        System.out.println("Final Fare: $" + finalFare + " |27979");
    }
}

// Main Application
public class FlightBookingApp {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Ticket ticket = null;

        while (true) {
            System.out.println("1. Book Ticket |27979");
            System.out.println("2. View Ticket |27979");
            System.out.println("3. Exit |27979");
            System.out.print("Enter choice: ");
            int ch = getPositiveInt("Invalid choice. Enter 1-3: ", 1, 3);

            if (ch == 1) {
                try {
                    ticket = createTicket();
                    System.out.println("Ticket booked successfully |27979");
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage() + " |27979");
                }
            } else if (ch == 2) {
                if (ticket == null) System.out.println("No ticket found |27979");
                else {
                    double finalFare = ticket.calculateFare(ticket.getBaseFare(), ticket.getTravelClass());
                    ticket.displayTicket(finalFare);
                }
            } else if (ch == 3) {
                System.out.println("Exit |27979");
                break;
            }
        }
    }


    public static int getPositiveInt(String prompt, int min, int max) {
        while (true) {
            try {
                int value = Integer.parseInt(sc.nextLine().trim());
                if (value >= min && value <= max) return value;
            } catch (Exception ignored) {}
            System.out.print(prompt);
        }
    }

    public static double getPositiveDouble(String prompt) {
        while (true) {
            try {
                double value = Double.parseDouble(sc.nextLine().trim());
                if (value > 0) return value;
            } catch (Exception ignored) {}
            System.out.print(prompt);
        }
    }

    public static String getNonEmptyString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim();
            if (!s.isEmpty()) return s;
        }
    }

    public static String getGender(String prompt) {
        while (true) {
            System.out.print(prompt);
            String g = sc.nextLine().trim();
            if (g.equalsIgnoreCase("Male") || g.equalsIgnoreCase("Female") || g.equalsIgnoreCase("Other"))
                return g;
        }
    }

    public static String getContact(String prompt) {
        while (true) {
            System.out.print(prompt);
            String c = sc.nextLine().trim();
            if (c.matches("^(078|079|072|073)\\d{7}$")) return c;
        }
    }

    public static String getTravelClass(String prompt) {
        while (true) {
            System.out.print(prompt);
            String t = sc.nextLine().trim();
            if (t.equalsIgnoreCase("Economy") || t.equalsIgnoreCase("Business") || t.equalsIgnoreCase("First"))
                return t;
        }
    }

   
    public static Ticket createTicket() {
        int id = 27979;
        String airportName = "Kigali International Airport";
        String airportCode = "KGL";
        String location = "Kigali";
        String airlineName = "RwandaAir";
        String airlineCode = "WB";
        String email = "info@rwandair.com";
        String flightNumber = "WB101";
        double baseFare = 500.0;
        String pilotName = "Captain John Smith";
        String licenseNumber = "PIL12345";
        int experienceYears = 10;
        String crewName = "Sarah Johnson";
        String role = "Flight Attendant";
        String shift = "Day";

        String departure = getNonEmptyString("Departure: ");
        String destination = getNonEmptyString("Destination: ");
        String passengerName = getNonEmptyString("Passenger Name: ");
        int age = getPositiveInt("Age: ", 1, 120);
        String gender = getGender("Gender (Male/Female/Other): ");
        String contact = getContact("Contact (078/079/072/073 + 7 digits): ");
        String bookingDate = getNonEmptyString("Booking Date: ");
        String seatNumber = getNonEmptyString("Seat Number: ");
        String travelClass = getTravelClass("Travel Class (Economy/Business/First): ");
        String paymentDate = getNonEmptyString("Payment Date: ");
        String paymentMethod = getNonEmptyString("Payment Method: ");
        double amountPaid = getPositiveDouble("Amount Paid: ");
        String ticketNumber = getNonEmptyString("Ticket Number: ");
        String issueDate = getNonEmptyString("Issue Date: ");

        return new Ticket(
                id, airportName, airportCode, location,
                airlineName, airlineCode, email,
                flightNumber, departure, destination, baseFare,
                pilotName, licenseNumber, experienceYears,
                crewName, role, shift,
                passengerName, age, gender, contact,
                bookingDate, seatNumber, travelClass,
                paymentDate, paymentMethod, amountPaid,
                ticketNumber, issueDate
        );
    }
}
