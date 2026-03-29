import java.util.HashMap;
import java.util.Map;

// Custom Exception
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
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
        return inventory.getOrDefault(roomType, -1);
    }

    public void updateAvailability(String roomType, int count) {
        inventory.put(roomType, count);
    }
}

// Validator class
class BookingValidator {

    public static void validate(String roomType, int requestedRooms, RoomInventory inventory)
            throws InvalidBookingException {

        // Check valid room type
        if (inventory.getAvailability(roomType) == -1) {
            throw new InvalidBookingException("Invalid room type: " + roomType);
        }

        // Check negative input
        if (requestedRooms <= 0) {
            throw new InvalidBookingException("Room count must be greater than 0");
        }

        // Check availability
        int available = inventory.getAvailability(roomType);

        if (requestedRooms > available) {
            throw new InvalidBookingException("Not enough rooms available");
        }
    }
}

// Main class
public class UC9 {

    public static void main(String[] args) {

        System.out.println("===== Book My Stay App v9.0 =====");

        RoomInventory inventory = new RoomInventory();

        try {
            // Test case (change values to test)
            String roomType = "Single Room";
            int requestedRooms = 6; // invalid (more than available)

            // Validate before booking
            BookingValidator.validate(roomType, requestedRooms, inventory);

            System.out.println("Booking successful!");

        } catch (InvalidBookingException e) {
            System.out.println("Booking Failed: " + e.getMessage());
        }

        System.out.println("System is still running safely...");
    }
}