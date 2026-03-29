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
}

// Shared Inventory (THREAD SAFE)
class RoomInventory {

    private int singleRooms = 2;

    // synchronized critical section
    public synchronized boolean bookRoom(String guest) {

        if (singleRooms > 0) {
            System.out.println(guest + " is booking...");

            singleRooms--;

            System.out.println("Booking confirmed for " + guest +
                    " | Remaining rooms: " + singleRooms);

            return true;
        } else {
            System.out.println("No rooms available for " + guest);
            return false;
        }
    }
}

// Booking Processor (Thread)
class BookingProcessor extends Thread {

    private Queue<Reservation> queue;
    private RoomInventory inventory;

    public BookingProcessor(Queue<Reservation> queue, RoomInventory inventory) {
        this.queue = queue;
        this.inventory = inventory;
    }

    @Override
    public void run() {

        while (true) {

            Reservation r;

            // synchronized queue access
            synchronized (queue) {
                if (queue.isEmpty()) break;
                r = queue.poll();
            }

            // process booking
            inventory.bookRoom(r.guestName);
        }
    }
}

// Main class
public class UC11 {

    public static void main(String[] args) {

        System.out.println("===== Book My Stay App v11.0 =====");

        Queue<Reservation> queue = new LinkedList<>();

        // Add booking requests
        queue.add(new Reservation("Abhijit", "Single Room"));
        queue.add(new Reservation("Rahul", "Single Room"));
        queue.add(new Reservation("Priya", "Single Room"));

        RoomInventory inventory = new RoomInventory();

        // Create threads
        Thread t1 = new BookingProcessor(queue, inventory);
        Thread t2 = new BookingProcessor(queue, inventory);

        // Start threads
        t1.start();
        t2.start();
    }
}