import java.util.ArrayList;
import java.util.List;

// Reservation class
class Reservation {
    String guestName;
    String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public void display() {
        System.out.println("Guest: " + guestName + " | Room: " + roomType);
    }
}

// Booking History
class BookingHistory {

    private List<Reservation> history = new ArrayList<>();

    // Add confirmed booking
    public void addBooking(Reservation reservation) {
        history.add(reservation);
    }

    // Get all bookings
    public List<Reservation> getAllBookings() {
        return history;
    }
}

// Reporting Service
class BookingReportService {

    public void generateReport(List<Reservation> bookings) {

        System.out.println("\n===== Booking History Report =====");

        for (Reservation r : bookings) {
            r.display();
        }

        System.out.println("\nTotal Bookings: " + bookings.size());
    }
}

// Main class
public class UC8 {

    public static void main(String[] args) {

        System.out.println("===== Book My Stay App v8.0 =====");

        BookingHistory history = new BookingHistory();

        // Simulating confirmed bookings
        history.addBooking(new Reservation("Abhijit", "Single Room"));
        history.addBooking(new Reservation("Rahul", "Double Room"));
        history.addBooking(new Reservation("Priya", "Suite Room"));

        // Generate report
        BookingReportService reportService = new BookingReportService();
        reportService.generateReport(history.getAllBookings());
    }
}