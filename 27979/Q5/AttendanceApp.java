import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

class Entity {
    private int id;
    private LocalDate createdDate;
    private LocalDate updatedDate;

    public Entity(int id) {
        if (id <= 0) throw new IllegalArgumentException("ID must be > 0");
        this.id = id;
        this.createdDate = LocalDate.now();
        this.updatedDate = LocalDate.now();
    }

    public int getId() { return id; }
    public LocalDate getCreatedDate() { return createdDate; }
    public LocalDate getUpdatedDate() { return updatedDate; }
    protected void touchUpdated() { this.updatedDate = LocalDate.now(); }
}

class Institution extends Entity {
    private String institutionName;
    private String code;
    private String address;

    public Institution(int id, String institutionName, String code, String address) {
        super(id);
        if (code == null || code.length() < 3) throw new IllegalArgumentException("Institution code must be ≥ 3 chars");
        this.institutionName = institutionName;
        this.code = code;
        this.address = address;
    }

    public String getInstitutionName() { return institutionName; }
    public String getCode() { return code; }
    public String getAddress() { return address; }
}

class Department extends Institution {
    private String departmentName;
    private String departmentHead;

    public Department(int id, String institutionName, String code, String address,
                      String departmentName, String departmentHead) {
        super(id, institutionName, code, address);
        if (departmentName == null || departmentName.isBlank()) throw new IllegalArgumentException("Department name required");
        if (departmentHead == null || departmentHead.isBlank()) throw new IllegalArgumentException("Department head required");
        this.departmentName = departmentName;
        this.departmentHead = departmentHead;
    }

    public String getDepartmentName() { return departmentName; }
    public String getDepartmentHead() { return departmentHead; }
}

class Course extends Department {
    private String courseName;
    private String courseCode;
    private int credits;

    public Course(int id, String institutionName, String code, String address,
                  String departmentName, String departmentHead,
                  String courseName, String courseCode, int credits) {
        super(id, institutionName, code, address, departmentName, departmentHead);
        if (courseName == null || courseName.isBlank()) throw new IllegalArgumentException("Course name required");
        if (courseCode == null || courseCode.isBlank()) throw new IllegalArgumentException("Course code required");
        if (credits <= 0) throw new IllegalArgumentException("Credits must be > 0");
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.credits = credits;
    }

    public String getCourseName() { return courseName; }
    public String getCourseCode() { return courseCode; }
    public int getCredits() { return credits; }
}

class Instructor extends Course {
    private String instructorName;
    private String email;
    private String phone;

    public Instructor(int id, String institutionName, String code, String address,
                      String departmentName, String departmentHead,
                      String courseName, String courseCode, int credits,
                      String instructorName, String email, String phone) {
        super(id, institutionName, code, address, departmentName, departmentHead, courseName, courseCode, credits);
        if (instructorName == null || instructorName.isBlank()) throw new IllegalArgumentException("Instructor name required");
        if (email == null || !email.contains("@")) throw new IllegalArgumentException("Invalid email");
        if (phone == null || !phone.matches("\\d{10}")) throw new IllegalArgumentException("Phone must be 10 digits");
        this.instructorName = instructorName;
        this.email = email;
        this.phone = phone;
    }

    public String getInstructorName() { return instructorName; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
}

class Student extends Instructor {
    private String studentName;
    private String studentID;
    private int age;

    public Student(int id, String institutionName, String code, String address,
                   String departmentName, String departmentHead,
                   String courseName, String courseCode, int credits,
                   String instructorName, String email, String phone,
                   String studentName, String studentID, int age) {
        super(id, institutionName, code, address, departmentName, departmentHead,
                courseName, courseCode, credits, instructorName, email, phone);
        if (studentName == null || studentName.isBlank()) throw new IllegalArgumentException("Student name required");
        if (studentID == null || studentID.isBlank()) throw new IllegalArgumentException("Student ID required");
        if (age <= 0) throw new IllegalArgumentException("Age must be > 0");
        this.studentName = studentName;
        this.studentID = studentID;
        this.age = age;
    }

    public String getStudentName() { return studentName; }
    public String getStudentID() { return studentID; }
    public int getAge() { return age; }

    @Override
    public String toString() {
        return studentID + " - " + studentName + " (Age: " + age + ") [ID:27979]";
    }
}

class ClassSessionSimple {
    int sessionId;
    LocalDate date;
    String topic;
    public ClassSessionSimple(int sessionId, LocalDate date, String topic) {
        this.sessionId = sessionId; this.date = date; this.topic = topic;
    }
    public String toString() { return "Session " + sessionId + ": " + date + " - " + topic + " [ID:27979]"; }
}

class AttendanceRecordSimple {
    String recordId;
    String studentID;
    int sessionID;
    String status;
    public AttendanceRecordSimple(String recordId, String studentID, int sessionID, String status) {
        this.recordId = recordId; this.studentID = studentID; this.sessionID = sessionID; this.status = status;
    }
    public String toString() { return "[" + recordId + "] " + studentID + " - S" + sessionID + " : " + status + " [ID:27979]"; }
}

class LeaveRequestSimple {
    String requestId;
    String studentID;
    int sessionID;
    LocalDate requestDate;
    String reason;
    boolean approved;
    public LeaveRequestSimple(String requestId, String studentID, int sessionID, LocalDate requestDate, String reason, boolean approved) {
        this.requestId = requestId; this.studentID = studentID; this.sessionID = sessionID; this.requestDate = requestDate; this.reason = reason; this.approved = approved;
    }
    public String toString() { return "[" + requestId + "] " + studentID + " for session " + sessionID + " on " + requestDate + " Approved:" + approved + " [ID:27979]"; }
}

public class AttendanceApp {
    private static final Scanner sc = new Scanner(System.in);
    private static final Map<String, Student> students = new LinkedHashMap<>();
    private static final Map<Integer, ClassSessionSimple> sessions = new LinkedHashMap<>();
    private static final List<AttendanceRecordSimple> records = new ArrayList<>();
    private static final List<LeaveRequestSimple> leaves = new ArrayList<>();

    private static final String DEFAULT_INSTITUTION = "My Institute";
    private static final String DEFAULT_INST_CODE = "MIN";
    private static final String DEFAULT_ADDRESS = "Main Campus";
    private static final String DEFAULT_DEPT = "Computer Science";
    private static final String DEFAULT_HEAD = "Dr. Head";
    private static final String DEFAULT_COURSE = "Data Structures";
    private static final String DEFAULT_COURSE_CODE = "CS201";
    private static final int DEFAULT_CREDITS = 3;
    private static final String DEFAULT_INSTRUCTOR = "Prof. Smith";
    private static final String DEFAULT_INSTRUCTOR_EMAIL = "smith@inst.edu";
    private static final String DEFAULT_INSTRUCTOR_PHONE = "0780000001";

    private static int sessionCounter = 0;
    private static int attendanceCounter = 0;
    private static int leaveCounter = 0;
    private static int studentAutoId = 1;

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            printMenu();
            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1" -> addStudent();
                case "2" -> viewStudents();
                case "3" -> addSession();
                case "4" -> viewSessions();
                case "5" -> markAttendance();
                case "6" -> requestLeave();
                case "7" -> approveLeaves();
                case "8" -> generateClassSummary();
                case "9" -> generateStudentSummary();
                case "10" -> viewAttendanceRecords();
                case "11" -> viewLeaveRequests();
                case "0" -> { System.out.println("Exiting... Goodbye! [ID:27979]"); running = false; }
                default -> System.out.println("Invalid choice — try again. [ID:27979]");
            }
        }
        sc.close();
    }

    private static void printMenu() {
        System.out.println("\nMenu [ID:27979]:");
        System.out.println("1. Add Student");
        System.out.println("2. View Students");
        System.out.println("3. Add Class Session");
        System.out.println("4. View Sessions");
        System.out.println("5. Mark Attendance");
        System.out.println("6. Request Leave");
        System.out.println("7. Approve/Reject Leave Requests");
        System.out.println("8. Generate Class Attendance Summary");
        System.out.println("9. Generate Individual Student Summary");
        System.out.println("10. View Attendance Records");
        System.out.println("11. View Leave Requests");
        System.out.println("0. Exit");
        System.out.print("Choose: ");
    }

    private static void addStudent() {
        try {
            System.out.print("Student name: ");
            String name = sc.nextLine().trim();
            System.out.print("Student ID (unique): ");
            String sid = sc.nextLine().trim();
            System.out.print("Age: ");
            int age = Integer.parseInt(sc.nextLine().trim());
            if (students.containsKey(sid)) {
                System.out.println("Student ID already exists. Use a unique ID. [ID:27979]");
                return;
            }
            Student s = new Student(studentAutoId++, DEFAULT_INSTITUTION, DEFAULT_INST_CODE, DEFAULT_ADDRESS,
                    DEFAULT_DEPT, DEFAULT_HEAD, DEFAULT_COURSE, DEFAULT_COURSE_CODE, DEFAULT_CREDITS,
                    DEFAULT_INSTRUCTOR, DEFAULT_INSTRUCTOR_EMAIL, DEFAULT_INSTRUCTOR_PHONE,
                    name, sid, age);
            students.put(sid, s);
            System.out.println("Added: " + s);
        } catch (NumberFormatException nfe) {
            System.out.println("Invalid number input. Student not added. [ID:27979]");
        } catch (IllegalArgumentException iae) {
            System.out.println("Validation error: " + iae.getMessage() + " [ID:27979]");
        }
    }

    private static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students registered. [ID:27979]");
            return;
        }
        System.out.println("Registered Students [ID:27979]:");
        students.values().forEach(System.out::println);
    }

    private static void addSession() {
        try {
            System.out.print("Session date (YYYY-MM-DD): ");
            String dateStr = sc.nextLine().trim();
            LocalDate date = LocalDate.parse(dateStr);
            System.out.print("Topic: ");
            String topic = sc.nextLine().trim();
            sessionCounter++;
            sessions.put(sessionCounter, new ClassSessionSimple(sessionCounter, date, topic));
            System.out.println("Session added: " + sessions.get(sessionCounter));
        } catch (DateTimeParseException dtpe) {
            System.out.println("Invalid date format. [ID:27979]");
        } catch (IllegalArgumentException iae) {
            System.out.println("Error: " + iae.getMessage() + " [ID:27979]");
        }
    }

    private static void viewSessions() {
        if (sessions.isEmpty()) {
            System.out.println("No sessions created. [ID:27979]");
            return;
        }
        System.out.println("Class Sessions [ID:27979]:");
        sessions.values().forEach(System.out::println);
    }

    private static void markAttendance() {
        if (sessions.isEmpty()) { System.out.println("No sessions exist. [ID:27979]"); return; }
        if (students.isEmpty()) { System.out.println("No students registered. [ID:27979]"); return; }
        System.out.println("Available sessions [ID:27979]:");
        sessions.forEach((k,v) -> System.out.println(k + ": " + v));
        System.out.print("Enter session ID: ");
        try {
            int sid = Integer.parseInt(sc.nextLine().trim());
            ClassSessionSimple sess = sessions.get(sid);
            if (sess == null) { System.out.println("Session not found. [ID:27979]"); return; }
            System.out.println("Marking attendance for " + sess);
            for (String studentID : students.keySet()) {
                System.out.print("Status for " + studentID + " (P=Present / A=Absent) [default P]: ");
                String in = sc.nextLine().trim();
                String status = in.isEmpty() || in.equalsIgnoreCase("P") ? "Present" : in.equalsIgnoreCase("A") ? "Absent" : "Absent";
                if (!status.equalsIgnoreCase("Present") && !status.equalsIgnoreCase("Absent")) System.out.println("Invalid input, defaulting to Absent. [ID:27979]");
                attendanceCounter++;
                String recId = "R" + attendanceCounter;
                records.add(new AttendanceRecordSimple(recId, studentID, sid, status));
            }
            System.out.println("Attendance recorded for session " + sid + " [ID:27979]");
        } catch (NumberFormatException nfe) { System.out.println("Invalid session id. [ID:27979]"); }
    }

    private static void requestLeave() {
        if (students.isEmpty()) { System.out.println("No students registered. [ID:27979]"); return; }
        if (sessions.isEmpty()) { System.out.println("No sessions exist. [ID:27979]"); return; }
        System.out.print("Student ID requesting leave: ");
        String sid = sc.nextLine().trim();
        if (!students.containsKey(sid)) { System.out.println("Unknown student ID. [ID:27979]"); return; }
        System.out.print("Session ID for leave: ");
        int sessId;
        try { sessId = Integer.parseInt(sc.nextLine().trim()); } 
        catch (NumberFormatException nfe) { System.out.println("Invalid session id. [ID:27979]"); return; }
        if (!sessions.containsKey(sessId)) { System.out.println("Session not found. [ID:27979]"); return; }
        System.out.print("Request date (YYYY-MM-DD) [default today]: ");
        String rd = sc.nextLine().trim();
        LocalDate requestDate = rd.isBlank() ? LocalDate.now() : LocalDate.parse(rd);
        System.out.print("Reason: ");
        String reason = sc.nextLine().trim();
        if (reason.isBlank()) { System.out.println("Reason required. [ID:27979]"); return; }
        leaveCounter++;
        leaves.add(new LeaveRequestSimple("L"+leaveCounter, sid, sessId, requestDate, reason, false));
        System.out.println("Leave requested: [" + "L"+leaveCounter + "] " + sid + " [ID:27979]");
    }

    private static void approveLeaves() {
        if (leaves.isEmpty()) { System.out.println("No leave requests. [ID:27979]"); return; }
        for (int i = 0; i < leaves.size(); i++) System.out.println(i + ": " + leaves.get(i));
        System.out.print("Select index to approve/reject (or 'q' to quit): ");
        String in = sc.nextLine().trim();
        if (in.equalsIgnoreCase("q")) return;
        try {
            int idx = Integer.parseInt(in);
            if (idx < 0 || idx >= leaves.size()) { System.out.println("Invalid index. [ID:27979]"); return; }
            LeaveRequestSimple lr = leaves.get(idx);
            System.out.print("1. Approve 2. Reject 3. Skip: ");
            String choice = sc.nextLine().trim();
            if (choice.equals("1")) { lr.approved = true; System.out.println("Approved. [ID:27979]"); }
            else if (choice.equals("2")) { lr.approved = false; System.out.println("Rejected. [ID:27979]"); }
            else System.out.println("Skipped. [ID:27979]");
        } catch (NumberFormatException nfe) { System.out.println("Invalid input. [ID:27979]"); }
    }

    private static void generateClassSummary() {
        if (records.isEmpty()) { System.out.println("No attendance records. [ID:27979]"); return; }
        Map<Integer,Integer> presentCount = new HashMap<>();
        Map<Integer,Integer> absentCount = new HashMap<>();
        for (AttendanceRecordSimple r: records) {
            presentCount.putIfAbsent(r.sessionID,0);
            absentCount.putIfAbsent(r.sessionID,0);
            if (r.status.equalsIgnoreCase("Present")) presentCount.put(r.sessionID,presentCount.get(r.sessionID)+1);
            else absentCount.put(r.sessionID,absentCount.get(r.sessionID)+1);
        }
        System.out.println("\nClass Attendance Summary by Session [ID:27979]:");
        for (Integer sid: sessions.keySet()) {
            int p = presentCount.getOrDefault(sid,0);
            int a = absentCount.getOrDefault(sid,0);
            int total = p + a;
            double percent = total==0?0.0:((double)p/total)*100.0;
            System.out.printf("Session %d (%s): Present=%d Absent=%d Attendance=%.2f%% [ID:27979]%n", sid,sessions.get(sid).date,p,a,percent);
        }
    }

    private static void generateStudentSummary() {
        if (records.isEmpty()) { System.out.println("No attendance records. [ID:27979]"); return; }
        System.out.print("Enter student ID: ");
        String sid = sc.nextLine().trim();
        if (!students.containsKey(sid)) { System.out.println("Unknown student ID. [ID:27979]"); return; }
        int present = 0, absent = 0;
        for (AttendanceRecordSimple r: records) {
            if (!r.studentID.equals(sid)) continue;
            if (r.status.equalsIgnoreCase("Present")) present++; else absent++;
        }
        int total = present+absent;
        double percent = total==0?0.0:((double)present/total)*100.0;
        System.out.printf("Student %s: Present=%d Absent=%d Attendance=%.2f%% [ID:27979]%n", sid,present,absent,percent);
    }

    private static void viewAttendanceRecords() {
        if (records.isEmpty()) { System.out.println("No records. [ID:27979]"); return; }
        System.out.println("Attendance Records [ID:27979]:");
        records.forEach(System.out::println);
    }

    private static void viewLeaveRequests() {
        if (leaves.isEmpty()) { System.out.println("No leave requests. [ID:27979]"); return; }
        System.out.println("Leave Requests [ID:27979]:");
        leaves.forEach(System.out::println);
    }
}
