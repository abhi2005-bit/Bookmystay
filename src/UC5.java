import java.util.LinkedList;
import java.util.Queue;

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

// Booking Queue class
class BookingRequestQueue {

    private Queue<Reservation> queue = new LinkedList<>();

    // Add request
    public void addRequest(Reservation reservation) {
        queue.add(reservation);
        System.out.println("Request added for " + reservation.guestName);
    }

    // Show all requests
    public void displayQueue() {
        System.out.println("\n===== Booking Queue (FIFO) =====");

        for (Reservation r : queue) {
            r.display();
        }
    }
}

// Main class
public class UseCase5BookingRequestQueue {

    public static void main(String[] args) {

        System.out.println("===== Book My Stay App v5.0 =====");

        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        // Add booking requests
        bookingQueue.addRequest(new Reservation("Abhijit", "Single Room"));
        bookingQueue.addRequest(new Reservation("Rahul", "Double Room"));
        bookingQueue.addRequest(new Reservation("Priya", "Suite Room"));

        // Display queue
        bookingQueue.displayQueue();
    }
}