import java.util.*;

// Reservation class
class Reservation {
    String guestName;
    String roomType;
    String roomId;

    public Reservation(String guestName, String roomType, String roomId) {
        this.guestName = guestName;
        this.roomType = roomType;
        this.roomId = roomId;
    }
}

// Inventory class
class RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 2);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public void increase(String roomType) {
        inventory.put(roomType, getAvailability(roomType) + 1);
    }

    public void decrease(String roomType) {
        inventory.put(roomType, getAvailability(roomType) - 1);
    }

    public void display() {
        System.out.println("\nInventory:");
        for (String key : inventory.keySet()) {
            System.out.println(key + " → " + inventory.get(key));
        }
    }
}

// Booking history
class BookingHistory {
    private List<Reservation> history = new ArrayList<>();

    public void add(Reservation r) {
        history.add(r);
    }

    public boolean remove(Reservation r) {
        return history.remove(r);
    }

    public boolean exists(String roomId) {
        for (Reservation r : history) {
            if (r.roomId.equals(roomId)) {
                return true;
            }
        }
        return false;
    }

    public Reservation getById(String roomId) {
        for (Reservation r : history) {
            if (r.roomId.equals(roomId)) {
                return r;
            }
        }
        return null;
    }
}

// Cancellation service
class CancellationService {

    private Stack<String> rollbackStack = new Stack<>();

    // Booking (for demo)
    public void book(Reservation r, RoomInventory inventory, BookingHistory history) {
        if (inventory.getAvailability(r.roomType) > 0) {
            inventory.decrease(r.roomType);
            history.add(r);
            rollbackStack.push(r.roomId);
            System.out.println("Booked: " + r.guestName + " (" + r.roomId + ")");
        } else {
            System.out.println("No rooms available");
        }
    }

    // Cancellation
    public void cancel(String roomId, RoomInventory inventory, BookingHistory history) {

        // Validate existence
        if (!history.exists(roomId)) {
            System.out.println("Invalid cancellation: Booking not found");
            return;
        }

        // LIFO check
        if (rollbackStack.isEmpty() || !rollbackStack.peek().equals(roomId)) {
            System.out.println("Cancellation must follow LIFO order");
            return;
        }

        rollbackStack.pop();

        Reservation r = history.getById(roomId);
        history.remove(r);

        inventory.increase(r.roomType);

        System.out.println("Cancelled booking for " + r.guestName);
    }
}

// Main class
public class UC10 {

    public static void main(String[] args) {

        System.out.println("===== Book My Stay App v10.0 =====");

        RoomInventory inventory = new RoomInventory();
        BookingHistory history = new BookingHistory();
        CancellationService service = new CancellationService();

        // Bookings
        service.book(new Reservation("Abhijit", "Single Room", "R101"), inventory, history);
        service.book(new Reservation("Rahul", "Double Room", "R102"), inventory, history);

        inventory.display();

        // Cancel last booking (valid)
        service.cancel("R102", inventory, history);

        inventory.display();

        // Try invalid cancellation
        service.cancel("R999", inventory, history);
    }
}